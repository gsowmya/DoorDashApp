package assignment.doordash.com.doordashapp.repository;

import android.arch.lifecycle.LiveData;

import assignment.doordash.com.doordashapp.repository.dao.RestaurantListResponseDao;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantListRequest;

/**
 * Created by Sowmya on 1/11/19.
 */

public interface DoorDashRepository {
    LiveData<RestaurantListResponseDao> getRestaurantList(RestaurantListRequest restaurantListRequest);
}
