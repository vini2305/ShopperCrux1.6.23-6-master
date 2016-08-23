package com.wvs.shoppercrux.CategoryDrawerItems;

import com.google.gson.annotations.SerializedName;

public class ItemObject {

    @SerializedName("name")
    private String songTitle;
    @SerializedName("category_id")
    private String songYear;


    public ItemObject(String songTitle, String songYear, String songAuthor) {
        this.songTitle = songTitle;
        this.songYear = songYear;

    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongYear() {
        return songYear;
    }


}
