package service;

import server.RpcService;
import service.HelloService;
@RpcService(value = HelloService.class)
public class HelloServiceImpl implements HelloService {
    public String sayHello() {
        return "hello world";
    }
}
