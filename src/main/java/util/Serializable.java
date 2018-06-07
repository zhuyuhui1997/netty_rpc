package util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
* 序列化工具,使用protostuff
* */
public class Serializable {
    //缓存,并发map
    private static Map<Class<?>, Schema<?>> cacheMap = new ConcurrentHashMap<Class<?>, Schema<?>>();
    //将对象序列化成字节
    public static <T> byte[] serialize(T object) {

        Class<T> tClass = (Class<T>) object.getClass();
        LinkedBuffer linkedBuffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            //从缓存中获取
            Schema<T> schema = (Schema<T>) cacheMap.get(tClass);
            if (schema == null) {
                schema = RuntimeSchema.createFrom(tClass);
                cacheMap.put(tClass, schema);
            }
            return ProtobufIOUtil.toByteArray(object, schema, linkedBuffer);

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage(), e);
        }
        finally {
            linkedBuffer.clear();
        }
    }

    /**
     * 反序列化,根据字节数组获取对象
     * @param data
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] data, Class<T> tClass) {
        Schema<T> schema = (Schema<T>) cacheMap.get(tClass);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(tClass);
            cacheMap.put(tClass, schema);
        }
        T message = schema.newMessage();
        ProtobufIOUtil.mergeFrom(data, message, schema);
        return message;

    }
}
