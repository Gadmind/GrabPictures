package com.daop.basic.demo.vo;

import com.daop.basic.demo.form.PageForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo.vo
 * @Description: 分页视图对象
 * @DATE: 2020-11-25
 * @AUTHOR: Daop
 **/
@Data
public class PageVO<T> {
    /**
     * 分页数据
     */
    @ApiModelProperty(value = "分页数据")
    private List<T> records;

    /**
     * 总条数
     */
    @ApiModelProperty(value = "总条数")
    private Integer total;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Integer pages;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer currentPage;

    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;

    /**
     * 设置当前页和每页显示的数量
     *
     * @param pageForm 分页表单
     * @return 返回分页信息
     */
    @ApiModelProperty(hidden = true)
    public PageVO<T> setCurrentPageAndSize(PageForm<?> pageForm) {
        BeanUtils.copyProperties(pageForm, this);
        return this;
    }

    /**
     * 设置总记录数
     *
     * @param total 总记录数
     */
    @ApiModelProperty(hidden = true)
    public void setTotal(Integer total) {
        this.total = total;
        this.setPages(this.total % this.pageSize > 0 ? this.total / this.pageSize + 1 : this.total / this.pageSize);
    }

}
