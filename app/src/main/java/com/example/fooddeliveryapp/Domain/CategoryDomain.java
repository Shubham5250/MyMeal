package com.example.fooddeliveryapp.Domain;

public class CategoryDomain {

    private String title;
    private String pic;

    public CategoryDomain(String title, String pic){}

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

    {
        this.title = title;
        this.pic = pic;

    }

}
