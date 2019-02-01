package assignment.doordash.com.doordashapp.repository.impl;

/**
 * Created by Sowmya on 1/12/19.
 */

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import assignment.doordash.com.doordashapp.Utils;
import assignment.doordash.com.doordashapp.activity.dataholders.RestaurantListItem;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantDao;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantListResponseDao;
import assignment.doordash.com.doordashapp.repository.vo.DoorDashRestaurant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantListCallBackHandler implements Callback<List<DoorDashRestaurant>> {

    public static final String TAG = RestaurantListCallBackHandler.class.getName();

    private MutableLiveData<RestaurantListResponseDao> doorDashListResponseDaoLiveData;
    Application application;

    protected RestaurantListCallBackHandler(LiveData<RestaurantListResponseDao> doorDashListResponseDaoLiveData, Application application){
        this.doorDashListResponseDaoLiveData = (MutableLiveData<RestaurantListResponseDao>) doorDashListResponseDaoLiveData;
       this.application = application;
    }

    @Override
    public void onResponse(Call<List<DoorDashRestaurant>> call, Response<List<DoorDashRestaurant>> response) {
        Log.d(TAG, "onResponse: " + response.message());
        if(response != null && response.body() != null){
            RestaurantListResponseDao doorDashListResponseDao =
                    convertRestaurantListResponse(response.body());
            ((MutableLiveData<RestaurantListResponseDao>)doorDashListResponseDaoLiveData).setValue(doorDashListResponseDao);
            Log.d(TAG,doorDashListResponseDao.getData().toString());
        }else{
            Log.w(TAG,"response in unexpected format");
        }
    }
    @Override
    public void onFailure(Call<List<DoorDashRestaurant>> call, Throwable t) {
        Log.e(TAG, "onFailure: "+ t.getMessage());
        RestaurantListResponseDao restaurantListResponseDao = convertErrorToRestaurantListResponse(t);
        ((MutableLiveData<RestaurantListResponseDao>)doorDashListResponseDaoLiveData).setValue(restaurantListResponseDao);
    }


    private RestaurantListResponseDao convertRestaurantListResponse(List<DoorDashRestaurant> restaurantList){
        List<RestaurantDao> restaurantDaoList = restaurantList.stream().map(
                restaurant ->
                        convertToRestaurantDAO(restaurant)).collect(Collectors.toList());
        return new RestaurantListResponseDao(restaurantDaoList,null);
    }

    private RestaurantListResponseDao convertErrorToRestaurantListResponse(Throwable error){
        return new RestaurantListResponseDao(null,error);
    }

    private RestaurantDao convertToRestaurantDAO(DoorDashRestaurant doorDashRestaurant){

        return RestaurantDao.builder().id(doorDashRestaurant.getId())
                .deliveryFee(doorDashRestaurant.getDeliveryFee())
                .statusType(doorDashRestaurant.getStatusType())
                .status(doorDashRestaurant.getStatus())
                .description(doorDashRestaurant.getDescription())
                .businessId(doorDashRestaurant.getBusinessId())
                .coverImgUrl(doorDashRestaurant.getCoverImgUrl())
                .name(doorDashRestaurant.getName())
                .likeStatus(getStatusById(doorDashRestaurant.getId()))
                .build();
    }

    private boolean getStatusById(int id){
        String likes = Utils.getLikeList(application);
        String unlikes = Utils.getUnLikeList(application);
        Log.d(TAG,likes);
       if(likes.contains(String.valueOf(id))){
           Log.d(TAG,likes+"true");
           return true;
       }
        return false;
    }
}
