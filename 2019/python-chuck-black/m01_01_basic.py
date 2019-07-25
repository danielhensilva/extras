balance = 1000
name = 'Chuck Black'
account_no = '01123456789'

print('name:', name, '\taccount:', account_no, '\tbalance:', balance)
print('name:', name, '\taccount:', account_no, '\tbalance:', '$' + str(balance))
print('name:', name, '\taccount:', account_no, '\tbalance:', '$' + str(float(balance)))

print(id(balance), id(name), id(account_no))
print('\n')