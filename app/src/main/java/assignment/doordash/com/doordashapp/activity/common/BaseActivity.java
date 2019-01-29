package assignment.doordash.com.doordashapp.activity.common;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import assignment.doordash.com.doordashapp.MyApplication;
import assignment.doordash.com.doordashapp.di.AppComponent;
import assignment.doordash.com.doordashapp.di.PresentationComponent;
import assignment.doordash.com.doordashapp.di.PresentationModule;

/**
 * Created by Sowmya on 1/12/19.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private boolean mIsInjectorUsed;
    PresentationComponent presentationComponent;

    @UiThread
    public PresentationComponent getPresentationComponent() {
        if (mIsInjectorUsed) {
            throw new RuntimeException("there is no need to use injector more than once");
        }
        mIsInjectorUsed = true;
        presentationComponent =  getApplicationComponent()
                .newPresentationComponent(new PresentationModule(this,getView()));
        return presentationComponent;
    }

    private AppComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }

    public abstract View getView();

    public void releasePresentationComponent(){
        presentationComponent = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePresentationComponent();
    }
}