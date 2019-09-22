import React, { PropTypes } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as courseActions from '../../actions/courseActions';
import CourseForm from './CourseForm';
import * as selectors from '../../selectors/selectors';
import toastr from 'toastr';

export class ManageCoursePage extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      course: Object.assign({}, this.props.course),
      errors: {},
      saving: false
    };

    this.updateCourseState = this.updateCourseState.bind(this);
    this.saveCourse = this.saveCourse.bind(this);
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.course.id === nextProps.course.id) 
      return;
    this.setState({course: Object.assign({}, nextProps.course)});
  }

  updateCourseState(event) {
    const field = event.target.name;
    let course = this.state.course;
    course[field] = event.target.value;
    return this.setState({course: course});
  }

  saveCourse(event) {
    event.preventDefault();

    if (!this.courseFormIsValid())
      return;

    this.setState({saving: true});
    this.props.actions.saveCourse(this.state.course)
      .then(() => this.redirect())
      .catch(error => {
        this.setState({saving: false});
        toastr.error(error);
      });
  }

  courseFormIsValid() {
    if (this.state.course.title.length < 5) {
      this.setState({
        errors: {
          title: 'Title must be at least 5 characters.'
        }
      });
      return false;
    }
    return true;
  }

  redirect() {
    this.setState({saving: false});
    toastr.success('Course saved');
    this.context.router.push('/courses');
  }

  render() {
    return (
      <div>
        <CourseForm
          allAuthors={this.props.authors}
          onChange={this.updateCourseState}
          onSave={this.saveCourse}
          course={this.state.course} 
          errors={this.state.errors}
          saving={this.state.saving}
        />
      </div>
    );
  }
}

ManageCoursePage.propTypes = {
  course: PropTypes.object.isRequired,
  authors: PropTypes.array.isRequired,
  actions: PropTypes.object.isRequired
};

ManageCoursePage.contextTypes = {
  router: PropTypes.object
};

function getCourseById(courses, id) {
  const course = courses.find(x => x.id === id);
  return course ? course : getDefaultCourse();
}

function getDefaultCourse() {
  return {
    id: '',
    title: '',
    length: '',
    watchHref: '',
    authorId: '',
    category: ''
  };
}

function mapStateToProps(state, ownProps) {
  return {
    course: getCourseById(state.courses, ownProps.params.id),
    authors: selectors.selectAuthors(state.authors)
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(courseActions, dispatch)
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(ManageCoursePage);