package com.example.testunit.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;

/**
 * @Despriction: base64Util
 * @Author: wangcheng
 * @Date: 2018/2/28 15:23
 */
public class Base64Util {

    /**
     * 将图片文件转化为字节数组字符串,再进行Base64编码处理
     * @param imgFilePath
     * @return
     */
    public static String imgToBase64FromFilePath(String imgFilePath) {
        byte[] data = null;

        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串,并删除其中的换行符 (window系统为回车+换行:\r\n, linux为换行:\n)
        return encoder.encode(data).replaceAll("\n","");
    }

    /**
     * 图片文件转换为base64,将图片文件用ImageIO转换后再进行Base64编码处理
     * @param imageFile
     * @return
     */
    public static String imgToBase64FromFile(File imageFile) {
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            //logger.error("Base64加密失败",e);
        } catch (IOException e) {
            //logger.error("Base64加密失败",e);
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串,并删除其中的换行符 (window系统为回车+换行:\r\n, linux为换行:\n)
        return encoder.encode(outputStream.toByteArray()).replaceAll("\n","");
    }

    /**
     * Base64转换为byte数组
     * @param base64Str
     * @return
     */
    public static byte[] base64ToByteArr(String base64Str) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(base64Str);
            return bytes;
        } catch (IOException e) {
            //logger.error("Base64解密失败",e);
        }
        return null;
    }

    /**
     * base64格式图片使用ImageIO转换后再转为base64
     * @param imgBase64
     * @return
     */
    public static String imgBase64Transform(String imgBase64){
        String finalBase64 = null;
        ByteArrayInputStream in = null;
        ByteArrayOutputStream outputStream = null;
        try{
            //base64格式图片转换为流
            BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(imgBase64);
            in = new ByteArrayInputStream(bytes);
            //流转换为jpg格式
            BufferedImage bt1 = ImageIO.read(in);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bt1,"jpg",outputStream);
            //转换为base64
            byte[] data = outputStream.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            finalBase64 = encoder.encode(data);
            //删除其中的换行符 (window系统为回车+换行:\r\n, linux为换行:\n)
            finalBase64 = finalBase64.replaceAll("\n","");
        }catch (Exception e){
        }finally {
            try {
                if (in != null){
                    in.close();
                }
                if (outputStream != null){
                    outputStream.close();
                }
            }catch (Exception e){
            }
        }
        return finalBase64;
    }
}
