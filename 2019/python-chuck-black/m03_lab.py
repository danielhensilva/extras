def print_table(table):
    print('=' * 110)
    print(f'{"Name":20} {"Location":20} {"Status":15} {"Employer":20} {"Job":30}')
    print('-' * 110)
    for row in table:
        print(f'{row["name"]:20} {row["location"]:20} {row["status"]:15} {row["employer"]:20} {row["job"]:30}')


files_lines = open('m03_lab.txt')

table = []
for file_line in files_lines:
    column = file_line.strip().split(',')
    row = dict()
    row['name'] = column[0]
    row['location'] = column[1]
    row['status'] = column[2]
    row['employer'] = column[3]
    row['job'] = column[4]
    table.append(row)

print('\n\nUnsorted:')
print_table(table)

print('\n\nSorted by employer:')
table.sort(key=lambda x: x['employer'])
print_table(table)

print('\n\nSorted by name:')
table.sort(key=lambda x: x['name'])
print_table(table)

while True:
    print('\n\n')
    search_text = input('Search a name: ').upper()

    if len(search_text) == 0:
        print('Exiting...')
        break

    matches = []
    for row in table:
        if search_text in row['name'].upper():
            matches.append(row)

    if len(matches) == 0:
        print('Nothing found')
        continue

    print_table(matches)