package com.haki.l4d2.manage;

import com.haki.l4d2.manage.pojo.dto.L4d2MapDTO;
import com.haki.l4d2.manage.spider.Spider;
import com.haki.l4d2.manage.spider.impl.L4D2ccSpider;
import com.haki.l4d2.manage.util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SpiderTest {



  @Test
  public void testSpider() {
    Document document = JsoupUtil.buildDocument("http://www.kk175.com/plus/list-8.html");
    System.out.println(document);
  }

  @Test
  public void testSpiderList() throws FileNotFoundException {
    Spider spiders = new L4D2ccSpider("http://www.kk175.com/plus/list-8.html");
    List<L4d2MapDTO> list = spiders.spider().stream()
            .sorted(Comparator.comparing(L4d2MapDTO::getSoccer).reversed())
            .collect(Collectors.toList());
    list.forEach(System.out::println);
  }

  @Test
  public void testStr() {
    String str = "   下载次数:63818 大小:181MB时间：2018-11-27";
    String[] split = str.trim().split("[:：]");
    Arrays.stream(split)
        .map(x -> x.replaceAll("大小|时间", ""))
        .map(String::trim)
        .forEach(System.out::println);
  }

  @Test
  public void testFile() {
    File file = new File("E:\\steam\\steamapps\\common\\Left 4 Dead 2\\left4dead2\\addons");
    File[] files = file.listFiles((dir, name) -> name.contains(".vpk"));
    Arrays.stream(files)
        .sorted(Comparator.comparing(File::length))
        .map(File::getName)
        .map(x -> x.substring(0, x.indexOf(".")))
        .forEach(System.out::println);
  }


}
