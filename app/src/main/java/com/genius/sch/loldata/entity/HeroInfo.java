package com.genius.sch.loldata.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeroInfo {


    /**
     * type : champion
     * release-date : 2013-06-13T19:43:26Z
     * name : 暗裔剑魔
     * slug : aatrox
     * associated-faction :
     * associated-faction-slug : unaffiliated
     * image : {"type":"image","title":{"en_us":""},"subtitle":"","description":"","uri":"http://yz.lol.qq.com/v1/assets/images/champion/splash/Aatrox_0.jpg","encoding":"image/jpeg","width":1920,"height":1080,"x":1351,"y":346,"featured-champions":[]}
     */

    private String type;
    @SerializedName("release-date")
    private String releasedate;
    private String name;
    private String slug;
    @SerializedName("associated-faction")
    private String associatedfaction;
    @SerializedName("associated-faction-slug")
    private String associatedfactionslug;
    private ImageBean image;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getAssociatedfaction() {
        return associatedfaction;
    }

    public void setAssociatedfaction(String associatedfaction) {
        this.associatedfaction = associatedfaction;
    }

    public String getAssociatedfactionslug() {
        return associatedfactionslug;
    }

    public void setAssociatedfactionslug(String associatedfactionslug) {
        this.associatedfactionslug = associatedfactionslug;
    }

    public ImageBean getImage() {
        return image;
    }

    public void setImage(ImageBean image) {
        this.image = image;
    }

    public static class ImageBean {
        /**
         * type : image
         * title : {"en_us":""}
         * subtitle :
         * description :
         * uri : http://yz.lol.qq.com/v1/assets/images/champion/splash/Aatrox_0.jpg
         * encoding : image/jpeg
         * width : 1920
         * height : 1080
         * x : 1351
         * y : 346
         * featured-champions : []
         */

        private String type;
        private TitleBean title;
        private String subtitle;
        private String description;
        private String uri;
        private String encoding;
        private int width;
        private int height;
        private int x;
        private int y;
        @SerializedName("featured-champions")
        private List<?> featuredchampions;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public TitleBean getTitle() {
            return title;
        }

        public void setTitle(TitleBean title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getEncoding() {
            return encoding;
        }

        public void setEncoding(String encoding) {
            this.encoding = encoding;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public List<?> getFeaturedchampions() {
            return featuredchampions;
        }

        public void setFeaturedchampions(List<?> featuredchampions) {
            this.featuredchampions = featuredchampions;
        }

        public static class TitleBean {
            /**
             * en_us :
             */

            private String en_us;

            public String getEn_us() {
                return en_us;
            }

            public void setEn_us(String en_us) {
                this.en_us = en_us;
            }
        }
    }
}
