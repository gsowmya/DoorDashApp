package assignment.doordash.com.doordashapp.repository.webservices;

import java.util.List;

import assignment.doordash.com.doordashapp.repository.vo.DoorDashRestaurant;
import assignment.doordash.com.doordashapp.repository.vo.DoorDashRestaurantList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sowmya on 1/11/19.
 */

public interface DoorDashApi {

    @GET("/v2/restaurant/?")
    Call<List<DoorDashRestaurant>> getRestaurantList(@Query("lat") String latitude,
                                                     @Query("lng") String longitude,
                                                     @Query("offset") int offset,
                                                     @Query("limit") int limit);
}
