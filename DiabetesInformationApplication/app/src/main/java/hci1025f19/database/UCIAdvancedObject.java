package hci1025f19.database;

import java.io.Serializable;

public class UCIAdvancedObject implements Serializable {

    private String diary;
    private String good;
    private String bad;
    private String id;
    private Long date;
    private String readableDate;

    public Boolean getHotfixes() {
        return hotfixes;
    }

    public void setHotfixes(Boolean hotfixes) {
        this.hotfixes = hotfixes;
    }

    private Boolean hotfixes;

    public String getDiary() {
        return diary;
    }

    public void setDiary(String diary) {
        this.diary = diary;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getBad() {
        return bad;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getReadableDate() {
        return readableDate;
    }

    public void setReadableDate(String readableDate) {
        this.readableDate = readableDate;
    }





}
