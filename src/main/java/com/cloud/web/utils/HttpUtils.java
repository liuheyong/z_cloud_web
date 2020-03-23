//package com.cloud.web.utils;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.net.ssl.*;
//import java.io.*;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
///**
// * @author: HeYongLiu
// * @create: 12-09-2019
// **/
//public class HttpUtils {
//
//    protected static Logger log = LoggerFactory.getLogger(HttpUtils.class);
//
//    public HttpUtils() {
//    }
//
//    private static RequestConfig requestConfig;
//
//    public static String postHttps(String url, Map<String, String> params) {
//        HttpsURLConnection conn = null;
//        try {
//            String postParams = "";
//            Entry entry;
//            for (Iterator var4 = params.entrySet().iterator(); var4.hasNext(); postParams =
//                    postParams + (String) entry.getKey() + "=" + (String) entry.getValue() + "&") {
//                entry = (Entry) var4.next();
//            }
//            if (postParams.length() > 0) {
//                postParams = postParams.substring(0, postParams.lastIndexOf("&"));
//            }
//            postParams = postParams.replaceAll("\\+", "%2B");
//            SSLContext context = SSLContext.getInstance("SSL");
//            context.init((KeyManager[]) null, new TrustManager[]{new HttpUtils.TrustAnyTrustManager()},
//                    new SecureRandom());
//            URL curl = new URL(url);
//            conn = (HttpsURLConnection) curl.openConnection();
//            conn.setSSLSocketFactory(context.getSocketFactory());
//            conn.setHostnameVerifier(new HttpUtils.TrustAnyHostnameVerifier());
//            conn.setRequestProperty("Cache-Control", "no-cache");
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            conn.setRequestProperty("Accept-Charset", "utf-8");
//            conn.setDoInput(true);
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//            conn.getOutputStream().write(postParams.getBytes());
//            conn.getOutputStream().flush();
//            conn.getOutputStream().close();
//            conn.connect();
//            int statusCode = conn.getResponseCode();
//            if (200 == statusCode) {
//                InputStream is = conn.getInputStream();
//                BufferedInputStream bis = new BufferedInputStream(is);
//                BufferedReader br = new BufferedReader(new InputStreamReader(bis, Charset.forName("utf-8")));
//                StringBuffer buff = new StringBuffer();
//                String line = null;
//
//                while (null != (line = br.readLine())) {
//                    buff.append(line);
//                }
//                br.close();
//                String var12 = buff.toString();
//                return var12;
//            }
//            log.error("请求状态不为成功statusCode[{" + statusCode + "}]");
//        } catch (IOException var18) {
//            log.error("io异常", var18);
//        } catch (KeyManagementException var19) {
//            log.error("KeyManagementException异常", var19);
//        } catch (NoSuchAlgorithmException var20) {
//            log.error("算法不存在异常", var20);
//        } finally {
//            if (null != conn) {
//                conn.disconnect();
//            }
//        }
//        return null;
//    }
//
//    private static class TrustAnyTrustManager implements X509TrustManager {
//        private TrustAnyTrustManager() {
//        }
//
//        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//        }
//
//        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//        }
//
//        public X509Certificate[] getAcceptedIssuers() {
//            return new X509Certificate[0];
//        }
//    }
//
//    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
//        private TrustAnyHostnameVerifier() {
//        }
//
//        public boolean verify(String hostname, SSLSession session) {
//            return true;
//        }
//    }
//
//    public static String doPost(String apiUrl, Map<String, Object> params) throws CoreException {
//        return doPost(apiUrl, params, "UTF-8");
//    }
//
//    public static String doPost(String apiUrl, Map<String, Object> params, String charset) throws CoreException {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        String httpStr = null;
//        HttpPost httpPost = new HttpPost(apiUrl);
//        CloseableHttpResponse response = null;
//        try {
//            httpPost.setConfig(requestConfig);
//            List<NameValuePair> pairList = new ArrayList(params.size());
//            Iterator var8 = params.entrySet().iterator();
//
//            while (var8.hasNext()) {
//                Entry<String, Object> entry = (Entry) var8.next();
//                NameValuePair pair = new BasicNameValuePair((String) entry.getKey(), entry.getValue().toString());
//                pairList.add(pair);
//            }
//
//            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(charset)));
//            response = httpClient.execute(httpPost);
//            log.info("status code : {}", response.getStatusLine().getStatusCode());
//            HttpEntity entity = response.getEntity();
//            httpStr = EntityUtils.toString(entity, charset);
//            return httpStr;
//        } catch (Exception var18) {
//            log.error("请求失败", var18);
//            throw new CoreException("请求失败", var18);
//        } finally {
//            if (response != null) {
//                try {
//                    EntityUtils.consume(response.getEntity());
//                    response.close();
//                    httpClient.close();
//                } catch (IOException var17) {
//                    var17.printStackTrace();
//                }
//            }
//
//        }
//    }

//public static String doPost(String url, JSONObject json, String charset) {
//        HttpClient httpClient = null;
//        HttpPost httpPost = null;
//        String result = null;
//        try {
//        httpClient = new SSLClient();
//        httpPost = new HttpPost(url);
//        // 设置参数
//        StringEntity s = new StringEntity(json.toString());
//        s.setContentEncoding("UTF-8");
//        s.setContentType("application/json");
//        httpPost.setEntity(s);
//        HttpResponse response = httpClient.execute(httpPost);
//        if (response != null) {
//        HttpEntity resEntity = response.getEntity();
//        if (resEntity != null) {
//        result = EntityUtils.toString(resEntity, charset);
//        }
//        }
//        } catch (Exception ex) {
//        logger.error("https 请求实时代付API异常", ex);
//        }
//        return result;
//        }
//
//}
