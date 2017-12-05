package com.xingen.architecturecomponents.room.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ${新根} on 2017/11/25.
 * blog:http://blog.csdn.net/hexingen
 *
 * 创建一个表对应的实体
 *
 *  1. @Entity： 设置实体对应的表名
 *  2. @PrimaryKey： 设置为主键
 *  3. @ColumnInfo：设置字段名
 */
@Entity(tableName = "movies")
public class MovieBean {
    /**
     * @PrimaryKey设置为主键，
     * 且设置autoGenerate为true,自增长
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "year")
    private String year;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "image")
    private String image;



    public MovieBean(String year, String title, String image) {
        this.year = year;
        this.title = title;
        this.image = image;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    @Ignore
    private Images images;
    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
    public static class Images{
        private String small;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        private String large;
    }
}
