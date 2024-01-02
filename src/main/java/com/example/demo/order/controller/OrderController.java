package com.example.demo.order.controller;


import com.example.demo.product.model.Product;
import com.example.demo.product.repository.ProductRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonToken;
import lombok.SneakyThrows;
import org.apache.coyote.Response;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.data.util.Optionals;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    ProductRepository productRepository;

    public OrderController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String getToken() throws IOException {
        HttpsURLConnection conn = null;

        URL url = new URL("https://api.iamport.kr/users/getToken");
        conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        JsonObject json = new JsonObject();
        json.addProperty("imp_key", "7166480510824686");
        json.addProperty("imp_secret", "nbqRZyrFl8HTrU6G6NXMfkzKgDFZZZeQzVdhhtg4iBd5AzReLFB8aU7p6c1Mp9Rw8GmB8Klg5RSj0poR");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(json.toString());
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        Gson gson = new Gson();
        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();

        String toekn = gson.fromJson(response, Map.class).get("access_token").toString();
        br.close();
        conn.disconnect();

        return toekn;
    }


    public Map<String, String> getPaymentInfo(String imUid) throws IOException {
        String token = getToken();
        HttpsURLConnection conn = null;
        URL url = new URL("https://api.iamport.kr/payments/" + imUid);
        conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        conn.setRequestProperty("Authorization", token);
        conn.setDoOutput(true);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        Gson gson = new Gson();
        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
        System.out.println(response);
        br.close();
        conn.disconnect();

        String amount = response.split("amount")[1].split(",")[0].replace("=", "");
        System.out.println(response.split("amount")[1]);
        System.out.println(response.split("amount")[1].split(",")[0]);
        System.out.println(response.split("amount")[1].split(",")[0].replace("=", ""));
        String name = response.split(" name")[1].split(",")[0].replace("=", "");
        String customData = response.split("custom_data")[1];
        System.out.println(customData);
        customData = customData.split(", customer_uid")[0];
        System.out.println(customData);
        customData = customData.replace("=", "");
        System.out.println(customData);


        Map<String, String> result = new HashMap<>();
        result.put("name", name);
        result.put("amount", amount);
        result.put("customData", customData);
        return result;
    }

    public void refundRequest(String accesstoken, String imUid, String reason) throws IOException {
        URL url = new URL("https://api.iamport.kr/payments/cancel");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");

        conn.setDoOutput(true);

        JsonObject json = new JsonObject();
        json.addProperty("imUid", imUid);
        json.addProperty("reason", reason);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(json.toString());
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        br.close();
        conn.disconnect();

    }

//    public String getToken(String imp_key, String imp_secret) throws IOException {
//        URL url = new URL("https://api.iamport.kr/users/getToken");
//        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Content-Type", "application/json");
//        conn.setRequestProperty("Accept", "application/json");
//
//        conn.setDoOutput(true);
//
//        JsonObject json = new JsonObject();
//        json.addProperty("imp_key", imp_key);
//        json.addProperty("imp_secret", imp_secret);
//
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//        bw.write(json.toString());
//        bw.flush();
//        bw.close();
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        Gson gson = new Gson();
//        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
//        String accessToken = gson.fromJson(response, Map.class).get("access_token").toString();
//        br.close();
//
//        conn.disconnect();
//
//        log.info("Iamport 엑세스 토큰 발급 성공 : {}", accessToken);
//        return accessToken;
//    }


    @RequestMapping("/validation")
    public ResponseEntity paymentValidation(String impUid) throws IOException {

        Map<String, String> paymentResult = getPaymentInfo(impUid);
        String name = paymentResult.get("name");
        Optional<Product> result = productRepository.findByName(name);
        Product product = result.get();

        Gson gson = new Gson();
        List<Map<String, Object>> myResult = gson.fromJson(paymentResult.get("customData"), List.class);


        if (paymentResult.get("amount").equals(product.getPrice())) {
            return ResponseEntity.ok().body("ok");
        } else {

            String token = getToken();
            refundRequest(token,impUid,paymentResult.get("amount"));
            return ResponseEntity.badRequest().body("fail");

        }

    }

}