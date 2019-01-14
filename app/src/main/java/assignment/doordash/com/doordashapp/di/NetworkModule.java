package assignment.doordash.com.doordashapp.di;

import javax.inject.Named;
import javax.inject.Singleton;

import assignment.doordash.com.doordashapp.repository.webservices.DoorDashApi;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sowmya on 1/11/19.
 */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    Retrofit providesRetrofit(@Named("doordash-production") String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Named("doordash-production")
    @Provides
    String provideBaseURL(){
        return "https://api.doordash.com";
    }

    @Singleton
    @Provides
    DoorDashApi providesDoorDashApi(Retrofit retrofit){
        return retrofit.create(DoorDashApi.class);
    }

}
