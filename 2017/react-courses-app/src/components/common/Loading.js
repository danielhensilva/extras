import React, {PropTypes} from 'react';
import Progress from 'react-progress';

class Loading extends React.Component {
  constructor(props, context) {
    super(props, context);
    this.state = {
      percentage: 10
    };
  }

  componentDidMount() {
    this.interval = setInterval(() => this.tick(), 25);
  }

  componentWillUnmount() {
    clearInterval(this.interval);
  }

  tick() {
    if (!this.props.loading) {
      this.setState({percentage: 100});
    }
    else {
      this.setState({
        percentage: this.state.percentage + 1
      });
    }
  }

  render() {
    return <Progress percent={this.state.percentage} height={3}/>;
  }
}

Loading.propTypes = {
  loading: PropTypes.bool
};

export default Loading;