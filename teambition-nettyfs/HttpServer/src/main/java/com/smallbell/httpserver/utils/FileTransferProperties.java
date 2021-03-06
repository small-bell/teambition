package com.smallbell.httpserver.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

/**
 * 配置文件加载
 */
public class FileTransferProperties
{
    private static Properties pro = new Properties();

    /**
     * 加载资源
     * @param path
     */
    public static void load(String path)
    {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        Resource resource = fileSystemResourceLoader.getResource(path);
        try
        {
            pro.load(resource.getInputStream());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 从Properties中取值
     * @param key 
     * @param defaultValue 默认值
     * @return
     */
    public static String getString(String key, String defaultValue)
    {
        String value = (String) pro.get(key);
        if (StringUtils.isEmpty(value))
        {
            return defaultValue;
        }
        return value;
    }
    
    /**
     * 从Properties中取值
     * @param key
     * @return
     */
    public static String getString(String key)
    {
        String value = (String) pro.get(key);
        return value;
    }

    /**
     * 从Properties中取值
     * @param key 
     * @param defaultValue 默认值
     * @return
     */
    public static int getInt(String key, int defaultValue)
    {
        Object value = pro.get(key);
        if (StringUtils.isEmpty(value))
        {
            return defaultValue;
        }
        return Integer.parseInt(value.toString());
    }

    /**
     * 从Properties中取值
     * @param key 
     * @param defaultValue 默认值
     * @return
     */
    public static boolean getBoolean(String key, boolean defaultValue)
    {
        Object value = (Boolean) pro.get(key);
        if (StringUtils.isEmpty(value))
        {
            return defaultValue;
        }
        return Boolean.parseBoolean(value.toString());
    }
}
