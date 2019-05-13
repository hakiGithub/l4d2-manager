package com.haki.l4d2.manage.repository;

import com.haki.l4d2.manage.pojo.po.MapDetailPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author:haki
 * @DATE:2019/5/13
 * @Description:
 */
@Repository
public interface MapDetailRepository extends JpaRepository<MapDetailPO,Integer> {


}
