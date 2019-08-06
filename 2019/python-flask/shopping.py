from flask import Flask, render_template, request, redirect, url_for, jsonify

app = Flask(__name__)
shopping_list = ['Milk', 'Eggs', 'Chocolate', 'Apple', 'Orange', 'Coke', 'Bread', 'Cheese', 'Candies']


@app.route('/', methods=['GET', 'POST'])
def index():
    global shopping_list
    if request.method == 'POST':
        shopping_list.append(request.form['item'])
        return redirect(url_for('index'))
    return render_template('index.html', items=shopping_list)


@app.route('/api/<name>', methods=['DELETE'])
def remove_item(name):
    global shopping_list
    if name in shopping_list:
        shopping_list.remove(name)
    return redirect(url_for('index'))


def paginate(func):
    def paginate_wrapper(*args, **kwargs):
        page = int(kwargs['page'])
        page_size = int(kwargs['page_size'])

        begin_index = page * page_size
        end_index = (page + 1) * page_size

        collection = func(*args, **kwargs)
        paginated_collection = collection[begin_index:end_index]

        return jsonify({
            'collection': paginated_collection,
            'length': len(collection)
        })

    return paginate_wrapper


@app.route('/api/items/<page>/<page_size>', methods=['GET'])
@paginate
def get_items(page, page_size):
    global shopping_list
    return shopping_list


if __name__ == '__main__':
    app.run(debug=True)
