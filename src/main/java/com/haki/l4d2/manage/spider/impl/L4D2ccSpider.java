package com.haki.l4d2.manage.spider.impl;

import com.google.common.collect.Lists;
import com.haki.l4d2.manage.pojo.L4d2Map;
import com.haki.l4d2.manage.spider.Spider;
import com.haki.l4d2.manage.util.DateUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * l4d2cc的解析规则
 */
public class L4D2ccSpider extends Spider {
    @Override
    public List<L4d2Map> spider(String url) {
        //解析url
        Document document = parseHtml(url);
        //
        Elements els = document.select(".con_two_1 li");
        List<L4d2Map> list = Lists.newArrayList();
        //构建地图
        els.stream()
                .map(this::buildMap)
                .forEach(list::add);
        return list;
    }

    @Override
    public void spiderNum(String url) {
        Document document = parseHtml(url);
        Element element = document.select(".pageinfo strong").first();
        String text = element.text();
        try {
            super.num = Integer.parseInt(text);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private L4d2Map buildMap(Element element) {

        L4d2Map map = new L4d2Map();
        //构建图片链接
        String image = element.select("a img").first().attr("src");
        map.setMapImage(image);
        Element el = element.select("a").get(1);
        //构建地图名称
        map.setMapName(el.ownText());
        //构建地图详细地址
        map.setMapUrl(el.attr("href"));
        //构建地图下载次数
        Elements els = element.select(".list_download_num");

        String text = els.first().text();
        String[] split = text.trim().split("[:：]");
        List<String> strs = Arrays.stream(split)
                .map(x -> x.replaceAll("大小|时间", ""))
                .map(String::trim)
                .filter(str -> !str.equals("下载次数")).collect(Collectors.toList());
        map.setDownloadCount(Long.parseLong(strs.get(0)));
        map.setSize(strs.get(1));
        map.setUpdateTime(DateUtil.transferDate(strs.get(2),"yyyy-MM-dd"));
        return map;
    }
}
