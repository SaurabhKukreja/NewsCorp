package com.kukroid.newscorp.network;

import com.kukroid.newscorp.model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kukresa on 12/26/2018.
 */

public interface DataServiceInterface {

    @GET("/photos")
    Call<List<News>> getNews();

}
