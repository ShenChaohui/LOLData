package com.genius.sch.loldata.entity;

import java.util.List;

public class HeroInfo {

    /**
     * version : 6.24.1
     * id : Aatrox
     * key : 266
     * name : 暗裔剑魔
     * title : 亚托克斯
     * blurb : 亚托克斯是一位传奇战士，也是一个被称为暗裔的上古种族里仅存的五位战士之一。他优雅镇定地挥舞着巨剑，用令人迷离的剑式在千军万马中穿行。每当敌人倒下，亚托克斯那把如同活物般的巨剑便会啜饮着他们的鲜血，同时增强他的力量，并为他残暴、优雅的杀戮战役提供给养。<br><br>最早关于亚托克斯的记述来源于尘封历史中的记载。它述说着两大仅留下名字的杰出派系“护国军”和“法术领主”的战争。在那时，法术领主取得了一系列压倒性的胜利，他们发誓要肃清敌人，护国军处在灭绝的边缘。...
     * info : {"attack":8,"defense":4,"magic":3,"difficulty":4}
     * image : {"full":"Aatrox.png","sprite":"champion0.png","group":"champion","x":0,"y":0,"w":48,"h":48}
     * tags : ["Fighter","Tank"]
     * partype : BloodWell
     * stats : {"hp":537.8,"hpperlevel":85,"mp":105.6,"mpperlevel":45,"movespeed":345,"armor":24.384,"armorperlevel":3.8,"spellblock":32.1,"spellblockperlevel":1.25,"attackrange":150,"hpregen":6.59,"hpregenperlevel":0.5,"mpregen":0,"mpregenperlevel":0,"crit":0,"critperlevel":0,"attackdamage":60.376,"attackdamageperlevel":3.2,"attackspeedoffset":-0.04,"attackspeedperlevel":3}
     */

    private String version;
    private String id;
    private String key;
    private String name;
    private String title;
    private String blurb;
    private InfoBean info;
    private ImageBean image;
    private String partype;
    private StatsBean stats;
    private List<String> tags;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public ImageBean getImage() {
        return image;
    }

    public void setImage(ImageBean image) {
        this.image = image;
    }

    public String getPartype() {
        return partype;
    }

    public void setPartype(String partype) {
        this.partype = partype;
    }

    public StatsBean getStats() {
        return stats;
    }

