package com.tdd.io;

import java.io.InputStream;

/**
 * @author tudedong
 * @description 加载配置文件的资源类
 * @date 2020-04-21 21:18:17
 */
public class Resources {

    /**
     * 根据配置文件的路径，将配置文件加载成字节输入流，并存储在内存中
     * @param path 配置文件的路径
     * @return InputStream
     */
    public static InputStream getResourceAsSteam(String path) {
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }

}
