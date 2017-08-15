package com.ld.common.utils;

import org.apache.log4j.*;
import java.util.*;
import java.net.*;
import org.apache.commons.codec.digest.*;
import java.io.*;

public class MessageToolUtil
{
    private static Logger logger;
    
    public static void sendMessage(final String content, String receiver) {
        try {
            final String httpUrl = FileUtil.getProperties("config.properties", "SMS_SEND_URL");
            final String TOKEN_KEY = FileUtil.getProperties("config.properties", "SMS_SEND_TOKEN_KEY");
            receiver = FileUtil.getProperties("config.properties", "SMS_SEND_MOBILE");
            System.out.println("**********************************");
            System.out.println("httpUrl[" + httpUrl + "]");
            System.out.println("TOKEN_KEY[" + TOKEN_KEY + "]");
            System.out.println("receiver[" + receiver + "]");
            System.out.println("**********************************");
            if (StringUtils.isNotBlank(receiver)) {
                final String[] split;
                final String[] receiverList = split = receiver.split(",");
                for (final String s : split) {
                    send(content, s, httpUrl, TOKEN_KEY);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String send(final String content, final String receiver, final String httpUrl, final String TOKEN_KEY) {
        BufferedReader reader = null;
        String result = null;
        final StringBuffer sbf = new StringBuffer();
        try {
            final URL url = new URL(httpUrl);
            final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            final StringBuffer params = new StringBuffer();
            final String timestamp = String.valueOf(new Date().getTime());
            params.append("timestamp").append("=").append(timestamp);
            params.append("&content").append("=").append(URLEncoder.encode(content, "UTF-8").toString());
            params.append("&receiver").append("=").append(receiver);
            params.append("&token").append("=").append(DigestUtils.md5Hex(TOKEN_KEY + timestamp));
            final DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(params.toString());
            out.flush();
            out.close();
            connection.connect();
            final InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    static {
        MessageToolUtil.logger = Logger.getLogger((Class)MessageToolUtil.class);
    }
}
