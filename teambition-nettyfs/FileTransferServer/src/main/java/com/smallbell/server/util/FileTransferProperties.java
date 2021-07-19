package com.smallbell.server.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

/**
 * �����ļ�����
 */
public class FileTransferProperties
{
    private static Properties pro = new Properties();

    /**
     * ������Դ
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
     * ��Properties��ȡֵ
     * @param key 
     * @param defaultValue Ĭ��ֵ
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
     * ��Properties��ȡֵ
     * @param key
     * @return
     */
    public static String getString(String key)
    {
        String value = (String) pro.get(key);
        return value;
    }

    /**
     * ��Properties��ȡֵ
     * @param key 
     * @param defaultValue Ĭ��ֵ
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
     * ��Properties��ȡֵ
     * @param key 
     * @param defaultValue Ĭ��ֵ
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
