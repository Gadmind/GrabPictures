package com.daop.basic.demo;

import com.daop.basic.demo.entity.Article;
import com.daop.basic.demo.entity.Tag;
import com.daop.basic.demo.mapper.ArticleMapper;
import com.daop.basic.demo.mapper.TagMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BasicDemoApplicationTests {
    @Autowired
    TagMapper tagMapper;
    @Autowired
    ArticleMapper articleMapper;

    @Test
    void addAllTags() {
        List<Tag> tags = JsoupTest.getAllTags();
        if (tags.size() != 0) {
            System.out.println(tags.size());
            tagMapper.insertBatch(tags);
        }
    }

    @Test
    void addAllArticles() {
        List<Article> articles = JsoupTest.getAllArticles();
        if (articles.size() != 0) {
            System.out.println("==============》文章总数："+articles.size());

            System.out.println("==============》数据开始入库");
            articleMapper.insertBatch(articles);
            System.out.println("==============》数据入库完毕");

        }
    }


}
