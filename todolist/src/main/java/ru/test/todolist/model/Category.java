package ru.test.todolist.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category", schema = "todolist")
public class Category {
    @Id
    @JsonProperty(value = "name")
    @Column(name = "name", columnDefinition = "text")
    private String name;

    @JsonProperty(value = "description")
    @Column(name = "description", columnDefinition = "text")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
