package Fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import utils.RMQUtil;

import java.io.IOException;

public class Provider {

    @Test
    public void sendMessage() throws IOException {
        Connection connection = RMQUtil.getConnection();
        Channel channel = connection.createChannel();
        //通道绑定交换机
        channel.exchangeDeclare("logs","fanout");
        //发送消息
        channel.basicPublish("logs","",null,"fanout model".getBytes());
        RMQUtil.closeResource(connection,channel);
    }
}
