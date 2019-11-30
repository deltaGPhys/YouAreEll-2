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
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
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
    private CloseableHttpClient httpclient;

    public TransactionController() {
        this.httpclient = HttpClients.createDefault();
    }

    public String MakeURLCall(String mainurl, String method, String jpayload) {
        try {
            switch (method) {
                case "GET":
                    //System.out.println("********************" + mainurl + "********************");
                    return get(mainurl);
                case "POST":
                    //System.out.println("********************" + mainurl + "********************");
                    return post(mainurl,jpayload);
                case "PUT":
                    //System.out.println("********************" + mainurl + "********************");
                    return put(mainurl,jpayload);
            }
        } catch (IOException e) {
            return e.getMessage();
        }
        return "Nothing returned";
    }

    public String get(String url) throws IOException {
        HttpGet httpGet = new HttpGet(this.rootURL + url);
        CloseableHttpResponse response1 = this.httpclient.execute(httpGet);

        try {
            HttpEntity entity1 = response1.getEntity();
            String result = new BufferedReader(new InputStreamReader(entity1.getContent()))
                    .lines().collect(Collectors.joining("\n"));

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
        httpPost.setEntity(new StringEntity(content));
        CloseableHttpResponse response2 = this.httpclient.execute(httpPost);
        String response = "";
        try {
            System.out.println("Response");
            System.out.println(response2.getStatusLine());
            System.out.println("Response");
            HttpEntity entity2 = response2.getEntity();
            response = EntityUtils.toString(entity2);
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
        }
        return response;
    }

    public String put(String url, String content) throws IOException {
        System.out.println(content);

        HttpPut httpPut = new HttpPut(this.rootURL + url);
        httpPut.setEntity(new StringEntity(content));
        CloseableHttpResponse response2 = this.httpclient.execute(httpPut);
        String response = "";
        try {
            System.out.println("Response");
            System.out.println(response2.getStatusLine());
            System.out.println("Response");
            HttpEntity entity2 = response2.getEntity();
            response = EntityUtils.toString(entity2);
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
        }
        return response;
    }
}
