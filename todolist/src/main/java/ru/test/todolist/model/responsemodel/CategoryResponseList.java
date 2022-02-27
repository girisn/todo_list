package ru.test.todolist.model.responsemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import ru.test.todolist.model.Category;

import java.util.List;

@JsonRootName("response")
public class CategoryResponseList {
    @JsonProperty(value = "categorylist")
    private List<Category> categoryList;

    public CategoryResponseList() {
    }

    public CategoryResponseList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
