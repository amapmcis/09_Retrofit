package com.example.f1.a09_retrofit.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Repo extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;
    private String html_url;

    public Repo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}