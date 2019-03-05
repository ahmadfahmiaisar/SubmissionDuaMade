package com.bismillah.bismillahsub2.api;

import com.bismillah.bismillahsub2.BuildConfig;
import com.bismillah.bismillahsub2.model.ModelResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET(BuildConfig.Now_Play)
    Call<ModelResponseMovie>getPlayingNow(@Query("language") String language);
    @GET(BuildConfig.UpCome)
    Call<ModelResponseMovie>getUpComing(@Query("language") String language);
    @GET(BuildConfig.Search)
    Call<ModelResponseMovie>getSearchMovie(@Query("query") String query);
}