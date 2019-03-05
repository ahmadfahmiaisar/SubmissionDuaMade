package com.bismillah.bismillahsub2.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelResponseMovie {
    @SerializedName("results")
    private ArrayList<ItemResponseMovie> results;

    @SerializedName("page")
    private int pages;

    @SerializedName("total_results")
    private int total_results;

    @SerializedName("total_pages")
    private int total_page;

    public ArrayList<ItemResponseMovie> getResults() {
        return results;
    }

}
