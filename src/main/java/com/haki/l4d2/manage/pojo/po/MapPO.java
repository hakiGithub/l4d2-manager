package com.haki.l4d2.manage.pojo.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private Integer id;

    private String name;

    private String image;
}
