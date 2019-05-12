package com.haki.l4d2.manage.spider.impl;

import com.google.common.collect.Lists;
import com.haki.l4d2.manage.pojo.L4d2MapDTO;
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
public class L4D2ccSpider extends Spider{



    public L4D2ccSpider(String url) {
        super.url = url;
    }

    @Override
    public List<L4d2MapDTO> spider() {
        //解析url
        Document document = parseHtml(url);
        document.setBaseUri("http://www.kk175.com");
        //
        Elements els = document.select(".con_two_1 li");
        List<L4d2MapDTO> list = Lists.newArrayList();
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
        element.setBaseUri("http://www.kk175.com");
        String text = element.text();
        try {
            super.num = Integer.parseInt(text);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     *
     * 功能描述: 根据网页信息组装地图的信息
     *
     * @param element 网页元素
     * @return: 组装好的地图信息
     * @auther: haki
     * @date: 2019/5/4 21:50
     */
    private L4d2MapDTO buildMap(Element element) {
        L4d2MapDTO map = new L4d2MapDTO();
        //构建图片链接
        String image = element.select("a img").first().absUrl("src");
        map.setMapImage(image);
        Element el = element.select("a").get(1);
        //构建地图名称
        map.setMapName(el.text());
       //构建地图详细地址
       map.setMapUrl(el.absUrl("href"));
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
        //构建地图评分
        String soccer  = element.select(".list_score_soft").first().text();
        map.setSoccer(Float.parseFloat(soccer));
        return map;
    }



    @Override
    public List call() throws Exception {
        return this.spider();
    }
}
