package com.cmlx.spider.persist.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-3 0003 17:02
 */
@Data
@Accessors(chain = true)
public class UrlDto {

    private String title;
    private String image;

}
