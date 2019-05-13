package com.haki.l4d2.manage.pojo.po;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author:haki
 * @DATE:2019/5/13
 * @Description:
 */
@Table(name = "l4d2_map_detail")
@Data
public class MapDetailPO implements Serializable {

    private static final long serialVersionUID = -3087438001977230148L;
    /**
     * 详情id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer detailId;
    /**
     * 映射l4d2_map中id
     */
    @Column(name = "map_id",nullable = false)
    private Integer mapId;
    /**
     * 地图介绍
     */
    @Column(name = "map_introduction")
    private String mapIntroduction;
    /**
     * 地图建图代码
     */
    @Column(name = "map_codes")
    private String mapCodes;
    /**
     * 地图下载地址
     */
    @Column(name = "map_download_url")
    private String mapDownloadUrl;
}
