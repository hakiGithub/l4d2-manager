package com.haki.l4d2.manage.pojo.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Auther: haki
 * @Date: 2019/5/11 19:52
 * @Description:
 */
@Entity(name="l4d2_map")
@Data
public class MapPO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**
     * 地图Id
     */
    private Integer id;
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
