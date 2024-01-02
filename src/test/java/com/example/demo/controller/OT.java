//package com.example.demo.controller;
//
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/jwj")
//@CrossOrigin(origins = "*") // 모두 허용
//public class OT {
//
//    private String getToken() throws IOException {
//        String token = getToken();
//        HttpURLConnection conn = null;
//
//        URL url = new URL("https://api.iamport.kr/users/getToken");
//        conn = (HttpURLConnection)url.openConnection();
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Content-type", "application/json");
//        conn.setRequestProperty("Accept", "application/json");
//        conn.setDoOutput(true);
//
//
//        JsonObject json = new JsonObject();
//        json.addProperty("imp_key", "7166480510824686"); // 7166480510824686 1156022477706867
//        json.addProperty("imp_secret", "nbqRZyrFl8HTrU6G6NXMfkzKgDFZZZeQzVdhhtg4iBd5AzReLFB8aU7p6c1Mp9Rw8GmB8Klg5RSj0poR");
//
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//        bw.write(json.toString());
//        bw.flush();
//        bw.close();
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
//        Gson gson = new Gson();
//        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
//
//        String Token = gson.fromJson(response, Map.class).get("access_token").toString();
//        br.close();
//        conn.disconnect();
//        return token;
//    }
//
//
//    @RequestMapping("/get/info")
//    public Map getPaymentInfo(String impUid) throws IOException {
//        String token = getToken();
//        HttpURLConnection conn = null;
//
//        URL url = new URL("https://api.iamport.kr/payments/" + "imp_816778276925" + impUid);
//        conn = (HttpURLConnection)url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Authorization", token);
//        conn.setDoOutput(true);
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
//        Gson gson = new Gson();
//        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
//        br.close();
//        conn.disconnect();
//
//        String amount = response.split("amount")[1].split(",")[0].replace("=","");
//        String name = response.split("name")[1].split(",")[0].replace("=","");
//
//        Map<String, String> result = new HashMap<>();
//        result.put("amount",amount);
//        result.put("name",name);
//
//        return result;
//
//
//    }
//
//
//    @RequestMapping("/validation")
//    public ResponseEntity paymentValidation(String impUid) throws IOException {
//        String dbPrice = "50000.0"; // db에서 값 조회
//        Map<String, String> paymentResult = getPaymentInfo(impUid);
//
//        if (paymentResult.get("amount").equals(dbPrice)) {
//            return ResponseEntity.ok().body("ok");
//        } else {
//            return ResponseEntity.badRequest().body("error");
//        }
//
//
//    }
//
//
//
//
//
//    public void refundRequest(String accesstoken, String imUid, String reason) throws IOException {
//        URL url = new URL("https://api.iamport.kr/payments/cancel");
//        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Content-type", "application/json");
//        conn.setRequestProperty("Accept", "application/json");
//
//        conn.setDoOutput(true);
//
//        JsonObject json = new JsonObject();
//        json.addProperty("imUid", imUid);
//        json.addProperty("reason", reason);
//
//        // 출력 스트림으로 해당 conn에 요청
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//        bw.write(json.toString());
//        bw.flush();
//        bw.close();
//
//        // 입력 스트림으로 conn 요청에 대한 응답 반환
//        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        br.close();
//        conn.disconnect();
//
//        log.info("결제 취소 완료 : 주문 번호 {}", imUid);
//    }
//        public String getToken(String imp_key, String imp_secret) throws IOException {
//            URL url = new URL("https://api.iamport.kr/users/getToken");
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("Accept", "application/json");
//
//            conn.setDoOutput(true);
//
//            JsonObject json = new JsonObject();
//            json.addProperty("imp_key", imp_key);
//            json.addProperty("imp_secret", imp_secret);
//
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//            bw.write(json.toString());
//            bw.flush();
//            bw.close();
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            Gson gson = new Gson();
//            String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
//            String accessToken = gson.fromJson(response, Map.class).get("access_token").toString();
//            br.close();
//
//            conn.disconnect();
//
//            log.info("Iamport 엑세스 토큰 발급 성공 : {}", accessToken);
//            return accessToken;
//        }
//    }
//





//public List<ProductDto> list() {
//        List<Product> result = productRepository.findAll();
//
//        List<ProductDto> productDtos = new ArrayList<>();
//        for (Product product : result) {
//
//        ProductDto productDto = ProductDto.builder()
//        .id(product.getId())
//        .name(product.getName())
//        .price(product.getPrice())
//        .build();
//        productDtos.add(productDto);
//        }
//
//        return productDtos;
//        }





//
//
//
//
//}
