import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
public class Recv {

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost( JSONSimpleExample.getEnv("host", "envconfig.json"));
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(JSONSimpleExample .getEnv("queue.input", "envconfig.json"), true, false, false, null);
        channel.queueBind(JSONSimpleExample .getEnv("queue.input", "envconfig.json"), JSONSimpleExample .getEnv("input.toppic", "envconfig.json"),JSONSimpleExample .getEnv("routing.key.input", "envconfig.json") );
        channel.queueBind(JSONSimpleExample .getEnv("queue.input", "envconfig.json"),JSONSimpleExample .getEnv("input.toppic", "envconfig.json") , "ALL.#");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        AddRule addRule = new AddRule();
        Thread t1 = new Thread(addRule);
                    t1.start();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            ExMulticmd cmd = new ExMulticmd(message);
            Thread t2 = new Thread(cmd);
            t2.start();
            System.out.println(message);
        };
        channel.basicConsume(JSONSimpleExample.getEnv("queue.input", "envconfig.json"), true, deliverCallback, consumerTag -> {});
    }
}
