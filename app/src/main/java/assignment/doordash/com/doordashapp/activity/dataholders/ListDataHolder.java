package assignment.doordash.com.doordashapp.activity.dataholders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sowmya on 1/12/19.
 */

public class ListDataHolder {

    List<RestaurantListItem> restaurantListItemList = new ArrayList<>();
    int errorResource;

    private ListDataHolder(ListDataHolderBuilder builder){
        this.restaurantListItemList = builder.restaurantListItemList;
        this.errorResource = builder.errorResource;
    }

    public List<RestaurantListItem> getRestaurantListItemList() {
        return restaurantListItemList;
    }

    public void setRestaurantListItemList(List<RestaurantListItem> restaurantListItemList) {
        this.restaurantListItemList = restaurantListItemList;
    }

    public int getErrorResource() {
        return errorResource;
    }

    public void setErrorResource(int errorResource) {
        this.errorResource = errorResource;
    }

    public static class ListDataHolderBuilder{
        List<RestaurantListItem> restaurantListItemList = new ArrayList<>();
        int errorResource;

        public ListDataHolderBuilder restaurantListItemList(List<RestaurantListItem> restaurantListItemList) {
            this.restaurantListItemList = restaurantListItemList;
            return this;
        }

        public ListDataHolderBuilder errorResource(int errorResource) {
            this.errorResource = errorResource;
            return this;
        }

        public ListDataHolder build(){
            return new ListDataHolder(this);
        }
    }
}
