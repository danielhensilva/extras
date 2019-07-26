with open('m03_01_csv.txt', 'r') as bills:
    for bill in bills:
        entries = bill.strip().split(',')
        print(f'{(entries[0]):3} {entries[1]:20} {entries[2]:30} {entries[3]:10}')