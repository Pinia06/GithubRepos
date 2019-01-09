package com.example.anas.gitrepos.Model;

public class Item {

    private long id;
    private String name;
    private Owner owner;
    private String description;
    private String stargazers_count;

    public Item(long id, String name, Owner owner, String description, String stargazers_count) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.stargazers_count = stargazers_count;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public String getStargazers_count() {
        return stargazers_count;
    }
}
