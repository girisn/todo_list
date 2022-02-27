package ru.test.todolist.model.responsemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import ru.test.todolist.model.Category;

@JsonRootName("response")
public class CategoryResponse {
    @JsonProperty(value = "category")
    private Category category;

    @JsonProperty(value = "comment")
    private String comment;

    public CategoryResponse() {
    }

    public CategoryResponse(Category category, String comment) {
        this.category = category;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
