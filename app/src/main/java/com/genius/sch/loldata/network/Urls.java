package com.genius.sch.loldata.network;

public class Urls {
    public static String HEROLIST = "http://yz.lol.qq.com/v1/zh_cn/search/index.json";

    public static String getHeroDetails(String slug) {
        return "http://yz.lol.qq.com/v1/zh_cn/champions/" + slug + "/index.json";
    }
}
