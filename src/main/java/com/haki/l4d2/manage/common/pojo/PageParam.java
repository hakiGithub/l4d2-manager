package com.haki.l4d2.manage.common.pojo;

import lombok.Data;

/**
 * @Author:haki
 * @DATE:2019/5/13
 * @Description:
 */
@Data
public class PageParam {
    /**
     * 每页的显示的数量,默认为10
     */
    private int pageSize = 10;

    /**
     * 当前页的索引，默认为0
     */
    private int pageNum = 0;

}
