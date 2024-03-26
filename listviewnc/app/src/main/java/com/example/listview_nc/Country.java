package com.example.listview_nc;

public class Country {

    private int image;
    private String name;
    private int pop;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public Country(int image, String name, int pop) {
        this.image = image;
        this.name = name;
        this.pop = pop;
    }
}
