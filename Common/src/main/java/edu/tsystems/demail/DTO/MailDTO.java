package edu.tsystems.demail.DTO;

import java.util.Date;

/**
 * Author: Ivan Pastukh
 * Date: 22.06.13
 * Time: 2:27
 */
public class MailDTO extends BaseDTO {
    private int id;
    private String from;
    private int fromId;
    private String to;
    private int toId;
    private String subject;
    private String body;
    private Date date;
    private Boolean read;

    public MailDTO() {
    }

    public MailDTO(String from, String to, String subject, Date date, Boolean read) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.date = date;
        this.read = read;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean isRead() {
        return read;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    @Override
    public String toString() {
        return "MailDTO{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", fromId=" + fromId +
                ", to='" + to + '\'' +
                ", toId='" + toId + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", read=" + read +
                '}';
    }
}
