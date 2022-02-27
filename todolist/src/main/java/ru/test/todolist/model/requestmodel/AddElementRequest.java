package ru.test.todolist.model.requestmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import ru.test.todolist.model.Category;

@JsonRootName("request")
public class AddElementRequest {
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "categoryname")
    private String categoryName;

    @JsonProperty(value = "statusname")
    private String statusName;

    @JsonProperty(value = "description")
    private String description;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
