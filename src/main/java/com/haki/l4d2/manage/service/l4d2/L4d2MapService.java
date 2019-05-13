package com.haki.l4d2.manage.service.l4d2;

import com.haki.l4d2.manage.common.pojo.PageParam;
import com.haki.l4d2.manage.pojo.dto.L4d2MapDTO;

import java.util.List;

/**
 * @Author:haki
 * @DATE:2019/5/12
 * @Description:
 */
public interface L4d2MapService {


    public L4d2MapDTO getL4D2MapDTOByPage(PageParam pageParam);
}
