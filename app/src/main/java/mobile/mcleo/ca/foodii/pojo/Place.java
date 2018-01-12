package mobile.mcleo.ca.foodii.pojo;

import java.util.Date;

/**
 * Created by leoman on 2017-08-27.
 */

public class Place {


    private String name;
    private String address;
    private Date createdTime;
    private String type;


    public Place(){

    }

    public Place(String name, String address){
        this.name = name;
        this.address = address;
        this.createdTime = new Date();
        this.type = "Food";
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getType() {
        return type;
    }

    public void setName(String name){
        this.name = name;
    }

    public Place clone(){
        Place place = new Place();
        place.name = this.name;
        place.address = this.address;
        place.type = this.type;
        place.createdTime = this.createdTime;
        return place;
    }


    @Override
    public String toString() {
        return name + " - " +type;
    }
}
