package com.haki.l4d2.manage.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author:haki
 * @DATE:2019/5/13
 * @Description:
 */
@Data
public class L4D2MapDetailDTO implements Serializable {

    private static final long serialVersionUID = -6417868395652870L;

    /**
     * 详情id
     */
    private Integer detailId;
    /**
     * 地图Id
     */
    private Integer mapId;
    /**
     * 地图介绍
     */
    private String mapIntroduction;
    /**
     * 建图代码
     */
    private List<String> mapCodes;

    /**
     * 地图下载地址
     */
    private Map<String, String> mapDownloadUrl;
}
