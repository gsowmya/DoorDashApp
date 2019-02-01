package assignment.doordash.com.doordashapp.activity.dataholders;

/**
 * Created by Sowmya on 1/12/19.
 */

public class RestaurantListItem {

    public void setId(int id) {
        this.id = id;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private int id;
    private String imageURL;
    private String name;
    private String description;
    private String waitTime;

    public boolean isStatus() {
        return status;
    }

    private boolean status;

    protected RestaurantListItem(RestaurantListItemBuilder builder){
        this.id = builder.id;
        this.imageURL = builder.imageURL;
        this.name = builder.name;
        this.description = builder.description;
        this.waitTime = builder.waitTime;
        this.status = builder.status;
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
