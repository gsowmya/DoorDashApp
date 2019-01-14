package assignment.doordash.com.doordashapp.repository.dao;

public class RestaurantListRequestBuilder {
    String latitude;
    String longitude;
    int limit;
    int offset;

    public RestaurantListRequestBuilder latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public RestaurantListRequestBuilder longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public RestaurantListRequestBuilder limit(int limit) {
        this.limit = limit;
        return this;
    }

    public RestaurantListRequestBuilder offset(int offset) {
        this.offset = offset;
        return this;
    }

    public RestaurantListRequest build() {
        return new RestaurantListRequest(this);
    }
}