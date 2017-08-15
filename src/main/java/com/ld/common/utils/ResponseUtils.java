package com.ld.common.utils;

import javax.servlet.http.*;
import java.io.*;
import org.slf4j.*;

public final class ResponseUtils
{
    public static final Logger log;
    
    public static void renderT(final HttpServletResponse response, final String text) {
        render(response, "text/html;charset=UTF-8", text);
    }
    
    public static void renderText(final HttpServletResponse response, final String text) {
        render(response, "text/plain;charset=UTF-8", text);
    }
    
    public static void renderJson(final HttpServletResponse response, final String text) {
        render(response, "application/json;charset=UTF-8", text);
    }
    
    public static void renderXml(final HttpServletResponse response, final String text) {
        render(response, "text/xml;charset=UTF-8", text);
    }
    
    public static void render(final HttpServletResponse response, final String contentType, final String text) {
        response.setContentType(contentType);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        try {
            response.getWriter().write(text);
        }
        catch (IOException e) {
            ResponseUtils.log.error(e.getMessage(), (Throwable)e);
        }
    }
    
    static {
        log = LoggerFactory.getLogger((Class)ResponseUtils.class);
    }
}
