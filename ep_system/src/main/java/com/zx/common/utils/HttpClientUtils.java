package com.zx.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * HttpClient 工具类
 * 
 * @author liguorui
 */
public class HttpClientUtils {

    /**
     * 用户代理类型
     */
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0";

    /**
     * http客户短
     */
    private static HttpClient httpClient;

    /**
     * 单个主机最大连接数
     */
    private static final int CONNS_PER_HOST = 100;

    /**
     * 读取超时
     */
    private static final int CONN_READ_TIMEOUT = 30000;

    /**
     * 连接超时
     */
    private static final int CONN_TIMEOUT = 30000;

    /**
     * 连接管理池的超时时长
     */
    private static final int CONN_FETCH_TIMEOUT = 30000;

    /**
     * 基础配置
     */
    static {
        HttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = httpConnectionManager.getParams();
        params.setDefaultMaxConnectionsPerHost(CONNS_PER_HOST);
        params.setMaxTotalConnections(Integer.MAX_VALUE);
        params.setConnectionTimeout(CONN_TIMEOUT);
        params.setSoTimeout(CONN_READ_TIMEOUT);
        httpClient = new HttpClient(httpConnectionManager);
        httpClient.getParams().setConnectionManagerTimeout(CONN_FETCH_TIMEOUT);
    }

