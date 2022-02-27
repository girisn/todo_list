package ru.test.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.todolist.model.Status;

public interface StatusRepository extends JpaRepository<Status, String> {
}
