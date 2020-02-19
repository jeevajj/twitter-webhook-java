package com.twitter.activity.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name ="events")
@Entity
public class Event {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "json_entity", columnDefinition = "json")
    private String jsonEntity;

    @Column(name = "created")
    private Timestamp date;

    public Event() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJsonEntity() {
        return jsonEntity;
    }

    public void setJsonEntity(String jsonEntity) {
        this.jsonEntity = jsonEntity;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
