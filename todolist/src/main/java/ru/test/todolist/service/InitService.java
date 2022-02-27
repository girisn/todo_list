package ru.test.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.test.todolist.model.Category;
import ru.test.todolist.model.Status;
import ru.test.todolist.repository.CategoryRepository;
import ru.test.todolist.repository.StatusRepository;

import javax.annotation.PostConstruct;

@Service
public class InitService {
    @Value("${test.todolist.init.db}")
    private String initDb;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StatusRepository statusRepository;

    @PostConstruct
    public void init() {
        if (initDb.equals("true")) {
            initCategories();
            initStatuses();
        }
    }

    private void initCategories() {
        Category personal = new Category("Personal", "Personal category");
        Category work = new Category("Work", "Work category");

        categoryRepository.save(personal);
        categoryRepository.save(work);
    }

    private void initStatuses() {
        Status draft = new Status("Draft", "Draft status");
        Status todo = new Status("Todo", "Todo status");
        Status inProgress = new Status("InProgress", "InProgress status");
        Status done = new Status("Done", "Done status");

        statusRepository.save(draft);
        statusRepository.save(todo);
        statusRepository.save(inProgress);
        statusRepository.save(done);
    }
}
