简单一个RPC框架,在boot处开启客户端和服务端

使用RpcRequest,RpcResponse封装了请求和响应对象

**使用了ZookerService来注册服务,最终客户端也是通过其查找服务地址**

继承了ByteToMessageDecoder实现了半包的处理

使用protostuff实现了序列化与反序列化的处理

使用Proxy构造服务代理对象,调用远程函数时,封装响应,构造客户端Client发送响应

在服务端,强制使用cglib代理调用函数,这样做不需要知道bean对象类型与函数是什么,统一处理

