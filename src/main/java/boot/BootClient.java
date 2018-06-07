package boot;

import client.RpcProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ByeByeService;
import service.HelloService;
import service.LinuxService;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;

public class BootClient {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("cli-spring.xml");
        RpcProxy rpcProxy = context.getBean(RpcProxy.class);
        /*HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.sayHello();
        System.out.println(result);
        ByeByeService byeByeService = rpcProxy.create(ByeByeService.class);
        result = byeByeService.sayByte();
        System.out.println(result);*/
        LinuxService linuxService = rpcProxy.create(LinuxService.class);
        String result = linuxService.pwd();
        System.out.println(result);
        System.out.println(Arrays.toString(linuxService.ls()));
        linuxService.cd("dir1");
        System.out.println(linuxService.pwd());
        System.out.println(Arrays.toString(linuxService.ls()));
        byte[] bytes = linuxService.cat("1.txt");
        String encoding = null;
        if (bytes.length >= 1) {
            if (bytes[0] == 0)
                encoding = "GBK";
            else
                encoding = "utf-8";

        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes), encoding));
        String s = null;
        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
        }
        Date date = linuxService.date();
        System.out.println(date);
    }
}
