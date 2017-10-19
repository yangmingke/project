package com.ucp.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.tools.AssertUtil;
import com.tools.ConverUtil;

/**
 * 
 * @author duanyj
 */
public class SysConfig
{
    private Properties props = null;// config.properties
    private static Logger log = Logger.getLogger(SysConfig.class);
    private static volatile SysConfig conf;

    private SysConfig()
    {
        props = new Properties();
        loadConfigProps();
    }

    public static SysConfig getInstance()
    {
        if (conf == null)
        {
            synchronized (SysConfig.class)
            {
                if (conf == null)
                {
                    conf = new SysConfig();
                }
            }
        }
        return conf;
    }

    public void loadConfigProps()
    {
        InputStream is = null;
        try
        {
            is = getClass().getResourceAsStream(
                "/WEB-INF/classes/config.properties");
            if (is == null)
            {
                is = getClass().getResourceAsStream("/config.properties");
            }
            InputStreamReader reader = new InputStreamReader(is, "UTF-8");
            props.load(reader);
            Iterator<String> iter = props.stringPropertyNames().iterator();
            while (iter.hasNext())
            {
                String key = iter.next();
                props.setProperty(key, props.getProperty(key));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            log.error("load config.properties error!please check the file!", e);
        }
        finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                    is = null;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getProperty(String key)
    {
        String tmp = props.getProperty(key);
        if (AssertUtil.isNotEmpty(tmp))
        {
            return tmp.trim();
        }
        return tmp;
    }

    public String getProperty(String key, String defaultValue)
    {
        String tmp = props.getProperty(key, defaultValue);
        if (AssertUtil.isNotEmpty(tmp))
        {
            return tmp.trim();
        }
        return tmp;
    }

    public int getPropertyInt(String key)
    {
        String tmp = props.getProperty(key);
        if (AssertUtil.isNotEmpty(tmp))
        {
            return ConverUtil.string2Int(tmp.trim());
        }
        return 0;

    }

    public int getPropertyInt(String key, int defaultValue)
    {
        String tmp = props.getProperty(key);
        if (AssertUtil.isNotEmpty(tmp))
        {
            return ConverUtil.string2Int(tmp.trim(), defaultValue);
        }
        return defaultValue;
    }

    public long getPropertyLong(String key, long defaultValue)
    {
        String tmp = props.getProperty(key);
        if (AssertUtil.isNotEmpty(tmp))
        {
            return ConverUtil.string2Long(tmp.trim(), defaultValue);
        }
        return defaultValue;
    }
}