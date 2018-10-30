package com.zx.xt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icbc.crypto.utils.Base64;
import com.icbc.crypto.utils.RSA;
import com.zx.common.annotation.NotAuthPassport;

@Controller
@RequestMapping("/connect")
public class ConnectController {

	@NotAuthPassport
	@RequestMapping(value="/get")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        PrintWriter out = null;
        try {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        //获取服务平台发来的时间原始值
        String timestamp = request.getParameter("timestamp");
        //获取服务平台发来的时间加密值
        String signature= request.getParameter("signature");
        //进行urldecode处理
        signature = URLDecoder.decode(signature,"utf-8");
        //进行base64 decode处理
        byte[] base64Data = Base64.icbcbase64decode(signature);
        //采用服务平台提供的公钥对秘文进行解密
        byte[] deData=
        decryptByPublicKey(base64Data,"E:\\platform_pub.key");
        //获取得到解密后的时间值
        signature = new String(deData);
        //将解密值与原始值进行比对
        if(signature.equals(timestamp)){
            //解密后的信息采用自身的私钥进行加密处理
            byte[] enData = encryptByPrivateKey(deData, "E:\\priv.key");
            out = response.getWriter();
            //将加密得到的秘文base64 encode后回传给服务器
            out.write(Base64.icbcbase64encode(enData));
            out.flush();
            }
        } catch (Exception e) {
        }
    }
    
    /**
     * 私钥加密
     * @param data 待加密数据
     * @param path 私钥密钥文件路径
     * @return byte[] 加密数据
     * @throws Exception 
     */
     public static byte[] encryptByPrivateKey(byte[] data, String path) throws Exception {
         String base64Text = Base64.icbcbase64encode(data);
         return RSA.icbcRsaPriEn(base64Text.getBytes(), path);
     }
 	
     /**
      * 公钥解密
      * @param data 待解密数据
      * @param path 公钥存放路径
      * @return byte[] 解密数据
      * @throws Exception
      */
     public static byte[] decryptByPublicKey(byte[] data, String path) throws Exception {
         byte[] plainText = RSA.icbcRsaPubDe(data, path);
         return Base64.icbcbase64decode(new String(plainText));
     }
}
