package cn.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class LoginGithubController {
    @Value("${server.github.url}")
    private String serverUrl;
    @Value("${client.github.id}")
    private String clientId;
    @Value("${client.github.secret}")
    private String clientSecret;
    @Value("${redirect.github.uri}")
    private String redirectUri;

    /**
     * 第一步：获取code
     *
     * @param response
     */
    @GetMapping("/getcodes")
    public void getcode(HttpServletResponse response) {
        try {
            String url = serverUrl + "/login/oauth/authorize?" +
                    "client_id=" + clientId +
                    "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") +
                    "&response_type=code";
            response.sendRedirect(url);
        } catch (Exception e) {
            log.error("获取授权码失败：", e);
        }
    }

    /**
     * 第二步：根据code换取access_token（自动回调的地址）
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/loginGithub")
    public String callback(String code) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(serverUrl + "/login/oauth/access_token");

            //构造post参数
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("grant_type", "authorization_code"));
            list.add(new BasicNameValuePair("code", code));
            list.add(new BasicNameValuePair("client_id", clientId));
            list.add(new BasicNameValuePair("redirect_uri", redirectUri));
            list.add(new BasicNameValuePair("client_secret", clientSecret));
            HttpEntity entity = new UrlEncodedFormEntity(list);
            httpPost.setEntity(entity);
            httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");
            //发送请求，获取响应
            response = httpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity());
            log.info("响应结果：{}", result);
            //根据http状态码判断是否成功
            if (response.getStatusLine().getStatusCode() == 200) {
                Map<String, String> tokenObject = new HashMap<>();
                String[] results = result.split("\\&");
                for (String str : results) {
                    //以=来解析字符串
                    String[] split = str.split("=");
                    //将字符串存入map中
                    if (split.length == 1) {
                        tokenObject.put(split[0], null);
                    } else {
                        tokenObject.put(split[0], split[1]);
                    }
                }
//                Map<String, String> tokenObject = new HashMap<>();
//                ObjectMapper om = new ObjectMapper();
//                tokenObject = om.readValue(result, Map.class);
                log.info(tokenObject.toString());
                String access_token = tokenObject.get("access_token");
                return "redirect:/getusers?access_token=" + access_token;
            } else {
                log.error("获取access_token失败：");
            }
        } catch (Exception e) {
            log.error("获取access_token失败：", e);
        } finally {
            HttpClientUtils.closeQuietly(httpClient);
            HttpClientUtils.closeQuietly(response);
        }
        return "error";
    }

    /**
     * 第三步：根据access_token获取服务器上授权开放的资源，这里是获取Github的用户信息
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/getusers")
    public String getuser(String access_token, Model model) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet("https://api.github.com/user?access_token=" + access_token + "&scope=&token_type=bearer");
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");
            //发送请求，获取响应
            response = httpClient.execute(httpGet);
            String result = EntityUtils.toString(response.getEntity());
            log.info("响应结果:{}", result);
            //根据http状态码判断是否成功
            if (response.getStatusLine().getStatusCode() == 200) {
                Map<String, String> userObject = new HashMap<String, String>();
                ObjectMapper om = new ObjectMapper();
                userObject = om.readValue(result, Map.class);
                log.info(userObject.toString());
                model.addAttribute("data", userObject);
            } else {
                log.error("获取用户信息失败:");
            }
        } catch (Exception e) {
            log.error("获取用户信息失败:", e);
        } finally {
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
        return "home";
    }
}
