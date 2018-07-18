import cn.ff.user.UserApplication;
import cn.ff.user.msg.SinkSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
@WebAppConfiguration
public class SendMsgTest {

//    @Autowired
//    private SinkSender sinkSender;
//    @Test
//    public void contextLoads(){
//
//        sinkSender.output().send(MessageBuilder.withPayload("From_Sink_Sender").build());
//    }
}
