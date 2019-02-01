package assignment.doordash.com.doordashapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import assignment.doordash.com.doordashapp.R;
import assignment.doordash.com.doordashapp.Utils;
import assignment.doordash.com.doordashapp.activity.dataholders.ListDataHolder;
import assignment.doordash.com.doordashapp.activity.dataholders.RestaurantListItem;
import assignment.doordash.com.doordashapp.repository.DoorDashRepository;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantDao;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantListResponseDao;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantListRequest;

/**
 * Created by Sowmya on 1/12/19.
 */

public class RestaurantListViewModel extends ViewModel{

    private DoorDashRepository doorDashRepository;

    @Inject
    RestaurantListViewModel(DoorDashRepository doorDashRepository){
        this.doorDashRepository = doorDashRepository;
    }

    public LiveData<ListDataHolder> getRestaurantList(RestaurantListRequest restaurantListRequest){
        LiveData<ListDataHolder> listScreenDataHolderLiveData = new MediatorLiveData<>();
        LiveData<RestaurantListResponseDao> restaurantListResponseDaoLiveData = doorDashRepository.getRestaurantList(restaurantListRequest);
        ((MediatorLiveData<ListDataHolder>) listScreenDataHolderLiveData).addSource(restaurantListResponseDaoLiveData, new Observer<RestaurantListResponseDao>() {
            @Override
            public void onChanged(@Nullable RestaurantListResponseDao restaurantListResponseDao) {
                ListDataHolder listDataHolder = convertDaoToViewObject(restaurantListResponseDao);
                ((MediatorLiveData<ListDataHolder>) listScreenDataHolderLiveData).setValue(listDataHolder);
            }
        });
        return listScreenDataHolderLiveData;
    }

    private ListDataHolder convertDaoToViewObject(RestaurantListResponseDao restaurantListResponseDao){
        if(restaurantListResponseDao.hasError()){
            return new ListDataHolder.ListDataHolderBuilder()
                    .errorResource(R.string.error_message).build();
        }
        List<RestaurantDao> listResponseDaoList = restaurantListResponseDao.getData();
        List<RestaurantListItem> restaurantListItems= listResponseDaoList.stream().map(s -> convertToViewItem(s))
                                .collect(Collectors.toList());
        return new ListDataHolder.ListDataHolderBuilder()
                .restaurantListItemList(restaurantListItems)
                .build();
    }

    private RestaurantListItem convertToViewItem(RestaurantDao restaurantDao){

        Log.d("viewmodel", restaurantDao.getId() + ":" +restaurantDao.isLikeStatus());
        return  RestaurantListItem.builder()
                .id(restaurantDao.getId())
                .description(restaurantDao.getDescription())
                .imageURL(restaurantDao.getCoverImgUrl())
                .name(restaurantDao.getName())
                .waitTime(restaurantDao.getStatus())
                .status(restaurantDao.isLikeStatus())
                .build();
    }

}
