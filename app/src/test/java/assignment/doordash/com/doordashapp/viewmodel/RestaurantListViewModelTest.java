package assignment.doordash.com.doordashapp.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.jraska.livedata.TestObserver;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import assignment.doordash.com.doordashapp.activity.dataholders.ListDataHolder;
import assignment.doordash.com.doordashapp.repository.DoorDashRepository;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantListRequest;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantListResponseDao;
import assignment.doordash.com.doordashapp.repository.webservices.DoorDashApi;

import static org.junit.Assert.*;

/**
 * Created by Sowmya on 1/13/19.
 */

@RunWith(MockitoJUnitRunner.class)
public class RestaurantListViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    DoorDashRepository doorDashRepository;

    @Mock
    RestaurantListRequest restaurantListRequest;

    @Mock
    MutableLiveData<RestaurantListResponseDao> restaurantListResponseDaoMutableLiveData;

    RestaurantListViewModel restaurantListViewModel;

    @Before
    public void setUp() throws Exception {
        restaurantListViewModel = new RestaurantListViewModel(doorDashRepository);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getRestaurantList() throws Exception {
        Mockito.when(doorDashRepository.getRestaurantList(restaurantListRequest)).thenReturn(restaurantListResponseDaoMutableLiveData);
        LiveData<ListDataHolder> restrauntList = restaurantListViewModel.getRestaurantList(restaurantListRequest);
        restaurantListResponseDaoMutableLiveData.setValue(new RestaurantListResponseDao(null,null));
        //need to update this library to 1.0.0 and resolve android depedencies to use await() funcion for this test to pass
        TestObserver.test(restrauntList)
                .assertHasValue();
    }

}