    /**
     * 接受参数的get请求
     * 
     * @param url 请求地址
     * @param map 参数map
     * @return 返回请求结果
     */
    public static String doGet(String url, Map<String, String> map){
        if(map != null && map.size() > 0) {
            Set<Entry<String, String>> set = map.entrySet();
            if (map != null && map.size() > 0) {
                StringBuilder sb = new StringBuilder("?");
                for (Entry<String, String> entry : set) {
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(entry.getValue());
                    sb.append("&");
                }
                sb.deleteCharAt(sb.length() - 1);
                url += sb;
            }
        }
        GetMethod get = getGettMethod(url);
        get.addRequestHeader("Accept", "text/html");
        String response = null;
        try {
            if (HttpServletResponse.SC_OK == httpClient.executeMethod(get)) {
                response = get.getResponseBodyAsString();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            get.releaseConnection();
        }
        return response;
    }

    /**
     * 接受参数的get请求
     * 
     * @param url 请求地址
     * @param map 参数map
     * @return 返回请求结果
     */
    public static String doGet(String url, Map<String, String> map, String jsessionId){
        if(map != null && map.size() > 0) {
            Set<Entry<String, String>> set = map.entrySet();
            if (map != null && map.size() > 0) {
                StringBuilder sb = new StringBuilder("?");
                for (Entry<String, String> entry : set) {
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(entry.getValue());
                    sb.append("&");
                }
                sb.deleteCharAt(sb.length() - 1);
                url += sb;
            }
        }
        GetMethod get = getGettMethod(url);
        get.addRequestHeader("Cookie", "JSESSIONID="+jsessionId);
        get.addRequestHeader("Accept", "text/html");
        String response = null;
        try {
            if (HttpServletResponse.SC_OK == httpClient.executeMethod(get)) {
                response = get.getResponseBodyAsString();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            get.releaseConnection();
        }
        return response;
    }
    
    /**
     * 发送get请求
     * 
     * @param url 请求地址
     * @return 返回byte[]
     */
    public static byte[] doGet(String url){
        GetMethod get = getGettMethod(url);

//        get.addRequestHeader("Accept-Encoding", "gzip,deflate");
//        get.addRequestHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
//        get.addRequestHeader("Connection", "keep-alive");

        byte[] response = null;
        try {
            if (HttpServletResponse.SC_OK == httpClient.executeMethod(get)) {
                response = get.getResponseBody();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            get.releaseConnection();
        }
        return response;
    }

    /**
     * 发送带参数的post请求
     * 
     * @param url 请求地址
     * @param params 参数map
     * @return 响应结果
     */
    public static String doPost(String url, Map<String, String> params){
        HttpClient client = getClient(url);
        PostMethod post = getPostMethod(url);
        post.setRequestHeader("Accept", "application/json");
        if(params != null && params.size() > 0) {
            Set<Entry<String, String>> set = params.entrySet();
            for (Entry<String, String> entry : set) {
                post.addParameter(entry.getKey(), entry.getValue());
            }
        }
        String response = null;
        try {
            client.executeMethod(post);
            response = post.getResponseBodyAsString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            post.releaseConnection();
        }
        return response;
    }

    /**
     * 发送携带json参数的post请求
     * 
     * @param url 请求地址
     * @param jsonStr json数据
     * @return 响应结果
     */
    public static String doPostByJson(String url, String jsonStr){
        PostMethod post = new PostMethod(url);
        String response = null;
        try {
            RequestEntity requestEntity = new StringRequestEntity(jsonStr, "text/xml", "UTF-8");
            post.setRequestEntity(requestEntity);
            post.setRequestHeader("Accept", "application/json");
            if (HttpServletResponse.SC_OK == httpClient.executeMethod(post)) {
                response = post.getResponseBodyAsString();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            post.releaseConnection();
        }
        return response;
    }

    /**
     * 获取http客户端
     * @param url 请求地址
     * @return Http客户端
     * @see
     * @since 1.0
     */
    private static HttpClient getClient(String url) {
        HttpClient client = httpClient;
        client.getHostConfiguration().setHost(url);
        return client;
    }

    /**
     * 获取get方法
     * 
     * @param url 请求地址
     * @return get方法
     * @see
     * @since 1.0
     */
    private static GetMethod getGettMethod(String url) {
        GetMethod get = new GetMethod(url);
        get.setRequestHeader("User-Agent", USER_AGENT);
        return get;

    }

    /**
     * 获取post方法
     * 
     * @param url 请求地址
     * @return post方法
     * @see
     * @since 1.0
     */
    private static PostMethod getPostMethod(String url) {
        PostMethod post = new PostMethod(url);
        post.setRequestHeader("User-Agent", USER_AGENT);
        return post;
    }

    /**
     * post发送文件
     * 
     * @param url 请求地址
     * @param file 文件
     * @return 响应结果
     */
    public static String postFile(String url, File file) {
        try {
            if (file.exists()) {
                PostMethod post = getPostMethod(url);
                FilePart part = new FilePart("file", file);
                Part[] pts = {part};
                post.setRequestEntity(new MultipartRequestEntity(pts, new HttpMethodParams()));
                String response = null;
                if (HttpServletResponse.SC_OK == httpClient.executeMethod(post)) {
                    response = post.getResponseBodyAsString();
                }
                return response;
            }
            else {
                throw new Exception("file does not exist");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 云存储上传入口
     * @author wuzhangshan
     * @date 2017-11-6
     * @param uplaodhost 上传接口
     * @param file 文件
     * @param filename 服务器文件名
     * @return
     * @throws IOException
     */
	public static String postFile(String uplaodhost, String filename, MultipartFile file) throws IOException {
		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    	builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, filename);// 文件流
        HttpEntity entity = builder.build();
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uplaodhost);
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);// 执行提交
        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            // 将响应内容转换为字符串
            String result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            
            return result;
        }
        
		return null;
	}
	
	/**
     * 云存储上传入口
     * @author wuzhangshan
     * @date 2017-11-6
     * @param uplaodhost 上传接口
     * @param file 文件
     * @param filename 服务器文件名
     * @return
     * @throws IOException
     */
	public static String postFile(String uplaodhost, String filename, File file) throws IOException {
		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    	builder.addBinaryBody("file", new FileInputStream(file), ContentType.MULTIPART_FORM_DATA, filename);// 文件流
        HttpEntity entity = builder.build();
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uplaodhost);
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);// 执行提交
        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            // 将响应内容转换为字符串
            String result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            
            return result;
        }
        
		return null;
	}
}