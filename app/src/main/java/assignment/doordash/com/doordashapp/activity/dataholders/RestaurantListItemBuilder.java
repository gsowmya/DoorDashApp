package assignment.doordash.com.doordashapp.activity.dataholders;

public class RestaurantListItemBuilder {
    int id;
    String name;
    String imageURL;
    String description;
    String waitTime;

    public RestaurantListItemBuilder id(int id) {
        this.id = id;
        return this;
    }

    public RestaurantListItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RestaurantListItemBuilder imageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public RestaurantListItemBuilder description(String description) {
        this.description = description;
        return this;
    }

    public RestaurantListItemBuilder waitTime(String waitTime) {
        this.waitTime = waitTime;
        return this;
    }

    public RestaurantListItem build() {
        return new RestaurantListItem(this);
    }
}