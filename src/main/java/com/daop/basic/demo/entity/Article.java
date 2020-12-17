package com.daop.basic.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo.entity
 * @Description: 文章实体类
 * @DATE: 2020-12-15
 * @AUTHOR: Administrator
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Article implements Serializable {
    /**
     * 文章ID
     */
    private long articleId;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章地址
     */
    private String articleUrl;
    /**
     * 最后一次更新时间
     */
    private LocalDateTime lastModDate;
    /**
     * 爬取时间
     */
    private LocalDateTime createDate;

    public Article articleId(long articleId) {
        this.articleId = articleId;
        return this;
    }

    public Article title(String title) {
        this.title = title;
        return this;
    }

    public Article articleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
        return this;
    }

    public Article lastModDate(LocalDateTime lastModDate) {
        this.lastModDate = lastModDate;
        return this;
    }

    public Article createDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }
}
