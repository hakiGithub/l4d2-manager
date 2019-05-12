package com.haki.l4d2.manage.service.l4d2.impl;

import com.haki.l4d2.manage.common.Exception.CustomException;
import com.haki.l4d2.manage.pojo.L4d2MapDTO;
import com.haki.l4d2.manage.service.l4d2.L4d2MapService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Author:haki
 * @DATE:2019/5/12
 * @Description:
 */
@Service
public class L4d2MapServiceImpl implements L4d2MapService {

    @Override
    public List<L4d2MapDTO> getAllL4d2Map(){
        L4d2MapDTO l4d2Map = new L4d2MapDTO();
        l4d2Map.setMapName("123222");
        l4d2Map.setMapUrl("123123");
        if (StringUtils.isBlank(l4d2Map.getMapImage())){
            throw new CustomException(202,"地图图片地址不能为空");
        }
        l4d2Map.getMapUrl();
        List<L4d2MapDTO> l4d2Maps = Collections.singletonList(l4d2Map);
        return l4d2Maps;
    }
}
