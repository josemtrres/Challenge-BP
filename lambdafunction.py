Account1 = {'Balance': 500}
Account2 = {'Balance': 500}

#Funcion lambda para observar el saldo de las cuentas
PrintBalance = lambda Account: print("Saldo de cuenta:", Account['Balance'])

#Función lambda para realizar transferencias reflejando nuevo monto permitida la transacción
Transfers = lambda OriginAccount, DestinationAccount, Amount: [
    (OriginAccount.update({'Balance': OriginAccount['Balance'] - Amount}),
    DestinationAccount.update({'Balance': DestinationAccount['Balance'] + Amount}) ) if OriginAccount['Balance'] >= Amount else print("Saldo insuficiente"),
    200  if OriginAccount['Balance'] >= Amount else 402 #codigos HTTP para escalabilidad dentro de un API, 200 OK 402 Payment Required
]

#Imprimir los saldos antes de la transferencia
PrintBalance(Account1)
PrintBalance(Account2)

#Impresión del código http dada la transacción
print(Transfers(Account1, Account2, 300)[1])

#Saldos después de la transferencia
PrintBalance(Account1)
PrintBalance(Account2)