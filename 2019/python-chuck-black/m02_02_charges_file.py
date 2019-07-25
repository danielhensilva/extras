balance = 2000
name = 'Chuck Black'
account_no = '01123456789'
charges = list(open('m02_02_charges_file.txt'))

print('name:', name, '\taccount:', account_no)
print('--- balance:', '$' + str(float(balance)))

for i in range(len(charges)):
    charge = float(charges[i])
    balance -= charge
    print(f'* {i:02} (-{charge}) - balance: ${float(balance):.2f}')
