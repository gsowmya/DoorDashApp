package assignment.doordash.com.doordashapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sowmya on 1/11/19.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class})
public interface AppComponent{

    public PresentationComponent newPresentationComponent(PresentationModule presentationModule);

    /**
     * Created by Sowmya on 1/13/19.
     */

    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    @interface ActivityScope {
    }
}
