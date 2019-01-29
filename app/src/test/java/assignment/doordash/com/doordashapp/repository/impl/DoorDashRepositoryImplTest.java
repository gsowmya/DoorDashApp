package assignment.doordash.com.doordashapp.repository.impl;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;

import com.jraska.livedata.TestObserver;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import assignment.doordash.com.doordashapp.repository.DoorDashRepository;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantListRequest;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantListRequestBuilder;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantListResponseDao;
import assignment.doordash.com.doordashapp.repository.vo.DoorDashRestaurant;
import assignment.doordash.com.doordashapp.repository.webservices.DoorDashApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Created by Sowmya on 1/21/19.
 */

@RunWith(MockitoJUnitRunner.class)
public class DoorDashRepositoryImplTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    DoorDashApi doorDashApi;

    @Mock
    Call<List<DoorDashRestaurant>> retrofitCall;

    DoorDashRepository doorDashRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        doorDashRepository = new DoorDashRepositoryImpl(doorDashApi);
    }

    @Test
    public void getRestaurantList() throws Exception {

        Mockito.when(doorDashApi.getRestaurantList(Mockito.anyString(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt()))
                .thenReturn(retrofitCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<List<DoorDashRestaurant>> callback = (Callback<List<DoorDashRestaurant>>)invocation.getArguments()[0];
                callback.onResponse(retrofitCall, Response.success(getMockRestrauntList()));
                return null;
            }
        }).when(retrofitCall).enqueue(Mockito.any(Callback.class));

        LiveData<RestaurantListResponseDao> restaurantListResponseDaoLiveData=
                doorDashRepository.getRestaurantList(new RestaurantListRequestBuilder()
                    .latitude("1")
                    .longitude("1")
                    .limit(5)
                    .offset(0)
                    .build());

        RestaurantListResponseDao restaurantListResponseDao =
                TestObserver.test(restaurantListResponseDaoLiveData)
                .value();

        assertTrue("verify restraunt name","Thai".equals(restaurantListResponseDao.getData().get(0).getName()));

    }

    private List<DoorDashRestaurant> getMockRestrauntList(){
        DoorDashRestaurant doorDashRestaurant = new DoorDashRestaurant();
        doorDashRestaurant.setName("Thai");
//        doorDashRestaurant.setId(1);
//        doorDashRestaurant.setDeliveryFee(1);
//        doorDashRestaurant.setStatus("status");
//        doorDashRestaurant.setDescription("description");
//        doorDashRestaurant.setBusinessId(1);
//        doorDashRestaurant.setCoverImgUrl("imageURL");
        List<DoorDashRestaurant> list = new ArrayList<>();
        list.add(doorDashRestaurant);
        return  list;
    }

}