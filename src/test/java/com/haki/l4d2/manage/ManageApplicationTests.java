package com.haki.l4d2.manage;

import com.haki.l4d2.manage.pojo.po.MapPO;
import com.haki.l4d2.manage.repository.MapRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageApplicationTests {

    @Autowired
    private MapRepository mapRepository;

    @Test
    public void contextLoads() {
        MapPO map = new MapPO();
        map.setImage("img.img");
        map.setName("摩耶山");
        MapPO save = mapRepository.save(map);
    }

}
