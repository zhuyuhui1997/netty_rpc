package service;

import server.RpcService;
import service.HelloService;

@RpcService(value = HelloService.class,version = "version2")
public class HelloService2Impl implements HelloService {

    @Override
    public String sayHello() {
        return "你好";
    }
}
