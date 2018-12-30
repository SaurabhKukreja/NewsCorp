package com.kukroid.newscorp.Interface;

import com.kukroid.newscorp.model.News;

import java.util.List;

/**
 * Created by kukresa on 12/28/2018.
 */

public interface HomeFragmentContract {


    void refreshOnSwipe();
    void setDataToRecyclerView(List<News> body);
    void onFail();
    void onOffline();
    boolean checkNetworkAvailability();


    interface NewsInterface{

        void onFinished(List<News> body);
        void onResponseFailure();

    }






}
