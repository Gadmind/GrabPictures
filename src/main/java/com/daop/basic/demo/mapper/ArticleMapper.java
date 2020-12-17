package com.daop.basic.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daop.basic.demo.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo.mapper
 * @Description:
 * @DATE: 2020-12-15
 * @AUTHOR: Administrator
 **/
public interface ArticleMapper extends BaseMapper<Article> {
    int insertBatch(@Param("articles") List<Article> articles);
}