    public void setStats(StatsBean stats) {
        this.stats = stats;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public static class InfoBean {
        /**
         * attack : 8
         * defense : 4
         * magic : 3
         * difficulty : 4
         */

        private int attack;
        private int defense;
        private int magic;
        private int difficulty;

        public int getAttack() {
            return attack;
        }

        public void setAttack(int attack) {
            this.attack = attack;
        }

        public int getDefense() {
            return defense;
        }

        public void setDefense(int defense) {
            this.defense = defense;
        }

        public int getMagic() {
            return magic;
        }

        public void setMagic(int magic) {
            this.magic = magic;
        }

        public int getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(int difficulty) {
            this.difficulty = difficulty;
        }
    }

    public static class ImageBean {
        /**
         * full : Aatrox.png
         * sprite : champion0.png
         * group : champion
         * x : 0
         * y : 0
         * w : 48
         * h : 48
         */

        private String full;
        private String sprite;
        private String group;
        private int x;
        private int y;
        private int w;
        private int h;

        public String getFull() {
            return full;
        }

        public void setFull(String full) {
            this.full = full;
        }

        public String getSprite() {
            return sprite;
        }

        public void setSprite(String sprite) {
            this.sprite = sprite;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
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

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }
    }

    public static class StatsBean {
        /**
         * hp : 537.8
         * hpperlevel : 85.0
         * mp : 105.6
         * mpperlevel : 45.0
         * movespeed : 345.0
         * armor : 24.384
         * armorperlevel : 3.8
         * spellblock : 32.1
         * spellblockperlevel : 1.25
         * attackrange : 150.0
         * hpregen : 6.59
         * hpregenperlevel : 0.5
         * mpregen : 0.0
         * mpregenperlevel : 0.0
         * crit : 0.0
         * critperlevel : 0.0
         * attackdamage : 60.376
         * attackdamageperlevel : 3.2
         * attackspeedoffset : -0.04
         * attackspeedperlevel : 3.0
         */

        private double hp;
        private double hpperlevel;
        private double mp;
        private double mpperlevel;
        private double movespeed;
        private double armor;
        private double armorperlevel;
        private double spellblock;
        private double spellblockperlevel;
        private double attackrange;
        private double hpregen;
        private double hpregenperlevel;
        private double mpregen;
        private double mpregenperlevel;
        private double crit;
        private double critperlevel;
        private double attackdamage;
        private double attackdamageperlevel;
        private double attackspeedoffset;
        private double attackspeedperlevel;

        public double getHp() {
            return hp;
        }

        public void setHp(double hp) {
            this.hp = hp;
        }

        public double getHpperlevel() {
            return hpperlevel;
        }

        public void setHpperlevel(double hpperlevel) {
            this.hpperlevel = hpperlevel;
        }

        public double getMp() {
            return mp;
        }

        public void setMp(double mp) {
            this.mp = mp;
        }

        public double getMpperlevel() {
            return mpperlevel;
        }

        public void setMpperlevel(double mpperlevel) {
            this.mpperlevel = mpperlevel;
        }

        public double getMovespeed() {
            return movespeed;
        }

        public void setMovespeed(double movespeed) {
            this.movespeed = movespeed;
        }

        public double getArmor() {
            return armor;
        }

        public void setArmor(double armor) {
            this.armor = armor;
        }

        public double getArmorperlevel() {
            return armorperlevel;
        }

        public void setArmorperlevel(double armorperlevel) {
            this.armorperlevel = armorperlevel;
        }

        public double getSpellblock() {
            return spellblock;
        }

        public void setSpellblock(double spellblock) {
            this.spellblock = spellblock;
        }

        public double getSpellblockperlevel() {
            return spellblockperlevel;
        }

        public void setSpellblockperlevel(double spellblockperlevel) {
            this.spellblockperlevel = spellblockperlevel;
        }

        public double getAttackrange() {
            return attackrange;
        }

        public void setAttackrange(double attackrange) {
            this.attackrange = attackrange;
        }

        public double getHpregen() {
            return hpregen;
        }

        public void setHpregen(double hpregen) {
            this.hpregen = hpregen;
        }

        public double getHpregenperlevel() {
            return hpregenperlevel;
        }

        public void setHpregenperlevel(double hpregenperlevel) {
            this.hpregenperlevel = hpregenperlevel;
        }

        public double getMpregen() {
            return mpregen;
        }

        public void setMpregen(double mpregen) {
            this.mpregen = mpregen;
        }

        public double getMpregenperlevel() {
            return mpregenperlevel;
        }

        public void setMpregenperlevel(double mpregenperlevel) {
            this.mpregenperlevel = mpregenperlevel;
        }

        public double getCrit() {
            return crit;
        }

        public void setCrit(double crit) {
            this.crit = crit;
        }

        public double getCritperlevel() {
            return critperlevel;
        }

        public void setCritperlevel(double critperlevel) {
            this.critperlevel = critperlevel;
        }

        public double getAttackdamage() {
            return attackdamage;
        }

        public void setAttackdamage(double attackdamage) {
            this.attackdamage = attackdamage;
        }

        public double getAttackdamageperlevel() {
            return attackdamageperlevel;
        }

        public void setAttackdamageperlevel(double attackdamageperlevel) {
            this.attackdamageperlevel = attackdamageperlevel;
        }

        public double getAttackspeedoffset() {
            return attackspeedoffset;
        }

        public void setAttackspeedoffset(double attackspeedoffset) {
            this.attackspeedoffset = attackspeedoffset;
        }

        public double getAttackspeedperlevel() {
            return attackspeedperlevel;
        }

        public void setAttackspeedperlevel(double attackspeedperlevel) {
            this.attackspeedperlevel = attackspeedperlevel;
        }
    }
}
