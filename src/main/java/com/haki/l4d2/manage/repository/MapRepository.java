package com.haki.l4d2.manage.repository;

import com.haki.l4d2.manage.pojo.po.MapPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : haki
 * @Date:  2019/5/11 19:54
 * @Description: 地图的dao类
 */
@Repository
public interface MapRepository extends JpaRepository<MapPO,Integer>{


}
