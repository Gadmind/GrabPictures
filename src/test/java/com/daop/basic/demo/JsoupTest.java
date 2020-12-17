package com.daop.basic.demo;

import com.daop.basic.demo.common.utils.BasicUrlUtils;
import com.daop.basic.demo.entity.Article;
import com.daop.basic.demo.entity.Tag;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo
 * @Description:
 * @DATE: 2020-12-15
 * @AUTHOR: Administrator
 **/
public class JsoupTest {
    public static void main(String[] args) {
        /*List<Tag> tags = getAllTags();
        for (Tag tag : tags) {
            System.out.println(tag);
        }*/
        /*for (Article article : getAllArticles()) {
            System.out.println(article.getArticleUrl());
        }*/
        String url = "https://www.vmgirls.com/3596html";
        String url1 = "https://www.vmgirls.com/3758html";
        String url2 = "https://www.vmgirls.com/15215.html";
        getArticleImages(url);
        getArticleImages(url1);
        getArticleImages(url2);
    }

    /**
     * 获取一篇文章中的所有图片
     *
     * @param url
     */
    public static void getArticleImages(String url) {
        try {
            Document document = Jsoup.parse(new URL(url), 10000);
            // 图片信息所在 class nc-light-gallery
            Elements images = document.getElementsByClass("nc-light-gallery").get(0).getElementsByTag("img");
            System.out.println("===========》分类：" + document.getElementsByAttributeValue("rel", "category tag").text());
            System.out.println("===========》标签：" + document.getElementsByClass("post-tags").get(0).getElementsByTag("a").text());
            System.out.println("===========》内容：" + document.getElementsByClass("nc-light-gallery").get(0).text());
            System.out.println("===========》图片列表");
            for (Element image : images) {
                String href = image.attr("data-src");
                System.out.println("===========》 https:" + href);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有文章
     *
     * @return
     */
    public static List<Article> getAllArticles() {
        System.out.println("==============》开始爬取文章");
        List<Article> articles = new ArrayList<>(1024);
        Map<String, LocalDateTime> articlesInfo = new HashMap<>(1024);
//        https://www.vmgirls.com/sitemap.html
//        https://www.vmgirls.com/sitemap.xml
//        https://www.vmgirls.com?append=list-author&paged=63&action=ajax_load_posts&query=1&page=author
        try {
            System.out.println("==============》开始爬取网站地图");
           /* Document siteMapXml = Jsoup.parse(new URL("https://www.vmgirls.com/sitemap.xml"), 10000);
            Elements locTags = siteMapXml.getElementsByTag("loc");
            for (Element loc : locTags) {
                String mapUrl = loc.text();
                if (mapUrl.contains("-pt-post")) {
                    Document doc = Jsoup.parse(new URL(mapUrl), 6000);
                    Elements urlInfos = doc.getElementsByTag("url");
                    for (Element urlInfo : urlInfos) {
                        String id;
                        String url = urlInfo.getElementsByTag("loc").get(0).text();
                        String lastMod = urlInfo.getElementsByTag("lastmod").get(0).text();
                        lastMod = lastMod.replace("+00:00", "");
                        if (url.contains(".html")) {
                            id = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf(".html"));
                        } else {
                            id = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("html"));
                        }
                        articlesInfo.put(id, LocalDateTime.parse(lastMod));
                    }
                }
            }*/
            System.out.println("==============》文章最后更新日期获取完毕");
            System.out.println("==============》开始爬取文章信息");
            Document siteMapHtml = Jsoup.parse(new URL("https://www.vmgirls.com/sitemap.html"), 10000);
            Elements articleList = siteMapHtml.getElementById("content").getElementsByTag("a");
            for (Element article : articleList) {
                String href = article.attr("href");
                String idStr;
                if (href.contains(".html")) {
                    idStr = href.substring(0, href.lastIndexOf(".html"));
                } else {
                    idStr = href.substring(0, href.lastIndexOf("html"));
                }
                String title = article.attr("title");
                articles.add(
                        new Article().articleId(Long.parseLong(idStr))
                                .title(title)
                                .articleUrl("/" + href)
//                                .lastModDate(articlesInfo.get(idStr))
                                .createDate(LocalDateTime.now()));
            }
            System.out.println("==============》爬取结束，共爬取: " + articles.size() + "条数据");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return articles;
    }

    /**
     * 获取所有标签
     *
     * @return
     */
    public static List<Tag> getAllTags() {
        List<Tag> tagList = new ArrayList<>();
        try {
            //获取所有标签
            Document document = Jsoup.parse(new URL("https://www.vmgirls.com/tags/"), 3000);
            Element tagCloud = document.getElementsByClass("tagcloud").get(0);
            Elements tagsLink = tagCloud.getElementsByTag("a");
            for (Element tag : tagsLink) {
                String tagName = tag.text();
                String count = tag.getElementsByTag("span").get(0).text();
                String href = tag.attr("href").replaceAll("/tag", "");
                tagName = tagName.substring(0, tagName.indexOf(count));
                if (href.contains("%")) {
                    href = "/" + tagName;
                }
                tagList.add(new Tag().tagName(tagName).tagRef(href));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tagList;
    }
}
