package com.cmlx.spider.controller;

import com.cmlx.spider.persist.dto.UrlDto;
import com.cmlx.spider.utils.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-3 0003 15:08
 */
@RestController
@RequestMapping("/spider")
public class SpiderController {

    @RequestMapping("getTitleAndImgByUrl")
    public UrlDto getTitleAndImgByUrl(String path){
        //设置官网地址
        String basePath = null;
        String[] split = path.split("/");
        basePath = split[2];
        Document doc = null;
        String title = null;
        String image = null;
        try {
            for (int i=0;i<5;i++){
                String s = HttpUtil.get(path);
                doc = Jsoup.parse(s);
                if (s != null){
                    break;
                }
            }
            title = doc.title();
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif|webp)]");
                String src1 = images.get(0).attr("src");
                if (src1 != null &&(!src1.contains("//")||src1.contains("data:"))){
                    src1 = basePath + src1;
                }
                System.out.println("src : " + src1);
                image = src1;
        } catch (Exception e) {
            //接收到错误链接（404页面）

        }

        return new UrlDto().setTitle(title).setImage(image);
    }


}
