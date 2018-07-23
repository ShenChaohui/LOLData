package com.genius.sch.loldata.network;

public class Urls {
    public static String GET_CHAMPION_LIST = "http://yz.lol.qq.com/v1/zh_cn/search/index.json";

    public static String getChampionDetails(String slug) {
        return "http://yz.lol.qq.com/v1/zh_cn/champions/" + slug + "/index.json";
    }

    public static String GET_FACTION = "http://yz.lol.qq.com/v1/zh_cn/faction-browse/index.json";

    public static String getFactionDetails(String slug) {
        return "http://yz.lol.qq.com/v1/zh_cn/factions/" + slug + "/index.json";
    }
}
