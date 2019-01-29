package assignment.doordash.com.doordashapp.repository.impl;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import assignment.doordash.com.doordashapp.repository.DoorDashRepository;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantDao;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantListResponseDao;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantListRequest;
import assignment.doordash.com.doordashapp.repository.vo.DoorDashRestaurant;
import assignment.doordash.com.doordashapp.repository.vo.DoorDashRestaurantList;
import assignment.doordash.com.doordashapp.repository.webservices.DoorDashApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sowmya on 1/11/19.
 */

public class DoorDashRepositoryImpl implements DoorDashRepository {

    private static final String TAG = DoorDashRepositoryImpl.class.getName();

    @VisibleForTesting
    private DoorDashApi doorDashApi;


    @Inject
    public DoorDashRepositoryImpl(DoorDashApi doorDashApi){
        this.doorDashApi = doorDashApi;
    }

    @Override
    public LiveData<RestaurantListResponseDao> getRestaurantList(RestaurantListRequest restaurantListRequest) {
       final LiveData<RestaurantListResponseDao> doorDashListResponseDaoLiveData = new MutableLiveData<>();
       Call<List<DoorDashRestaurant>> doorDashRestaurantList =
               doorDashApi.getRestaurantList(restaurantListRequest.getLatitude(),restaurantListRequest.getLongitude(),
                restaurantListRequest.getOffset(),restaurantListRequest.getLimit());
        doorDashRestaurantList.enqueue(new RestaurantListCallBackHandler(doorDashListResponseDaoLiveData));
       return doorDashListResponseDaoLiveData;
    }

}
