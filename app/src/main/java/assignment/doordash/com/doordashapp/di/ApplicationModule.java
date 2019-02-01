package assignment.doordash.com.doordashapp.di;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import assignment.doordash.com.doordashapp.repository.DoorDashRepository;
import assignment.doordash.com.doordashapp.repository.dao.RestaurantListResponseDao;
import assignment.doordash.com.doordashapp.repository.impl.DoorDashRepositoryImpl;
import assignment.doordash.com.doordashapp.repository.webservices.DoorDashApi;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Sowmya on 1/11/19.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    public DoorDashRepository providesDoorDashRepository(Application application,DoorDashApi doorDashApi){
        return new DoorDashRepositoryImpl(application,doorDashApi);
    }

    @Singleton
    @Provides
    @Named("restaurantListLiveData")
    public LiveData<RestaurantListResponseDao> providesRestaurantListLiveData(){
        return new MediatorLiveData<RestaurantListResponseDao>();
    }

    @Provides
    @Singleton
    public Application providesApplication(){
        return mApplication;
    }
}
