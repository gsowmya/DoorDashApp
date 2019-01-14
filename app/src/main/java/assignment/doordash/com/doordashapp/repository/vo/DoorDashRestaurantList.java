package assignment.doordash.com.doordashapp.repository.vo;

import java.util.List;

/**
 * Created by Sowmya on 1/12/19.
 */

public class DoorDashRestaurantList {
    public List<DoorDashRestaurant> getDoorDashRestaurantList() {
        return doorDashRestaurantList;
    }

    public void setDoorDashRestaurantList(List<DoorDashRestaurant> doorDashRestaurantList) {
        this.doorDashRestaurantList = doorDashRestaurantList;
    }

    private List<DoorDashRestaurant> doorDashRestaurantList;
}
