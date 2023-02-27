package com.example.api;

public class DATA {
    String name,typs,price,color,id,code;

    public DATA(String name,String typs,String price,String color,String id,String code){

        this.name=name;
        this.typs=typs;
        this.price=price;
        this.color=color;
        this.id=id;
        this.code=code;

    }
    public String getName() {
        return name;
    }
    public String getTyps() {
        return typs;
    }
    public String getPrice() {
        return price;
    }
    public String getId() {
        return id;
    }
    public String getColor() {
        return color;
    }
    public String getCode() {
        return code;
    }


}
