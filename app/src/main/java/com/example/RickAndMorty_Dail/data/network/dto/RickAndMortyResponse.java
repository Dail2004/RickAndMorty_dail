package com.example.RickAndMorty_Dail.data.network.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RickAndMortyResponse<T> {
    @SerializedName("info")
    private Info info;

    @SerializedName("results")
    private ArrayList<T> results;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public ArrayList<T> getResult() {
        return results;
    }

    public void setResult(ArrayList<T> result) {
        this.results = result;
    }
}
