package com.linchtech.download;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class PictureStorePipeline implements Pipeline {

    public void process(ResultItems resultItems, Task task) {
        // System.out.println(resultItems.getRequest().getUrl());
        Object img = resultItems.get("img");
        Object title = resultItems.get("title");
        Object desc = resultItems.get("desc");
        Object girlName = resultItems.get("girlName");
        if (img == null) {
            return;
        }
    }
}