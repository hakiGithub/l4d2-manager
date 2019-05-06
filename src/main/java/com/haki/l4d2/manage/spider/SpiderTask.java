package com.haki.l4d2.manage.spider;

import com.google.common.collect.Lists;
import com.haki.l4d2.manage.util.Task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Auther: haki
 * @Date: 2019/5/4 22:00
 * @Description:
 * */
public class SpiderTask implements Task {

  private Spider spider;

  public SpiderTask(Spider spider) {
    this.spider = spider;
  }

  @Override
  public void execute() {
    int num = spider.num;
    ExecutorService service = Executors.newFixedThreadPool(num);
    List<Future<List>> futures = Lists.newArrayList();

    futures.add(service.submit(spider));
  }
}
