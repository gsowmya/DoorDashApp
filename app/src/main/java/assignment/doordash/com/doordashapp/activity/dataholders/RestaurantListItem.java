package assignment.doordash.com.doordashapp.activity.dataholders;

/**
 * Created by Sowmya on 1/12/19.
 */

public class RestaurantListItem {

    private int id;
    private String imageURL;
    private String name;
    private String description;
    private String waitTime;

    protected RestaurantListItem(RestaurantListItemBuilder builder){
        this.id = builder.id;
        this.imageURL = builder.imageURL;
        this.name = builder.name;
        this.description = builder.description;
        this.waitTime = builder.waitTime;
    }

    public static RestaurantListItemBuilder builder(){
        return new RestaurantListItemBuilder();
    }

    public int getId() {
        return id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getWaitTime() {
        return waitTime;
    }
}
