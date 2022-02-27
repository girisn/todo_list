package ru.test.todolist.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "element", schema = "todolist")
public class Element {
    @Id
    @JsonProperty(value = "id")
    @Column(name = "id", columnDefinition = "text")
    private String id;

    @JsonProperty(value = "name")
    @Column(name = "name", columnDefinition = "text")
    private String name;

    @JsonProperty(value = "description")
    @Column(name = "description", columnDefinition = "text")
    private String description;

    @JsonProperty(value = "status")
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "status", referencedColumnName = "name", nullable = false,
            foreignKey = @ForeignKey(name = "fk_element_status_name"), columnDefinition = "text")
    private Status status;

    @JsonProperty(value = "category")
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category", referencedColumnName = "name", nullable = false,
            foreignKey = @ForeignKey(name = "fk_element_category_name"), columnDefinition = "text")
    private Category category;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
