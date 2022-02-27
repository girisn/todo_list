package ru.test.todolist.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.todolist.model.Element;
import ru.test.todolist.model.Status;

import java.util.List;
import java.util.Optional;

public interface ElementRepository extends JpaRepository<Element, String> {
    List<Element> findByStatus(Status status, Pageable pageable);

    Optional<Element> findByName(String name);
}
