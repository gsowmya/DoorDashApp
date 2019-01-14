package assignment.doordash.com.doordashapp.di;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import assignment.doordash.com.doordashapp.activity.dataholders.ListDataHolder;
import assignment.doordash.com.doordashapp.activity.RestaurantListAdapter;
import assignment.doordash.com.doordashapp.activity.RestaurantListObserver;
import assignment.doordash.com.doordashapp.activity.common.LocationProvider;
import assignment.doordash.com.doordashapp.viewmodel.RestaurantListViewModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Sowmya on 1/12/19.
 */

@Module
public class PresentationModule {

    private final FragmentActivity mActivity;
    private final View mView;

    public PresentationModule(FragmentActivity mActivity,View mView) {
        this.mActivity = mActivity;
        this.mView = mView;
    }

    @Provides
    LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    @Provides
    Activity getActivity() {
        return mActivity;
    }

    @Provides
    Context context(Activity activity) {
        return activity;
    }

    @Provides
    ViewModel restrauntListViewModel() {
        return ViewModelProviders.of(mActivity).get(RestaurantListViewModel.class);
    }

    @Provides
    LocationProvider providesLocationProvider(){
        return new LocationProvider(mActivity);
    }

    @Provides
    @AppComponent.ActivityScope
    RestaurantListAdapter providesListAdapter(){
        ListDataHolder listDataHolder = new ListDataHolder.ListDataHolderBuilder().build();
        return new RestaurantListAdapter(mActivity,listDataHolder);
    }

    @Provides
    RecyclerView providesRecyclerView(){
        return (RecyclerView) mView;
    }

    @Provides
    @AppComponent.ActivityScope
    RestaurantListObserver providesListObserver(Context context,RestaurantListAdapter restaurantListAdapter,RecyclerView recyclerView){
        return new RestaurantListObserver(context,restaurantListAdapter,recyclerView,(RestaurantListObserver.LoadDataListener)mActivity);
    }

}
