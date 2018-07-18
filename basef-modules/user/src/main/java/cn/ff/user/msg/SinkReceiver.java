package cn.ff.user.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(value = {Sink.class, SinkSender.class})// 绑定消息通道(channel) ; Sink 是 stream中默认对输入消息通道的绑定的定义
public class SinkReceiver {

    private final static Logger LOGGER = LoggerFactory.getLogger(SinkReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload){
        LOGGER.info("Received:" + payload);

    }

}
