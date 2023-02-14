package com.example.fooddeliveryapp.Domain;

public class CategoryDomain {

    private String title;
    private String pic;


    // === is the constructor to specify what does each particular element in arraylist has (here title, pic) ===
    public CategoryDomain(String title, String pic){


            this.title = title;
            this.pic = pic;


    }


    // === GETTER AND SETTER METHODS FOR TITLE AND PIC ===
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



}
