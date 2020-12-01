package net.gfg.retrofitexample;

import android.app.Application;

public class MyApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApiComponent = net.gfg.retrofitexample.DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule("https://restcountries.eu/rest/v2/"))
                .build();
    }

    public ApiComponent getNetComponent() {
        return mApiComponent;
    }
}