package com.wvs.shoppercrux.ProductDescription;

/**
 * Created by JUNED on 6/16/2016.
 */
public class GetDataAdapter {

    public String productName;
    public String productImage;
    private String productPrice;
    private String productDesription;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = "http://prachodayat.in/shoppercrux/image/"+productImage;
    }

    public String getProductDesription() {
        return productDesription;
    }

    public void setProductDesription(String productDesription) {
        this.productDesription = productDesription;
    }
//    public void setImageServerUrl(String imageServerUrl) {
//        this.ImageServerUrl = "http://prachodayat.in/shoppercrux/image/"+imageServerUrl;
//    }




}
