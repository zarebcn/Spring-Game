package com.zarebcn.springgames.model;

public class Game {

    private String title;
    private String developer;
    private String genre;
    private int id;

    public Game (String title, String developer, String genre, int id) {

        this.title = title;
        this.developer = developer;
        this.genre = genre;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getGenre() {
        return genre;
    }

    public int getId() {
        return id;
    }
}
