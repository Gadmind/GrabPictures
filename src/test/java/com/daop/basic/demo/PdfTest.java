package com.daop.basic.demo;

import ch.qos.logback.core.util.CloseUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo
 * @Description:
 * @DATE: 2020-12-16
 * @AUTHOR: Administrator
 **/
public class PdfTest {
    public static void main(String[] args) throws IOException, InterruptedException {
//        https://max.book118.com/html/2019/0619/8055020017002031.shtm
//        preview-bd
//        http://www.doc88.com/p-4387423484163.html
//        page_view  outer_page
        getPdfImg("https://max.book118.com/html/2019/0619/8055020017002031.shtm");
//        getPdfImg("https://max.book118.com/html/2020/1217/5033131103003043.shtm");
    }

    public static void getPdfImg(String url) {
        /*
        原创力文档预览接口
        https://openapi.book118.com/getPreview.html?&project_id=1&  项目ID 貌似是固定的
        aid=225154516&                                              解密后的文章ID      必填
        view_token=nD6o8c2lLIkhyocgKVrSUeiv4oYZkSkk&                预览密钥           必填
        page=111&                                                   页数  每页六条      必填
        filetype=doc&                                               文件类型           可选
        t=b1c73c7229a10857939636d047e13293&                         发送日期           可选
        callback=jQuery1830878034105949171_1608098450586&           回调函数名称        可选
        _=1608100201721                                             时间戳             可选
        * */
        try {
            Document document = Jsoup.parse(new URL(url), 3000);
            Elements script = document.getElementsByTag("script");
            for (Element element : script) {
                String data = element.data();
                if (!data.equals("")) {
                    if (data.contains("base.detail")) {
                        Map<String, String> fileInfos = fileInfos(data);
                        int actualPage = Integer.parseInt(fileInfos.get("actualPage"));
                        int previewPage = Integer.parseInt(fileInfos.get("previewPage"));
                        int total;
                        if (previewPage == actualPage) {
                            total = actualPage % 6 == 0 ? (actualPage / 6) : ((actualPage / 6) + 1);
                        } else {
                            total = previewPage % 6 == 0 ? (previewPage / 6) : ((previewPage / 6) + 1);
                            actualPage = previewPage;
                        }
                        for (int i = 1; i <= total; i++) {
                            String fileUrl = "https://openapi.book118.com/getPreview.html?&project_id=1&aid=" + fileInfos.get("fileId") +
                                    "&view_token=" + fileInfos.get("viewToken") +
                                    "&filetype=" + fileInfos.get("fileType") +
                                    "&page=" + (Math.min((i * 6), actualPage));
                            System.out.println(fileUrl);
                            String response = getUrlResponseToString(fileUrl);
                            response=response.substring(response.indexOf("(")+1,(response.length()-2));
                            System.out.println(response);
                            JSONObject object = JSONObject.parseObject(response);
                            Map dataInfo = JSONArray.parseObject(object.get("data").toString(), Map.class);
                            for (Object key : dataInfo.keySet()) {
                                Object val = dataInfo.get(key);
                                System.out.println(key+"==="+val);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static URLConnection getUrlConnection(String url) {
        URLConnection urlConnection = null;
        try {
            urlConnection = new URL(url).openConnection();
            urlConnection.setRequestProperty("user-agent", " Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
//            urlConnection.setRequestProperty("cookie", " __cfduid=d39c9ce8f5d1eca81dacbd986b4302e7e1602762248; remember_web_59ba36addc2b2f9401580f014c7f58ea4e30989d=eyJpdiI6IlpZclRGOTk0Nm1qR1dsQ00xTDRZR2c9PSIsInZhbHVlIjoidmViMk1HR0I5N3M4T1F1Q3lXSldYXC9MR2NoWGk5VmVhUUZIbU5zcHJkajhERlhOT1wvT3c4SHR1YlpXS1R4UnJnZ21HNHBxN1ZYMlc0MTJFcTVTZzI0VTlzU2ttXC9mK0F3U2krSXFRZ3A2V2ZHWTBadXRcL0RQdGIyRVhhS1NTR2RXZE5NZERiT1J6dFh3TFRyc2RhU2xROEIrTnNLMFRzN080cDVzYjdJQWlaSzlcL3lqMFwvMmJhR1wvYkdET0oyZG16YyIsIm1hYyI6IjgzMGI1NjQwYzU3NjMyYzdhMjNmNTAxNzcwYmFjYmViYjdmYjc4MDc5MDA4OTljZTVmZjkzOTQxOTUzYmE0ZGMifQ%3D%3D; _pk_ses.1.01b8=1; XSRF-TOKEN=eyJpdiI6IjN5MnJTZXd2aGZJaEhqQ1orYWdxMEE9PSIsInZhbHVlIjoiYjRPaTZtdFhQMkJ5QUpxaEQxZDJzKzdqUDlXa1pDMGJjRTl4N1pXd2N0akRvRVlweG5URVZsZGxpd0hDa0J6WiIsIm1hYyI6IjI2N2Y3YWRmNTE4Y2FlMmY2NDc5NGE0ZTI5YWRkMDVkOTdjZDkzMjQ0ZmY1ZDQ2ZWUxMDdiOTUzYmU1YmEyZWUifQ%3D%3D; wallhaven_session=eyJpdiI6IlkzeW9MV3pZdHpKR29qUjB1ZW1wRXc9PSIsInZhbHVlIjoiNHJDMitxWDhqa3g1YUF3cWhYNFR3VXBwMVZWbUFvbXJjNkFra3R6UHBQTTZFKzFCaGxZUVBleDVCQmJTZDB6bSIsIm1hYyI6IjcwMWI2Y2NjOTZjMDAwNGY3ZDU3OWVmNjI4MDBmZGJiZTg3NGE4MzBjYjc3Y2RmNWVmMjE1NTc1ZTRmMzU4ZTQifQ%3D%3D; _pk_id.1.01b8=da33600227865f77.1602762263.9.1602914617.1602901937.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlConnection;
    }
    public static String getUrlResponseToString(String url) {
        HttpURLConnection urlConnection = null;
        StringBuilder sb = null;
        BufferedReader br = null;
        try {
            urlConnection = (HttpURLConnection) getUrlConnection(url);
            urlConnection.connect();
            sb = new StringBuilder();
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            urlConnection.disconnect();

        }
        return sb.toString();
    }

    /**
     * 获取文档信息
     *
     * @param data
     * @return
     */
    public static Map<String, String> fileInfos(String data) {
        Map<String, String> infoMap = new HashMap<>(6);
        Matcher fileTypeMat = Pattern.compile("format:(\\'?)(.*)(\\'{1})(.*)(\\')").matcher(data);
        if (fileTypeMat.find()) {
            String fileType = fileTypeMat.group(4);
            infoMap.put("fileType", fileType);
        }
        Matcher fileInfoMat = Pattern.compile("title:(\\'?)(.*)(\\'{1})(.*)(\\')").matcher(data);
        if (fileInfoMat.find()) {
            String fileInfo = fileInfoMat.group(4);
            infoMap.put("fileInfo", fileInfo);
        }
        Matcher aidMat = Pattern.compile("aid:(\\s)(.*)(,{1})\\s\\/\\/解密后的id").matcher(data);
        if (aidMat.find()) {
            String fileId = aidMat.group(2);
            infoMap.put("fileId", fileId);
        }
        Matcher actualPageMat = Pattern.compile("actual_page:(\\s)(.*)(,{1})").matcher(data);
        if (actualPageMat.find()) {
            String actualPage = actualPageMat.group(2);
            infoMap.put("actualPage", actualPage);
        }
        Matcher previewPageMat = Pattern.compile("preview_page:(\\s)(.*)(,{1})").matcher(data);
        if (previewPageMat.find()) {
            String previewPage = previewPageMat.group(2);
            infoMap.put("previewPage", previewPage);
        }
        Matcher viewTokenMat = Pattern.compile("view_token:(\\s?)('?)(.*)('{1})").matcher(data);
        if (viewTokenMat.find()) {
            String viewToken = viewTokenMat.group(3);
            infoMap.put("viewToken", viewToken);
        }
        return infoMap;
    }
}
