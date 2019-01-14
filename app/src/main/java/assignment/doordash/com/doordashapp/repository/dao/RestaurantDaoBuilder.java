package assignment.doordash.com.doordashapp.repository.dao;

public class RestaurantDaoBuilder {
    int deliveryFee;
    int id;
    String statusType;
    String description;
    int businessId;
    String coverImgUrl;
    String name;
    String status;

    protected RestaurantDaoBuilder(){

    }

    public RestaurantDaoBuilder deliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
        return this;
    }

    public RestaurantDaoBuilder id(int id) {
        this.id = id;
        return this;
    }

    public RestaurantDaoBuilder statusType(String statusType) {
        this.statusType = statusType;
        return this;
    }

    public RestaurantDaoBuilder status(String status) {
        this.status = status;
        return this;
    }

    public RestaurantDaoBuilder description(String description) {
        this.description = description;
        return this;
    }

    public RestaurantDaoBuilder businessId(int businessId) {
        this.businessId = businessId;
        return this;
    }

    public RestaurantDaoBuilder coverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
        return this;
    }

    public RestaurantDaoBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RestaurantDao build() {
        return new RestaurantDao(this);
    }
}