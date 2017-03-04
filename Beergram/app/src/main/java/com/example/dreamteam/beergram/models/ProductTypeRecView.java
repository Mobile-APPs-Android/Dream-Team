package com.example.dreamteam.beergram.models;

public final class ProductTypeRecView {
    private String productName;
    private int productPictureId;

    public ProductTypeRecView(String productName, int productPictureId){
        this.setProductName(productName);
        this.setProductPictureId(productPictureId);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPictureId() {
        return productPictureId;
    }

    public void setProductPictureId(int productPictureId) {
        this.productPictureId = productPictureId;
    }
}
