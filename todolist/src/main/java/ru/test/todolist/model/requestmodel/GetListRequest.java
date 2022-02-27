package ru.test.todolist.model.requestmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("request")
public class GetListRequest {
    @JsonProperty(value = "statusname")
    private String statusName;

    @JsonProperty(value = "limit")
    private Integer limit;

    @JsonProperty(value = "page")
    private Integer page;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
