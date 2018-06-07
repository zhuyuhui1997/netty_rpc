package service;

import java.io.File;
import java.util.Date;

public interface LinuxService {
    //查看当前目录
    String pwd();
    //列出当前目录下的文件
    String[] ls();
    //进入某个目录
    boolean cd(String directory);
    //查看某个文件
    byte[] cat(String path);
    //查看服务器时间
    Date date();
}
