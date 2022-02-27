package ru.test.todolist.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.test.todolist.model.Category;
import ru.test.todolist.model.requestmodel.StatusCategoryRequest;
import ru.test.todolist.model.responsemodel.CategoryResponse;
import ru.test.todolist.model.responsemodel.CategoryResponseList;
import ru.test.todolist.model.responsemodel.ElementResponse;
import ru.test.todolist.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/addcategory")
    private ResponseEntity<String> addCategory(@RequestBody StatusCategoryRequest request) throws JsonProcessingException {
        if (StringUtils.isEmpty(request.getName()))
            return ResponseEntity.badRequest().body("Укажите значение ключевого поля name");

        Long startNano = System.nanoTime();
        Optional<Category> categoryFromDb = categoryRepository.findById(request.getName());
        if (categoryFromDb.isPresent())
            return ResponseEntity.badRequest().body(objectMapper.writeValueAsString(new CategoryResponse(categoryFromDb.get(), "Категория уже существует")));
        Category category = new Category(request.getName(), request.getDescription());
        categoryRepository.save(category);
        Long endNano = System.nanoTime();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("querytime", (endNano - startNano) / 1000000 + "ms");

        return ResponseEntity.ok().headers(httpHeaders).body(objectMapper.writeValueAsString(new CategoryResponse(category, "OK")));
    }

    @GetMapping("/getcategories")
    private ResponseEntity<String> getCategories() throws JsonProcessingException {
        Long startNano = System.nanoTime();
        List<Category> listFromDb = categoryRepository.findAll();
        Long endNano = System.nanoTime();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("querytime", (endNano - startNano) / 1000000 + "ms");

        return ResponseEntity.ok().headers(httpHeaders).body(objectMapper.writeValueAsString(new CategoryResponseList(listFromDb)));
    }

    @PostMapping("/changecategory")
    private ResponseEntity<String> changeCategory(@RequestBody StatusCategoryRequest request) throws JsonProcessingException {
        if (StringUtils.isEmpty(request.getName()))
            return ResponseEntity.badRequest().body("Укажите значение ключевого поля name");

        Long startNano = System.nanoTime();
        Optional<Category> fromDb = categoryRepository.findById(request.getName());
        Category category = null;
        if (fromDb.isPresent()) {
            category = fromDb.get();
            category.setDescription(request.getDescription());
            categoryRepository.save(category);
        }
        Long endNano = System.nanoTime();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("querytime", (endNano - startNano) / 1000000 + "ms");

        if (fromDb.isPresent())
            return ResponseEntity.ok().headers(httpHeaders).body(objectMapper.writeValueAsString(new CategoryResponse(category, "OK")));
        return ResponseEntity.ok().headers(httpHeaders).body(objectMapper.writeValueAsString(new CategoryResponse(null, "Категория не найдена")));
    }
}
