package com.kukroid.newscorp.presenter;

import com.kukroid.newscorp.Interface.HomeFragmentContract;
import com.kukroid.newscorp.model.News;
import com.kukroid.newscorp.network.NewsDataIntractor;

import java.util.List;

/**
 * Created by kukresa on 12/28/2018.
 */

public class HomeFragmentPresenter implements HomeFragmentContract.NewsInterface {

    private HomeFragmentContract viewContract;

    public HomeFragmentPresenter(HomeFragmentContract viewContract) {
        this.viewContract = viewContract;
    }

    public void swipeToRefresh() {

        if(viewContract.checkNetworkAvailability()) {
            new NewsDataIntractor(this).getNewsData();
            viewContract.refreshOnSwipe();
        }else {
            viewContract.onOffline();
        }

    }


    public void getNewsData() {
        if(viewContract.checkNetworkAvailability()) {
            new NewsDataIntractor(this).getNewsData();
        }else {
            viewContract.onOffline();
        }

    }

    @Override
    public void onFinished(List<News> body) {
        viewContract.setDataToRecyclerView(body);

    }

    @Override
    public void onResponseFailure()
    {
        viewContract.onFail();
    }
}
