package com.isysnext.feedapp.dto;

/**
 * Created by Anuved on 28/08/18.
 */
public class CategoryData {
    public String getCategoryName() {
        return categoryName;
    }

    public CategoryData(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private String categoryName;
}
