package assignment.doordash.com.doordashapp.repository.impl;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import assignment.doordash.com.doordashapp.Utils;
import assignment.doordash.com.doordashapp.activity.dataholders.ListDataHolder;
import assignment.doordash.com.doordashapp.activity.dataholders.RestaurantListItem;
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
    private Application application;


    @Inject
    public DoorDashRepositoryImpl(Application application,DoorDashApi doorDashApi){
        this.doorDashApi = doorDashApi;
        this.application =  application;
    }

    @Override
    public LiveData<RestaurantListResponseDao> getRestaurantList(RestaurantListRequest restaurantListRequest) {
       final LiveData<RestaurantListResponseDao> doorDashListResponseDaoLiveData = new MutableLiveData<>();
       Call<List<DoorDashRestaurant>> doorDashRestaurantList =
               doorDashApi.getRestaurantList(restaurantListRequest.getLatitude(),restaurantListRequest.getLongitude(),
                restaurantListRequest.getOffset(),restaurantListRequest.getLimit());
        doorDashRestaurantList.enqueue(new RestaurantListCallBackHandler(doorDashListResponseDaoLiveData,application));
       return doorDashListResponseDaoLiveData;
    }



}
