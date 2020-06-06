package com.model.bean;

/**
 * @author HRH
 * @version 1.0
 * @date 2020/6/5 15:48
 */
public class Message {
    private Boolean succeed;
    private String title;
    private String text;

    public Message() {
    }

    public Message(Boolean succeed, String title, String text) {
        this.succeed = succeed;
        this.title = title;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "succeed=" + succeed +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public Boolean getSucceed() {
        return succeed;
    }

    public Message setSucceed(Boolean succeed) {
        this.succeed = succeed;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Message setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }
}
