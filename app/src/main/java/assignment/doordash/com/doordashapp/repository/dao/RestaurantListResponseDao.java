package assignment.doordash.com.doordashapp.repository.dao;

import java.util.List;

/**
 * Created by Sowmya on 1/12/19.
 */

public class RestaurantListResponseDao {

    private List<RestaurantDao> restaurantDaoList;

    private Throwable error;

    public Throwable getError(){
        return error;
    }

    public boolean hasError(){
        return error != null;
    }

    public List<RestaurantDao> getData() {
        return restaurantDaoList;
    }

    public RestaurantListResponseDao(List<RestaurantDao> restaurantDaoList,Throwable error) {
        this.restaurantDaoList = restaurantDaoList;
        this.error = error;
    }


}
