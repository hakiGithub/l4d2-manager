package com.haki.l4d2.manage.controller.l4d2;

import com.haki.l4d2.manage.common.pojo.PageParam;
import com.haki.l4d2.manage.common.pojo.ResultBean;
import com.haki.l4d2.manage.pojo.dto.L4d2MapDTO;
import com.haki.l4d2.manage.service.l4d2.L4d2MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:haki
 * @DATE:2019/5/12
 * @Description:l4d2地图Controller
 */
@RestController
@RequestMapping("map")
public class L4d2MapController {

    @Autowired
    private L4d2MapService l4d2MapService;

    @GetMapping("show")
    public ResultBean show(PageParam pageParam){
        return ResultBean.success(l4d2MapService.getL4D2MapDTOByPage(pageParam));
    }

}
