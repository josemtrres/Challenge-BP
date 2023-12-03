package com.example.demo.controllers;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class transferCntrl {

    @Autowired
    private AmazonDynamoDB dynamoDbClient;


    public void realizarTransaccion(String idCuentaOrigen, String idCuentaDestino, double monto) {

        // Consulta los saldos
        double saldoOrigen = consultarSaldo(idCuentaOrigen);
        double saldoDestino = consultarSaldo(idCuentaDestino);

        // Realiza la transferencia
        double saldoNuevoOrigen = saldoOrigen - monto;
        double saldoNuevoDestino = saldoDestino + monto;

        // Actualiza los saldos
        actualizarSaldo(idCuentaOrigen, saldoNuevoOrigen);
        actualizarSaldo(idCuentaDestino, saldoNuevoDestino);
    }

    private double consultarSaldo(String id_cuenta) {
        // Crea la solicitud de consulta
        QueryRequest queryRequest = new QueryRequest()
                .withTableName("Cuentas")
                .withKeyConditionExpression("id_cuenta = :id_cuenta")
                .withExpressionAttributeValues(Map.of(":id_cuenta", new AttributeValue().withS(id_cuenta)));
    
        // Ejecuta la solicitud
        QueryResult queryResult = dynamoDbClient.query(queryRequest);

        // Verifica si hay resultados
        if (!queryResult.getItems().isEmpty()) {
            // Obtiene el saldo
            AttributeValue saldo = queryResult.getItems().get(0).get("saldo");
            return Double.parseDouble(saldo.getN());
        } else {
            // Manejar el caso cuando no se encuentra la cuenta
            throw new RuntimeException("No se encontró la cuenta con ID: " + id_cuenta);
        }
    }

    
    private void actualizarSaldo(String id_cuenta, double saldo) {
       
            // Crea la solicitud de actualización
        PutItemRequest putItemRequest = new PutItemRequest()
                .withTableName("Cuentas")
                .withItem(Map.of(
                        "id_cuenta", new AttributeValue().withS(id_cuenta),
                        "saldo", new AttributeValue().withN(String.valueOf(saldo))
        ));
    
        dynamoDbClient.putItem(putItemRequest);
        
    }
        
}
