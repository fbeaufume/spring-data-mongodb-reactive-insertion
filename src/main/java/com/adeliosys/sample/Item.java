package com.adeliosys.sample;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Item {

    private String id;

    private Date date;

    public Item() {
        date = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
