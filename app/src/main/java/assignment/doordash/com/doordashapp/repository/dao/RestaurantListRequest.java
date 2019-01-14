package assignment.doordash.com.doordashapp.repository.dao;

/**
 * Created by Sowmya on 1/12/19.
 */

public class RestaurantListRequest {
    private String latitude;
    private String longitude;
    private int limit;
    private int offset;

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public static RestaurantListRequestBuilder builder(){
        return new RestaurantListRequestBuilder();
    }

    protected RestaurantListRequest(RestaurantListRequestBuilder restaurantRequestBuilder) {
        this.latitude = restaurantRequestBuilder.latitude;
        this.longitude = restaurantRequestBuilder.longitude;
        this.limit = restaurantRequestBuilder.limit;
        this.offset = restaurantRequestBuilder.offset;
    }

}
