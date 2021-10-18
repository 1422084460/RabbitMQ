package Direct;

import com.rabbitmq.client.*;
import utils.RMQUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RMQUtil.getConnection();
        //获取连接中的通道对象
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",true,false,false,null);
        //消费消息
        channel.basicConsume("hello",true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body) = " + new String(body));
                System.out.println(properties);
                System.out.println(envelope);
            }

            @Override
            public void handleConsumeOk(String consumerTag) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("相当于前置通知,先执行");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //channel.close();
        //connection.close();
    }
}
