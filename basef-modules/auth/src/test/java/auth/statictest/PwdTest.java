package auth.statictest;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PwdTest {

//    private static String init = "webApp";
    private static String init = "ff";


    @Test
    public void encode(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(init);
        System.out.println(password);


    }
}
