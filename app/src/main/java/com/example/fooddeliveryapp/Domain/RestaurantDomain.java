package com.example.fooddeliveryapp.Domain;

public class RestaurantDomain  {

    private String title;
    private String pic;
    private String typeVeg;
    private String vegSquarePic;
    private String distance;


    public RestaurantDomain(String title, String pic, String typeVeg, String vegSquarePic, String distance){
        this.title =title;
        this.pic =pic;
        this.typeVeg =typeVeg;
        this.vegSquarePic =vegSquarePic;
        this.distance =distance;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTypeVeg() {
        return typeVeg;
    }

    public void setTypeVeg(String typeVeg) {
        this.typeVeg = typeVeg;
    }

    public String getVegSquarePic() {
        return vegSquarePic;
    }

    public void setVegSquarePic(String vegSquarePic) {
        this.vegSquarePic = vegSquarePic;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
