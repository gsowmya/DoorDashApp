package assignment.doordash.com.doordashapp.di;

import assignment.doordash.com.doordashapp.activity.RestaurantListActivity;
import dagger.Subcomponent;

/**
 * Created by Sowmya on 1/12/19.
 */


@AppComponent.ActivityScope
@Subcomponent(modules = PresentationModule.class)
public interface PresentationComponent {
    void inject(RestaurantListActivity restaurantListActivity);
}
