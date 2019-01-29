package assignment.doordash.com.doordashapp.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.jraska.livedata.TestObserver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import assignment.doordash.com.doordashapp.activity.dataholders.ListDataHolder;
import assignment.doordash.com.doordashapp.repository.DoorDashRepository;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantDao;
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

    RestaurantListViewModel restaurantListViewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        restaurantListViewModel = new RestaurantListViewModel(doorDashRepository);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getRestaurantList() throws Exception {
        MutableLiveData data = new MutableLiveData();
        Mockito.when(doorDashRepository.getRestaurantList(Mockito.any())).thenReturn(data);
        data.setValue(new RestaurantListResponseDao(getRestrauntDaoListWithMockData(),null));
        LiveData<ListDataHolder> restrauntList = restaurantListViewModel.getRestaurantList(Mockito.any());
        ListDataHolder dataHolder = TestObserver.test(restrauntList)
                .value();
        Assert.assertTrue("validate name","DishnDash".equals(dataHolder.getRestaurantListItemList().get(0).getName()));
    }

    private List<RestaurantDao> getRestrauntDaoListWithMockData(){
        List<RestaurantDao> daoList = new ArrayList<>();
        daoList.add(RestaurantDao.builder().name("DishnDash").build());
        return daoList;
    }



}