package assignment.doordash.com.doordashapp;

import android.app.Application;

import assignment.doordash.com.doordashapp.di.AppComponent;
import assignment.doordash.com.doordashapp.di.ApplicationModule;
import assignment.doordash.com.doordashapp.di.DaggerAppComponent;
import assignment.doordash.com.doordashapp.di.NetworkModule;


/**
 * Created by Sowmya on 1/12/19.
 */

public class MyApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .networkModule(new NetworkModule())
                    .build();
    }

    public AppComponent getApplicationComponent() {
        return component;
    }
}
