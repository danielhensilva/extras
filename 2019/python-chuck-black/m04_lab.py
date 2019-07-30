from operator import itemgetter


def print_columns(name, location, status, employer, job):
    print(f'{name:18}{location:18}{status:15}{employer:20}{job:28}')


def print_table(table):
    print('=' * 100)
    print_columns('Name', 'Location', 'Status', 'Employer', 'Job')
    print('-' * 100)
    for row in table:
        print_columns(row["name"], row["location"], row["status"], row["employer"], row["job"])


def print_table_with_title(title, table, sort_key=''):
    print('\n\n' + title)
    if len(sort_key) == 0:
        print_table(table)
    else:
        print_table(sorted(table, key=itemgetter(sort_key)))


def find_table_matches_by_key(table, search_text, search_key):
    count = 0
    matches = []
    for row in table:
        if search_text.upper() in row[search_key].upper():
            matches.append(row)
            count += 1
    return matches, count


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

print_table_with_title('Unsorted', table)
print_table_with_title('Sorted by employer', table, 'employer')
print_table_with_title('Sorted by name', table, 'name')

while True:
    print('\n\n')
    search_text = input('Search a name: ')

    if len(search_text) == 0:
        print('Exiting...')
        break

    matches, count = find_table_matches_by_key(table, search_text, 'name')

    if count == 0:
        print('Nothing found')
        continue

    print_table_with_title('Found', matches, 'name')