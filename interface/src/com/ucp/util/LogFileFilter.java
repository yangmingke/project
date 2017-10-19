package com.ucp.util;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.commons.lang.StringUtils;
public class LogFileFilter implements FilenameFilter
{
    /**
     * 是否是正常的文件
     * @param fileName
     * @return
     */
    public boolean isNormalFile(String fileName)
    {
        if (StringUtils.isEmpty(fileName))
        {
            return false;
        }
        if(fileName.matches("\\d{12}")){
            return true;
        }
        return false;
    }

    /**
     * @param dir
     * @param name
     * @return
     */
    @Override
    public boolean accept(File dir, String name)
    {
        return isNormalFile(name);
    }

}
