package com.example.novia.foodcourt;

/**
 * Created by Novia on 11/3/2017.
 */

class Food {
    private long id;
    private String nama_food;
    private String menu_food;
    private String harga_food;

    public Food()
    {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama_food() {
        return nama_food;
    }

    public void setNama_food(String nama_food) {
        this.nama_food = nama_food;
    }

    public String getMenu_food() {
        return menu_food;
    }

    public void setMenu_food(String menu_food) {
        this.menu_food = menu_food;
    }

    public String getHarga_food() {
        return harga_food;
    }

    public void setHarga_food(String harga_food) {
        this.harga_food = harga_food;
    }

    public String toString()
    {
        return "Food "+ nama_food +" "+ menu_food + " "+ harga_food;
    }
}
