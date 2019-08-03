from matplotlib import pyplot
import random

x_values = range(30)
y_values = [random.randint(x-5, x+5) for x in x_values]
pyplot.plot(x_values, y_values, "o-")

pyplot.ylabel("Value")
pyplot.xlabel("Time")
pyplot.title("Test plot")

pyplot.show()
