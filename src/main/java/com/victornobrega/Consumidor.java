package com.victornobrega;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumidor {
    public static void main(String[] args) throws Exception {
        System.out.println("Consumidor no ar");

        String NOME_FILA = "filaOla";

        //criando a fábrica de conexões e criando uma conexão
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("mqadmin");
        connectionFactory.setPassword("Admin123XX_");
        connectionFactory.setHost("localhost");
        Connection conexao = connectionFactory.newConnection();

        //criando um canal e declarando a fila
        Channel channel = conexao.createChannel();
        channel.queueDeclare(NOME_FILA, false, false, false, null);


        //Definindo a funcao callback, que será executada quando receber um objeto, no caso a mensagem.
        DeliverCallback callback = (consumerTag, delivery) -> {
            String mensagem = new String(delivery.getBody());
            System.out.println("Recebi a mensagem: " + mensagem);
        };

        //Consumindo da fila
        channel.basicConsume(NOME_FILA, true, callback, consumerTag -> {});

        System.out.println("Continuarei executando outras atividades enquanto não chega mensagem...");

    }
}
