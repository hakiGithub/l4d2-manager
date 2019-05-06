package com.haki.l4d2.manage.pojo;

import com.haki.l4d2.manage.util.annotation.SpiderConfig;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
    private String size;
    /**
     * 地图类型 1为合作 2为对抗 3为生存
     */
    private Integer type;
    /**
     *更新时间
     */
    private Date updateTime;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     *
     * 地图作者
     *
     */
    private String author;
}
