package com.kukroid.newscorp.network;

import com.kukroid.newscorp.Interface.HomeFragmentContract;
import com.kukroid.newscorp.model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kukresa on 12/28/2018.
 */

public class NewsDataIntractor  {


    DataServiceInterface dataServiceInterface;
    private HomeFragmentContract.NewsInterface newsInterface;

    public NewsDataIntractor(HomeFragmentContract.NewsInterface newsInterface) {

        this.newsInterface = newsInterface;
        dataServiceInterface = API.getAPIInstance().create(DataServiceInterface.class);
    }

    public void getNewsData(){

        Call<List<News>> invokeService = dataServiceInterface.getNews();
        invokeService.enqueue(dataCallbacks);

    }

    Callback<List<News>> dataCallbacks = new Callback<List<News>>() {
        @Override
        public void onResponse(Call<List<News>> call, Response<List<News>> response) {
            newsInterface.onFinished(response.body());


        }

        @Override
        public void onFailure(Call<List<News>> call, Throwable throwable) {
            newsInterface.onResponseFailure();

        }
    };


}
