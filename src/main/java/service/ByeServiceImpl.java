package service;

import server.RpcService;

@RpcService(value = ByeByeService.class)
public class ByeServiceImpl implements ByeByeService{
    @Override
    public String sayByte() {
       return "bye bye";
    }
}
