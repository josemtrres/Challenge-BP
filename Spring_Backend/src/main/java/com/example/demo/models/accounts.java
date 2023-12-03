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
@DynamoDBTable(tableName = "Cuentas")
public class accounts {
    
    @DynamoDBHashKey
    private String id_cuenta;
    
    @DynamoDBRangeKey
    private String id_cliente;

    @DynamoDBAttribute
    private Integer saldo;

    public String getId(){
        return id_cuenta;
    }

    public Integer getBalance(){
        return saldo;
    }
}