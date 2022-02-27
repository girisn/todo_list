package ru.test.todolist.model.responsemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import ru.test.todolist.model.Status;

import java.util.List;

@JsonRootName("response")
public class StatusResponseList {
    @JsonProperty(value = "statuslist")
    private List<Status> statusList;

    public StatusResponseList() {
    }

    public StatusResponseList(List<Status> statusList) {
        this.statusList = statusList;
    }

    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }
}
