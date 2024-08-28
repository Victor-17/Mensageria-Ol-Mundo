package com.victornobrega;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * Classe responsavel por enviar itens à fila
 */
public class Produtor {

    public static void main(String[] args) throws Exception{
        //Criacao de uma factory de conexao, responsavel por criar as conexoes
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //configurações do Qeue Menager
        connectionFactory.setUsername("mqadmin");
        connectionFactory.setPassword("Admin123XX_");
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);

        String NOME_FILA = "filaOla";
        try(
                //criação de uma conexão
                Connection connection = connectionFactory.newConnection();
                //criando um canal na conexão
                Channel channel = connection.createChannel()) {
            //Esse corpo especifica o envio da mensagem para a fila

            //Declaração da fila. Se não existir ainda no queue manager, será criada. Se já existir, e foi criada com
            //os mesmos parametros, pega a referência da fila. Se foi criada com parametros diferentes, lança exceção
            channel.queueDeclare(NOME_FILA, false, false, false, null);
            String mensagem = "Olá mundo!";
            //publicando uma mensagem na fila
            channel.basicPublish("", NOME_FILA, null, mensagem.getBytes());
            System.out.println("Enviei mensagem: " + mensagem);
        }
    }
}
