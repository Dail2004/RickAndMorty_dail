package com.example.RickAndMorty_Dail.data.network.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class LocationModel {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("dimension")
    private String dimension;

    @SerializedName("url")
    private String url;

    @SerializedName("created")
    private String created;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationModel that = (LocationModel) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(dimension, that.dimension) && Objects.equals(url, that.url) && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, dimension, url, created);
    }
}
