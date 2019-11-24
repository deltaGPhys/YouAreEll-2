package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import models.Id;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085/";
    private static final TransactionController INSTANCE = new TransactionController();

    private TransactionController() {
    }

    public static TransactionController getInstance() {
        return INSTANCE;
    }

    CloseableHttpClient httpclient = HttpClients.createDefault();

    public String get(String url) throws IOException {
        HttpGet httpGet = new HttpGet(this.rootURL + url);
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        // The underlying HTTP connection is still held by the response object
        // to allow the response content to be streamed directly from the network socket.
        // In order to ensure correct deallocation of system resources
        // the user MUST call CloseableHttpResponse#close() from a finally clause.
        // Please note that if response content is not fully consumed the underlying
        // connection cannot be safely re-used and will be shut down and discarded
        // by the connection manager.
        try {
            //System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            String response = entity1.getContent().toString();
            String result = new BufferedReader(new InputStreamReader(entity1.getContent()))
                    .lines().collect(Collectors.joining("\n"));
//            System.out.println(result);
//            System.out.println(response);

            EntityUtils.consume(entity1);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response1.close();
        }
        return null;
    }

    public String post(String url, String content) throws IOException {
        System.out.println(content);

        HttpPost httpPost = new HttpPost(this.rootURL + url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("username", "vip"));
//        nvps.add(new BasicNameValuePair("password", "secret"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response2 = httpclient.execute(httpPost);

        try {
            System.out.println("Response");
            System.out.println(response2.getStatusLine());
            System.out.println("Response");
            HttpEntity entity2 = response2.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
        }
        return "";
    }
}
