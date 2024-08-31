package com.example.Schemone.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TravelPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String randomLink;
    private String userName;

    @Override
    public String toString() {
        return "TravelPlan{" +
                "id=" + id +
                ", randomLink='" + randomLink + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRandomLink() {
        return randomLink;
    }

    public void setRandomLink(String randomLink) {
        this.randomLink = randomLink;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
