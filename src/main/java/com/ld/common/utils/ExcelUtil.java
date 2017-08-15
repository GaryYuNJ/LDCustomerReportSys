package com.ld.common.utils;

import java.util.*;
import org.springframework.web.multipart.*;
import java.lang.reflect.*;
import org.apache.poi.hssf.usermodel.*;
import javax.servlet.http.*;
import java.io.*;

public class ExcelUtil<T>
{
    public List<T> prepareData(final String[] column_en, final Class<T> clazz, final HttpServletRequest request) {
        final MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest)request;
        final List<T> prepareData = new ArrayList<T>();
        final MultipartFile file = mrequest.getFile("upfile");
        InputStream in = null;
        try {
            in = file.getInputStream();
            final HSSFWorkbook hssfworkbook = new HSSFWorkbook(in);
            for (int SheetNum = 0; SheetNum < hssfworkbook.getNumberOfSheets(); ++SheetNum) {
                final HSSFSheet hssfsheet = hssfworkbook.getSheetAt(SheetNum);
                if (hssfsheet != null) {
                    T entity = null;
                    for (int rowNum = 1; rowNum <= hssfsheet.getLastRowNum(); ++rowNum) {
                        final HSSFRow hssfrow = hssfsheet.getRow(rowNum);
                        if (hssfrow != null) {
                            entity = clazz.newInstance();
                            for (int index = 0; index < column_en.length; ++index) {
                                try {
                                    final Class[] parameterTypes = { null };
                                    final Field field = clazz.getDeclaredField(column_en[index]);
                                    parameterTypes[0] = field.getType();
                                    final Method m = clazz.getMethod("set" + column_en[index].substring(0, 1).toUpperCase() + column_en[index].substring(1), (Class<?>[])parameterTypes);
                                    final HSSFCell cell = hssfrow.getCell(index);
                                    cell.setCellType(1);
                                    final String value = cell.getStringCellValue().trim();
                                    m.invoke(entity, value);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            prepareData.add(entity);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            try {
                in.close();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        finally {
            try {
                in.close();
            }
            catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return prepareData;
    }
    
    public String prepareExcel(final String[] column_en, final String[] column_zh, final String fileName, final List<T> dataList, final Class<T> clazz, final HttpServletResponse response) {
        final HSSFWorkbook bookWorkbook = new HSSFWorkbook();
        final HSSFSheet sheet = bookWorkbook.createSheet("\u7b2c\u4e00\u9875");
        int rowint = 0;
        final int titlerow1 = rowint++;
        HSSFRow row = sheet.createRow((int)(short)titlerow1);
        for (int i = 0; i < column_zh.length; ++i) {
            final HSSFCell cell = row.createCell((int)(short)i);
            cell.setCellValue(column_zh[i]);
        }
        for (int i = 0; i < dataList.size(); ++i) {
            final T oject = dataList.get(i);
            row = sheet.createRow((int)(short)(rowint + i));
            int kk = 0;
            for (final String methodName : column_en) {
                try {
                    final Method m = clazz.getMethod("get" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1), (Class<?>[])new Class[0]);
                    final String value = (String)m.invoke(oject, new Object[0]);
                    final HSSFCell cell = row.createCell((int)(short)kk);
                    cell.setCellValue(value);
                    ++kk;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return exportExcel(response, fileName + ".xls", bookWorkbook);
    }
    
    private static String exportExcel(final HttpServletResponse response, final String fileName, final HSSFWorkbook workbook) {
        response.addHeader("Content-Type", "text/html; charset=utf-8");
        response.setContentType("application/msexcel;charset=UTF-8");
        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        OutputStream fOut = null;
        try {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "ISO8859-1"));
            fOut = (OutputStream)response.getOutputStream();
            workbook.write(fOut);
        }
        catch (UnsupportedEncodingException ex) {}
        catch (Exception ex2) {}
        finally {
            try {
                fOut.flush();
                fOut.close();
            }
            catch (IOException ex3) {}
        }
        return null;
    }
}
