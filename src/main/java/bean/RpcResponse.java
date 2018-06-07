package bean;

/**
 * 封装 RPC 响应
 *由serverhandler处理响应后发送
 */
public class RpcResponse {

    private String requestId;  //响应id
    private Exception exception;  //异常
    private Object result;  //返回对象

    public boolean hasException() {
        return exception != null;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
