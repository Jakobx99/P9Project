package hci1025f19.database;

import java.io.Serializable;

public class ErrorObject implements Serializable {

    private String page;
    private Long date;
    private String readableDate;
    private String task;
    private String type;
    private String element;
    private String frequency;
    private String completed;
    private String error;
    private String effect;
    private String id;
    private Boolean advanced = false;
    private String expectation;

    public Boolean getHotfixes() {
        return hotfixes;
    }

    public void setHotfixes(Boolean hotfixes) {
        this.hotfixes = hotfixes;
    }

    private Boolean hotfixes = false;


    public String getExpectation() {
        return expectation;
    }

    public void setExpectation(String expectation) {
        this.expectation = expectation;
    }

    public Boolean getAdvanced() {
        return advanced;
    }

    public void setAdvanced(Boolean advanced) {
        this.advanced = advanced;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }


    @Override
    public String toString() {
        return "ErrorObject{" +
                "page='" + page + '\'' +
                ", date=" + date +
                ", task='" + task + '\'' +
                ", type='" + type + '\'' +
                ", element='" + element + '\'' +
                ", frequency='" + frequency + '\'' +
                ", completed='" + completed + '\'' +
                ", error='" + error + '\'' +
                ", effect='" + effect + '\'' +
                '}';
    }
}
