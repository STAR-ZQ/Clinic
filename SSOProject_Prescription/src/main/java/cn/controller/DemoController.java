package cn.controller;

import cn.http.HttpClientHelper;
import cn.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class DemoController {

    @Value("${server.medicine.url}")
    private String serverMedicineUrl;
    @Autowired
    private HttpClientHelper httpClientHelper;

    @GetMapping("/api/medicine/users")
    public Response getUsers() {
        return httpClientHelper.get(serverMedicineUrl + "/api/medicine/users");
    }

    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    /*private String ssoServerUrl = "http://localhost:9001";
    private String ssoCallbackUrl = "http://localhost:9005/login";
    private String ssoClientId = "client";
    private String ssoClientSecret = "secret";

    @GetMapping("tologin")
    public String tologin() {
        return "tologin";
    }

    *//**
     * 第三方登录第一步,获取code
     * 页面会跳转到第三方网站(微信、gq、微博)，请求授权，用户允许后，第三方网站会回调redirect_ uri, 并传回code
     *
     * @param response
     *//*
    @GetMapping(" /oauth2/code")
    public void code(HttpServletResponse response) {
        String params = "/oauth/authorize?response_type=code"
                + "&client_id=" + ssoClientId
                + "&redirect_uri=" + ssoCallbackUrl
                + "&scope=app";
        try {
            response.sendRedirect(ssoServerUrl + params);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        log.info("发送请求成功:{}, {}", ssoServerUrl, params);
    }

    *//**
     * 第三方登录第二步
     * 回调地址， 根据code获取access_ token
     * client_ _id (也可以叫appid) , client_ secret(密码),回调地址,都需要在第三方系统中注册登记
     *
     * @param code
     * @return
     *//*
    @GetMapping("/login")
    public String callback(String code, HttpServletResponse resp, Model model) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(ssoServerUrl + "/oauth/token");
            //1.构建list集合，往里面丢数据
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("grant_type", "authorization_code"));
            list.add(new BasicNameValuePair("scope", "app"));
            list.add(new BasicNameValuePair("redirect_uri", ssoCallbackUrl));
            list.add(new BasicNameValuePair("code", code));
            list.add(new BasicNameValuePair("client_id", ssoClientId));
            list.add(new BasicNameValuePair("client_secret", ssoClientSecret));
            log.debug("请求参数：{}", list);
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list);
            httpPost.setEntity(formEntity);
            //4.发送请求（获取access_token，默认只支持post请求）
            response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            log.debug("响应状态：{}", response.getStatusLine());
            String result = null;
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity);
                log.debug("响应内容长度：{}", httpEntity.getContentLength());
                log.debug("响应内容：{}", result);
            }
            if (response.getStatusLine().getStatusCode() == 200) {
                //取出access_token值
                ObjectMapper om = new ObjectMapper();
                @SuppressWarnings("unchecked")
                Map<String, String> map = om.readValue(result, Map.class);
                log.info("成功获取到令牌：{}", map);
                model.addAttribute("token", map);
                return "redirect:/call_api?access_token=" + map.get("access_token");
            } else {
                log.error(result);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            HttpClientUtils.closeQuietly(httpClient);
            HttpClientUtils.closeQuietly(response);
        }
        return "error";
    }

    *//**
     * 第三步，根据access_token调用服务器上的api
     *//*
    @GetMapping("/call_api")
    public String callback(String access_token, Model model) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet("http://localhost:9003/content/list?categoryId=1&access_token=" + access_token);

            response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            log.debug("响应状态：{}", response.getStatusLine());
            String result = null;
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity);
                log.debug("响应内容长度：{}", httpEntity.getContentLength());
                log.debug("响应内容：{}", result);
            }
            if (response.getStatusLine().getStatusCode() == 200) {
                //取出access_token值
                ObjectMapper om = new ObjectMapper();
                @SuppressWarnings("unchecked")
                List<Map<String, String>> list = om.readValue(result, List.class);
                log.info("成功获取到服务器资源：{}", list);
                model.addAttribute("data", list);
                return "index";
            } else {
                log.error(result);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            HttpClientUtils.closeQuietly(httpClient);
            HttpClientUtils.closeQuietly(response);
        }
        return "error";
    }
*/
}
