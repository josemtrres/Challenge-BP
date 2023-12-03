package com.example.demo.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable (tableName = "Transacciones")
public class transfer {

    @DynamoDBHashKey
    private long id_cuenta_origen;
    
    @DynamoDBRangeKey
    private long id_cuenta_destino;
    
    @DynamoDBAttribute
    private double monto;

    @DynamoDBAttribute
    private String tipo_transaccion;

}
