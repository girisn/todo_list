package ru.test.todolist.model.responsemodel;

import com.fasterxml.jackson.annotation.JsonRootName;
import ru.test.todolist.model.Element;

@JsonRootName("response")
public class ElementResponse {
    private Element element;

    public ElementResponse() {
    }

    public ElementResponse(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
