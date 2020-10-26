package com.diabumma.openmoviemvvm.adapters;

public class MovieRecyclerAdapter {

    private String title;
    private String year;
    private String imdbId;
    private String type;
    private String posterUrl;

    public MovieRecyclerAdapter(String title, String year, String imdbId, String type, String posterUrl) {
        this.title = title;
        this.year = year;
        this.imdbId = imdbId;
        this.type = type;
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
