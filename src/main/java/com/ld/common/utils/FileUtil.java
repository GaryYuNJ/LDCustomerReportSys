package com.ld.common.utils;

import java.io.*;
import java.util.*;

public class FileUtil
{
    public static void copy(final String src, final String des) {
        final File file1 = new File(src);
        final File[] fs = file1.listFiles();
        final File file2 = new File(des);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        if (!file2.exists()) {
            file2.mkdirs();
        }
        for (final File f : fs) {
            if (f.isFile()) {
                fileCopy(f.getPath(), des + "\\" + f.getName());
            }
            else if (f.isDirectory()) {
                copy(f.getPath(), des + "\\" + f.getName());
            }
        }
    }
    
    public static void fileCopy(final String src, final String des) {
        InputStream inStream = null;
        FileOutputStream fs = null;
        try {
            int bytesum = 0;
            int byteread = 0;
            inStream = new FileInputStream(src);
            fs = new FileOutputStream(des);
            final byte[] buffer = new byte[1444];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
            fs.flush();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
                if (fs != null) {
                    fs.close();
                }
            }
            catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }
    
    public static String getProperties(final String fileNamePath, final String key) {
        final Properties props = new Properties();
        InputStream in = null;
        try {
            in = FileUtil.class.getClassLoader().getResourceAsStream(fileNamePath);
            props.load(in);
            final String value = props.getProperty(key);
            return value;
        }
        catch (IOException e) {
            return null;
        }
        finally {
            if (null != in) {
                try {
                    in.close();
                }
                catch (IOException ex) {}
            }
        }
    }
    
    public static void main(final String[] args) {
        System.out.println("\u6587\u4ef6\u62f7\u8d1d\u5f00\u59cb!");
        copy("F:\\360WiFi\\aaaa", "F:\\360WiFi\\bbbb");
        System.out.println("\u6587\u4ef6\u62f7\u8d1d\u5b8c\u6210!");
    }
}
