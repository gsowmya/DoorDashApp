package assignment.doordash.com.doordashapp.activity;

import android.arch.lifecycle.LiveData;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import assignment.doordash.com.doordashapp.R;
import assignment.doordash.com.doordashapp.activity.common.BaseActivity;
import assignment.doordash.com.doordashapp.activity.common.EndlessRecyclerViewScrollListener;
import assignment.doordash.com.doordashapp.activity.dataholders.ListDataHolder;
import assignment.doordash.com.doordashapp.activity.common.LocationProvider;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantListRequest;
import assignment.doordash.com.doordashapp.viewmodel.RestaurantListViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantListActivity extends BaseActivity implements RestaurantListObserver.LoadDataListener {

    public static final String TAG = RestaurantListActivity.class.getName();

    @Inject
    RestaurantListViewModel restaurantListViewModel;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    LocationProvider locationProvider;

    @Inject
    RestaurantListObserver restaurantListObserver;

    // Store a member variable for the listener
    private EndlessRecyclerViewScrollListener scrollListener;
    int offset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_restaurent_list);
        ButterKnife.bind(this);
        getPresentationComponent().inject(this);
        renderUI();
    }

    private void renderUI(){
        if(!locationProvider.hasLocationPermission()) {
            locationProvider.requestLocationPermission();
        }else{
            loadRestaurantsFromCurrentLocation(offset);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 101){
            //permissions granted, so load current restaurants
            loadRestaurantsFromCurrentLocation(offset);
        }else{
            Toast.makeText(this,"Location permission is required to proceed",Toast.LENGTH_LONG).show();
        }
    }

    private void loadRestaurantsFromCurrentLocation(int offset){
        Location location = locationProvider.getLatLngFromCurrentLocation();
        if(location == null) return;
        LiveData<ListDataHolder> restaurantList = restaurantListViewModel.getRestaurantList(getRestaurantRequest(location,offset));
        restaurantList.observe(this, restaurantListObserver);
    }


    private RestaurantListRequest getRestaurantRequest(Location location,int offset){
        return RestaurantListRequest.builder()
                .latitude(String.valueOf(location.getLatitude()))
                .longitude(String.valueOf(location.getLongitude()))
                .limit(50)
                .offset(offset)
                .build();
    }

    @Override
    public View getView() {
        return recyclerView;
    }

    @Override
    public void loadMoreByOffset(int offset) {
        loadRestaurantsFromCurrentLocation(offset);
    }
}
