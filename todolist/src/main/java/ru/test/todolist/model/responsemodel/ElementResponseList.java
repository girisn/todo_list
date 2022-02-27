package ru.test.todolist.model.responsemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import ru.test.todolist.model.Element;

import java.util.List;

@JsonRootName("response")
public class ElementResponseList {
    @JsonProperty(value = "elementlist")
    private List<Element> elementList;

    public ElementResponseList(List<Element> elementList) {
        this.elementList = elementList;
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public void setElementList(List<Element> elementList) {
        this.elementList = elementList;
    }
}
