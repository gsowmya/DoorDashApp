package assignment.doordash.com.doordashapp.repository.impl;

/**
 * Created by Sowmya on 1/12/19.
 */

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;
import java.util.stream.Collectors;

import assignment.doordash.com.doordashapp.repository.dao.RestaurantDao;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantListResponseDao;
import assignment.doordash.com.doordashapp.repository.vo.DoorDashRestaurant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantListCallBackHandler implements Callback<List<DoorDashRestaurant>> {

    public static final String TAG = RestaurantListCallBackHandler.class.getName();

    private MutableLiveData<RestaurantListResponseDao> doorDashListResponseDaoLiveData;

    protected RestaurantListCallBackHandler(LiveData<RestaurantListResponseDao> doorDashListResponseDaoLiveData){
        this.doorDashListResponseDaoLiveData = (MutableLiveData<RestaurantListResponseDao>) doorDashListResponseDaoLiveData;
    }

    @Override
    public void onResponse(Call<List<DoorDashRestaurant>> call, Response<List<DoorDashRestaurant>> response) {
        Log.d(TAG, "onResponse: " + response.message());
        if(response != null && response.body() != null){
            RestaurantListResponseDao doorDashListResponseDao =
                    convertRestaurantListResponse(response.body());
            ((MutableLiveData<RestaurantListResponseDao>)doorDashListResponseDaoLiveData).setValue(doorDashListResponseDao);
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
                .build();
    }
}
