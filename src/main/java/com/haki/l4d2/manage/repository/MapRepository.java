package com.haki.l4d2.manage.repository;

import com.haki.l4d2.manage.pojo.po.MapPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: haki
 * @Date: 2019/5/11 19:54
 * @Description:
 */
@Repository
public interface MapRepository extends JpaRepository<MapPO,Integer>{


}
