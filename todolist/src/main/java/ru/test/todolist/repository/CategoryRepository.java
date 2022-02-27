package ru.test.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.todolist.model.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
