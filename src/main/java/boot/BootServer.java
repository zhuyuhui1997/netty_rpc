package boot;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootServer {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring.xml");
    }
}