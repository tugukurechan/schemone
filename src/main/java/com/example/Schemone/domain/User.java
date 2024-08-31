package com.example.Schemone.domain;

/**
 * userのドメインクラス.
 *
 * @author tuguk
 */
public class User {
    private Integer id;/** not null */
    private String name;/** not null */


    public User(){}
    public User(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
