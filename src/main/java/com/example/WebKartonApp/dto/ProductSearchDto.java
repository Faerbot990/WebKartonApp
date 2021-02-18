package com.example.WebKartonApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductSearchDto {
    public List<Integer> getPrices() {
        return prices;
    }

    public void setPrices(List<Integer> prices) {
        this.prices = prices;
    }

    public List<String> getProductName() {
        return productName;
    }

    public void setProductName(List<String> productName) {
        this.productName = productName;
    }

    public List<String> getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(List<String> productCategory) {
        this.productCategory = productCategory;
    }




    List<Integer> prices;
    List<String> productName;
    List<String> productCategory;

}
