balance = 1000
name = 'Chuck Black'
account_no = '01123456789'
charges = [5.99, 12.45, 28.05]

print('name:', name, '\taccount:', account_no)
print('--- balance:', '$' + str(float(balance)))

for i in range(len(charges)):
    charge = charges[i]
    balance -= charge
    print(f'* charge {i:02} ({charge}) - new balance: ${str(float(balance))}')
