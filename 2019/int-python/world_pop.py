from matplotlib import pyplot

file = open('world_pop.txt')
lines = [x.strip().split() for x in file.readlines()]
lines.reverse()

x_values = [int(year) for (year, total) in lines]
y_values = [int(total) for (year, total) in lines]

pyplot.plot(x_values, y_values, "o-")

pyplot.ylabel("Population")
pyplot.xlabel("Year")
pyplot.title("World Population per Year")

pyplot.show()
