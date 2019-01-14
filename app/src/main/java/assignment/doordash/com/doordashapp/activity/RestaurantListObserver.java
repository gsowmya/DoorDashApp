package assignment.doordash.com.doordashapp.activity;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import assignment.doordash.com.doordashapp.activity.common.EndlessRecyclerViewScrollListener;
import assignment.doordash.com.doordashapp.activity.dataholders.ListDataHolder;
import assignment.doordash.com.doordashapp.activity.dataholders.RestaurantListItem;

/**
 * Created by Sowmya on 1/13/19.
 */

public class RestaurantListObserver implements Observer<ListDataHolder> {

    public static final String TAG = RestaurantListObserver.class.getName();

    RecyclerView recyclerView;

    RestaurantListAdapter restaurantListAdapter;

    Context mContext;

    LoadDataListener loadDataListener;

    private EndlessRecyclerViewScrollListener scrollListener;

    public RestaurantListObserver(Context mContext,RestaurantListAdapter restaurantListAdapter,RecyclerView recyclerView,LoadDataListener loadDataListener){
        this.mContext = mContext;
        this.recyclerView = recyclerView;
        this.restaurantListAdapter = restaurantListAdapter;
        this.loadDataListener = loadDataListener;
        setLayoutAndRegisterListeners();
    }

    private void setLayoutAndRegisterListeners(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        setAdapterAndLayoutToRecyclerView(linearLayoutManager);
        registerEndlessScrollListener(linearLayoutManager);
    }

    private void setAdapterAndLayoutToRecyclerView(LinearLayoutManager linearLayoutManager){
        recyclerView.setAdapter(restaurantListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void registerEndlessScrollListener(LinearLayoutManager linearLayoutManager){
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextDataFromApi(totalItemsCount);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }

    @Override
    public void onChanged(@Nullable ListDataHolder listDataHolder) {
        List<RestaurantListItem> listItems = restaurantListAdapter.getListDataHolder().getRestaurantListItemList();
        if(listItems!=null) {
            listItems.addAll(listDataHolder.getRestaurantListItemList());
        }
        restaurantListAdapter.notifyDataSetChanged();
    }

    // Append the next page of data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void loadNextDataFromApi(int offset) {
        loadDataListener.loadMoreByOffset(offset);
    }

    public interface LoadDataListener{
        void loadMoreByOffset(int offset);
    }
}
