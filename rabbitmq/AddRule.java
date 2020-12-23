import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class AddRule implements Runnable {

  public void run() {
    try{
    FileHandle fileHandle = new FileHandle();
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(JSONSimpleExample.getEnv("host", "envconfig.json"));
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare(JSONSimpleExample.getEnv("queue.rule", "envconfig.json"), false, false, false, null);
    channel.queueBind(JSONSimpleExample .getEnv("queue.rule", "envconfig.json"), JSONSimpleExample .getEnv("input.toppic", "envconfig.json"),JSONSimpleExample .getEnv("routing.key.addrule", "envconfig.json") );
    System.out.println(" [*] Waiting for rules");
    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), "UTF-8");
      fileHandle.addRules(message);
      System.out.println(" Add rule" + message + "da nhan");
    };
    channel.basicConsume(JSONSimpleExample.getEnv("queue.rule", "envconfig.json"), true, deliverCallback, consumerTag -> {
    });
  }catch( Exception e){
    System.out.println(e);
  };
  }
}
