package ru.test.todolist.model.responsemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import ru.test.todolist.model.Status;

@JsonRootName("response")
public class StatusResponse {
    @JsonProperty(value = "status")
    private Status status;

    @JsonProperty(value = "comment")
    private String comment;

    public StatusResponse() {
    }

    public StatusResponse(Status status, String comment) {
        this.status = status;
        this.comment = comment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
