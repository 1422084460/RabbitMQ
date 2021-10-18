package Direct;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;
import utils.RMQUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {

    @Test
    public void sendMessage() throws IOException, TimeoutException {
        Connection connection = RMQUtil.getConnection();
        //获取连接中的通道对象
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",true,false,false,null);
        //发布消息
//        AMQP.BasicProperties properties = new AMQP.BasicProperties("text/plain",
//                "utf-8",
//                null,
//                2,
//                1, null, null, null,
//                null, null, null, null,
//                null, null);
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"direct model".getBytes());
        RMQUtil.closeResource(connection,channel);
    }
}
