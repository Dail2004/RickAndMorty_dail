package com.example.RickAndMorty_Dail.data.network.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class EpisodeModel {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("episode")
    private String episode;

    @SerializedName("air_date")
    private String air_date;

    @SerializedName("url")
    private String url;

    @SerializedName("created")
    private String created;

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EpisodeModel that = (EpisodeModel) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(episode, that.episode) && Objects.equals(air_date, that.air_date) && Objects.equals(url, that.url) && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, episode, air_date, url, created);
    }
}
