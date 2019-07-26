bills = []
file_lines = list(open('m03_01_csv.txt', 'r'))
file_titles = file_lines[0].strip().split(',')
file_entries = list(map(lambda x: x.strip().split(','), file_lines[1:]))
file_columns_count = len(file_titles)


for file_entry in file_entries:
    bill = dict()
    for i in range(file_columns_count):
        title = file_titles[i]
        value = file_entry[i]
        bill[title] = value
    bills.append(bill)


for bill in bills:
    print(bill)