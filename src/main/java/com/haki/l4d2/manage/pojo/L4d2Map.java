package com.haki.l4d2.manage.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 求生之路地图
 */
@Data
public class L4d2Map implements Serializable {

    private static final long serialVersionUID = -4963383273534100784L;
    /**
     * 地图Id
     */
     private Integer Id;
    /**
     * 地图名称
     */
    private String mapName;
    /**
     *地图封面
     */
    private String mapImage;
    /**
     * 地图评分
     */
    private Float soccer;
    /**
     * 下载次数
     */
    private Long downloadCount;
    /**
     * 地图地址
     */
    private String mapUrl;
    /**
     * 地图大小
     */
    private Integer size;
    /**
     * 地图类型 1为合作 2为对抗 3为生存
     */
    private Integer type;
}
