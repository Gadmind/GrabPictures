package com.daop.basic.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daop.basic.demo.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo.mapper
 * @Description:
 * @DATE: 2020-12-15
 * @AUTHOR: Administrator
 **/
public interface TagMapper extends BaseMapper<Tag> {
    int insertBatch(@Param("tags") List<Tag> tags);
}
