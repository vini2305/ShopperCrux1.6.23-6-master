package com.wvs.shoppercrux.Product;

/**
 * Created by JUNED on 6/16/2016.
 */
public class GetDataAdapter {

    public String ImageServerUrl;
    public String ImageTitleName;
    private String SellerID;

    public String getSellerID() {
        return SellerID;
    }

    public String setSellerID(String sellerID) {
        SellerID = sellerID;
        return sellerID;
    }

    public String getImageServerUrl() {
        return ImageServerUrl;
    }

    public void setImageServerUrl(String imageServerUrl) {
        this.ImageServerUrl = "http://prachodayat.in/shoppercrux/image/"+imageServerUrl;
    }

    public String getImageTitleName() {
        return ImageTitleName;
    }

    public void setImageTitleNamee(String Imagetitlename) {
        this.ImageTitleName = Imagetitlename;
    }


}
