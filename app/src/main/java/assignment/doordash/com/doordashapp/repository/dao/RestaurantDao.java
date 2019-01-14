package assignment.doordash.com.doordashapp.repository.dao;

/**
 * Created by Sowmya on 1/12/19.
 */

public class RestaurantDao {

    private Integer deliveryFee;
    private Integer id;
    private String statusType;
    private String status;
    private String description;
    private Integer businessId;
    private String coverImgUrl;
    private String name;

    RestaurantDao(RestaurantDaoBuilder restaurantDaoBuilder){
        this.businessId = restaurantDaoBuilder.businessId;
        this.coverImgUrl = restaurantDaoBuilder.coverImgUrl;
        this.deliveryFee = restaurantDaoBuilder.deliveryFee;
        this.description = restaurantDaoBuilder.description;
        this.id = restaurantDaoBuilder.id;
        this.name = restaurantDaoBuilder.name;
        this.status = restaurantDaoBuilder.status;
        this.statusType = restaurantDaoBuilder.statusType;
    }

    public static RestaurantDaoBuilder builder(){
        return new RestaurantDaoBuilder();
    }

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public Integer getId() {
        return id;
    }

    public String getStatusType() {
        return statusType;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public String getName() {
        return name;
    }


    }
