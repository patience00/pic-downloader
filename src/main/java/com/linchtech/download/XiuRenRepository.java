package com.linchtech.download;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.FilePageModelPipeline;

import java.util.List;

/**
 * @author 107
 * @date 2021-12-27 9:41
 * @description
 **/
@HelpUrl("https://www.xrmn5.cc/[A-Za-z]*/$")
@TargetUrl("https://www.xrmn5.cc/\\w+/\\d+/\\d+\\.html")
public class XiuRenRepository {

    @ExtractBy("//div[@class='jianjie']/p[0]")
    private String title;

    @ExtractBy("//div[@class='jianjie']/p[1]")
    private String desc;

    @ExtractBy("//div[@class='jianjie']/p[2]")
    private String girlName;

    @ExtractBy("//div[@class='content']/div[@class='content_left']/img/src")
    private List<String> imgUrl;

    public static void main(String[] args) {
        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(1000), XiuRenRepository.class);
        // ooSpider.addPipeline(new FilePipeline("E:\\spider"));
        ooSpider.addPageModel(new FilePageModelPipeline("E:\\spider"))
                .addPageModel(new ConsolePageModelPipeline())
                .addUrl("https://www.xrmn5.cc/YouWu/2021/20217404.html").thread(1).run();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGirlName() {
        return girlName;
    }

    public void setGirlName(String girlName) {
        this.girlName = girlName;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }
}
