package com.Y3.AnalyticsTeam.CT.util;

import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpHelp {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpHelp.class);

    /***
     * post方式
     *
     * @param url
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, String> maps, String encoding) throws Exception {

        String responseStr = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(url);// 创建httpPost

            LOGGER.debug("### Executing request -> " + httpPost.getRequestLine());

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(); // 参数处理
            for (String key : maps.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
            }
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, encoding));
            } catch (Exception e) {
                LOGGER.error("### HttpHelp.doPost->setEntity Exception!", e);
            }

            CloseableHttpResponse response = httpclient.execute(httpPost);
            LOGGER.debug("### ->"+response.getStatusLine());
            try {
                responseStr = EntityUtils.toString(response.getEntity());
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }

        return responseStr;

    }

    /***
     * get方式
     *
     * @param url
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String doGet(String url, String encoding) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);

            LOGGER.debug("Executing request " + httpget.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                LOGGER.debug("### " + response.getStatusLine().toString());
                return EntityUtils.toString(response.getEntity());
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }

    }

    /***
     * post方式，使用代理
     *
     * @param url
     * @param maps
     * @param proxyHost
     * @param proxyPort
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String doPostOnProxy(String url, Map<String, String> maps, String proxyHost, Integer proxyPort,
                                       String encoding) throws Exception {

        String responseStr = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // proxy setting
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);
            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

            HttpPost httpPost = new HttpPost(url);// 创建httpPost
            httpPost.setConfig(config); // use proxy setting
            LOGGER.debug("Executing request " + httpPost.getRequestLine() + " to " + " via " + proxy);

            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                LOGGER.debug(response.getStatusLine().toString());
                return EntityUtils.toString(response.getEntity());
            } finally {
                response.close();
            }

        } finally {
            httpclient.close();
        }
    }

    /***
     * get方式，通过代理请求
     *
     * @param url
     * @param proxyHost
     * @param proxyPort
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String doGetOnProxy(String url, String proxyHost, int proxyPort, String encoding) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);

            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            HttpGet httpget = new HttpGet(url);
            httpget.setConfig(config);

            LOGGER.debug("Executing request " + httpget.getRequestLine() + " to " + " via " + proxy);

            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                LOGGER.debug(response.getStatusLine().toString());
                return EntityUtils.toString(response.getEntity());
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }

    }

    /***
     * post方式，使用代理并有验证
     * @param url
     * @param maps
     * @param proxyHost
     * @param proxyPort
     * @param user
     * @param passwd
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String doPostOnProxyWithAuth(String url, Map<String, String> maps, String proxyHost,
                                               Integer proxyPort, String user, String passwd, String encoding) throws Exception {

        String responseStr = "";

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(proxyHost, proxyPort),
                new UsernamePasswordCredentials(user, passwd));

        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        try {
            // proxy setting
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);
            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

            HttpPost httpPost = new HttpPost(url);// 创建httpPost
            httpPost.setConfig(config); // use proxy setting
            LOGGER.debug("Executing request " + httpPost.getRequestLine() + " to " + " via " + proxy);

            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                LOGGER.info(response.getStatusLine().toString());
                return EntityUtils.toString(response.getEntity());
            } finally {
                response.close();
            }

        } finally {
            httpclient.close();
        }
    }

    /***
     * get方式，使用代理并有验证
     * @param url
     * @param proxyHost
     * @param proxyPort
     * @param user
     * @param passwd
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String doGetOnProxyWithAuth(String url, String proxyHost, int proxyPort,String user,String passwd, String encoding)
            throws Exception {

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(proxyHost, proxyPort),
                new UsernamePasswordCredentials(user, passwd));

        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        try {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);

            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            HttpGet httpget = new HttpGet(url);
            httpget.setConfig(config);

            LOGGER.debug("Executing request " + httpget.getRequestLine() + " to " + " via " + proxy);

            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                LOGGER.debug(response.getStatusLine().toString());
                return EntityUtils.toString(response.getEntity());
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }

    }

}
