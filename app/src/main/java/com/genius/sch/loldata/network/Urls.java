package com.genius.sch.loldata.network;

public class Urls {
    public static String HeroList = "http://yz.lol.qq.com/v1/zh_cn/search/index.json";

    public static String getHeroDetails(String heroName) {
        return "http://yz.lol.qq.com/v1/zh_cn/champions/" + heroName + "/index.json";
    }
}
