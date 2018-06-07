package service;

import server.RpcService;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

@RpcService(value = LinuxService.class)
public class LinuxServiceImpl implements LinuxService {
    private File currentDirectory = new File("F:\\test");
    @Override
    public String pwd() {
        String path = currentDirectory.getPath();
        int index = path.indexOf("test");
        System.out.println(path);
        // 去掉test\
        String prefix = path.substring(index + 4, path.length());
        System.out.println(prefix);
        return "root" + prefix;
    }

    @Override
    public String[] ls() {
        return currentDirectory.list();
    }

    @Override
    public boolean cd(String directory) {
        for (String s : currentDirectory.list()) {
            if (s.equals(directory)) {
                currentDirectory = new File(currentDirectory.getPath() + "\\" + directory);
                return true;
            }
        }
        return false;
    }

    @Override
    public byte[] cat(String path) {
        boolean flag = false;
        for (String s : currentDirectory.list()) {
            if (s.equals(path)) {
                flag = true;
            }
        }
        if (flag) {
            File file = new File(currentDirectory.getPath() + "\\" + path);
            long length = file.length();
            byte[] bytes = new byte[(int)length + 1];
            String s = System.getProperty("file.encoding");
            System.out.println(s);
            if (s.equalsIgnoreCase("utf-8") || s.equalsIgnoreCase("utf8"))
                bytes[0] = 1; //utf-8编码
            else
                bytes[1] = 0; //gbk编码
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                //System.out.println(file.length());
                while (fileInputStream.available() != 0) {
                    fileInputStream.read(bytes);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bytes;
        }
        return new byte[0];

    }

    public Date date() {
        return new Date();
    }
}
