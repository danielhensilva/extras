from operator import itemgetter
from datetime import date
import calendar


def format_columns(vendor, year, month, day, charge, balance):
    return f'{vendor:24} {year:4} {month:15} {day:3} {charge:12} {balance:20}\n'


def get_date(date_string):
    year = int(date_string[:4])
    month = int(date_string[5:7])
    day = int(date_string[8:9])

    if month not in range(1, 13):
        return date(1969, 1, 1)

    if day not in range(1, 32):
        return date(1969, 1, 1)

    return date(year, month, day)


def read_charges(charges_file):
    charges = list()
    charges_dict = dict()
    file_lines = open(charges_file)

    for file_line in file_lines:
        file_line_slices = file_line.strip().split(',')

        info = dict()
        info['vendor'] = file_line_slices[0]
        info['date'] = file_line_slices[1]
        info['charge'] = file_line_slices[2]

        charges.append(info)

        if info['vendor'] not in charges_dict:
            charges_dict[info['vendor']] = list()

        charges_dict[info['vendor']].append(info)

    return charges, charges_dict


def write_charges_to_file(charges_list, balance, output_filename):
    if len(charges_list) == 0:
        return balance

    with open(output_filename, 'w') as out:
        header_columns = format_columns('Vendor', 'Year', 'Month', 'Day', 'Charge', 'Balance')
        out.write(header_columns)

        for charge_info in charges_list:
            balance = balance - float(charge_info['charge'])
            charge_date = get_date(charge_info['date'])
            formatted_columns = format_columns(
                charge_info['vendor'],
                charge_date.year,
                calendar.month_name[charge_date.month],
                charge_date.day,
                float(charge_info['charge']),
                balance)
            out.write(formatted_columns)

    return balance


charges_list, charges_dict = read_charges('m06_charges.txt')
bal = new_bal = 80000
sorted_charges = sorted(charges_list, key= itemgetter('date'))

current_year = 0
for charge in sorted_charges:
    this_year = get_date(charge['date']).year

    if this_year != current_year:
        if current_year != 0:
            new_bal = write_charges_to_file(charges_by_year, bal, "m06_charges-out-" + str(current_year))

        charges_by_year = list()
        current_year = this_year
        bal = new_bal

    charges_by_year.append(charge)

write_charges_to_file(charges_by_year, bal, "m06_charges-out-" + str(current_year))