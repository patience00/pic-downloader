package com.linchtech.download;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.management.JMException;

/**
 * @author 107
 * @date 2021-12-27 14:07
 * @description
 **/
public class XiuRenPageProcessor implements PageProcessor {


    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        // page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("title", page.getHtml().xpath("//div[@class='jianjie']/p[1]").toString());
        page.putField("desc", page.getHtml().xpath("//div[@class='jianjie']/p[2]").toString());
        page.putField("girlName", page.getHtml().xpath("//div[@class='jianjie']/p[3]").toString());
        page.putField("img", page.getHtml().xpath("//div[@class='content']/div[@class='content_left']/p/img/@src").toString());
        // if (page.getResultItems().get("name") == null) {
        //     //skip this page
        //     page.setSkip(true);
        // }
        // page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        // 部分三：从页面发现后续的url地址来抓取
        // page.addTargetRequests(page.getHtml().links().regex("https://www.xrmn5.cc/[A-Za-z]*/$").all());
        page.addTargetRequests(page.getHtml().links().regex("https://www.xrmn5.cc/\\w+/\\d+/\\w+\\.html").all());
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws JMException {

        Spider spider = Spider.create(new XiuRenPageProcessor());
        spider.addUrl("https://www.xrmn5.cc/XiuRen/2021/20219792.html");
        spider.addPipeline(new ConsolePipeline());
        spider.addPipeline(new PictureStorePipeline());
        //开启5个线程抓取
        spider.thread(5);
        //启动爬虫
        SpiderMonitor.instance().register(spider);
        spider.start();
    }
}
