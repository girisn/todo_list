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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "status", referencedColumnName = "name", nullable = false,
            foreignKey = @ForeignKey(name = "fk_element_status_name"), columnDefinition = "text")
    private String status;

    @JsonProperty(value = "category")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category", referencedColumnName = "name", nullable = false,
            foreignKey = @ForeignKey(name = "fk_element_category_name"), columnDefinition = "text")
    private String category;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
