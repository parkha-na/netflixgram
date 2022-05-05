package com.github.parkhana.service;

import com.github.parkhana.dao.UserDao;
import com.github.parkhana.vo.Users;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private static String NAVER_TOKEN_API_URL = "https://nid.naver.com/oauth2.0/token";
    private static String NAVER_ME_API_URL = "https://openapi.naver.com/v1/nid/me";
    private static String CLIENT_ID = "MCfxN6QafHKnz4o_H26m";   /* 애플리케이션 클라이언트 아이디값 (개발) */
    private static String CLIENT_SECRET = "frg2VF_47B";         /* 애플리케이션 클라이언트 시크릿값 (개발) */
    private static String DEV_CLIENT_ID = "TAMKGPSU19GpuoJbmZHE";   /* 애플리케이션 클라이언트 아이디값 */
    private static String DEV_CLIENT_SECRET = "_6HhQo8ktS";         /* 애플리케이션 클라이언트 시크릿값 */

    @Value("${app.upload.dir}")
    private String serverLocation;

    @Autowired
    private UserDao userDao;

    public int insertUser(Users user) {
        return userDao.insertUser(user);
    }

    public boolean checkUser(Map<String, Object> params) {
        Users user = userDao.selectUserOne(params);
        if (user != null && user.getSns_id() != null) {
            return true;
        } else {
            return false;
        }
    }

    public JSONObject getNaverToken(Map<String, Object> params) {
        String code = (String) params.get("code");
        String state = (String) params.get("state");

        String redirectURI = null;
        JSONParser jsonParser = new JSONParser();
        JSONObject tokenJSONObject = null;
        try {
            redirectURI = URLEncoder.encode("/callback", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String tokenApiURL = NAVER_TOKEN_API_URL;
        String clientId = "";
        String clientSecrect = "";
        if (StringUtils.equalsIgnoreCase(serverLocation, "server")) {
            clientId = CLIENT_ID;
            clientSecrect = CLIENT_SECRET;
        } else {
            clientId = DEV_CLIENT_ID;
            clientSecrect = DEV_CLIENT_SECRET;
        }
        tokenApiURL += "?grant_type=authorization_code";
        tokenApiURL += "&client_id=" + clientId;
        tokenApiURL += "&client_secret=" + clientSecrect;
        tokenApiURL += "&redirect_uri=" + redirectURI;
        tokenApiURL += "&code=" + code;
        tokenApiURL += "&state=" + state;

        try {
            URL url = new URL(tokenApiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            System.out.print("responseCode=" + responseCode);
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            if (responseCode == 200) {
//                {
//                    "access_token": "AAAAQosjWDJieBiQZc3to9YQp6HDLvrmyKC+6+iZ3gq7qrkqf50ljZC+Lgoqrg",
//                    "refresh_token": "c8ceMEJisO4Se7uGisHoX0f5JEii7JnipglQipkOn5Zp3tyP7dHQoP0zNKHUq2gY",
//                    "token_type": "bearer",
//                    "expires_in": "3600"
//                }
                tokenJSONObject = (JSONObject) jsonParser.parse(res.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tokenJSONObject;
    }

    public JSONObject getNaverUserProfile(Map<String, Object> params) {
        JSONParser jsonParser = new JSONParser();
        JSONObject meJSONObject = new JSONObject();
        String accessToken = (String) params.get("access_token");
        String refreshToken = (String) params.get("refresh_token");
        String tokenType = (String) params.get("token_type");
        String expiresIn = (String) params.get("expires_in");

        // 네이버 회원정보 가져오기
//        String token = "YOUR_ACCESS_TOKEN"; // 네이버 로그인 접근 토큰;
        String token = accessToken;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(NAVER_ME_API_URL, requestHeaders);

        try {
            meJSONObject = (JSONObject) jsonParser.parse(responseBody);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return meJSONObject;
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
