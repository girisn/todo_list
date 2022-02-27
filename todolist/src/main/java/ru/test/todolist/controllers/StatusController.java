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
import ru.test.todolist.model.Status;
import ru.test.todolist.model.requestmodel.StatusCategoryRequest;
import ru.test.todolist.model.responsemodel.CategoryResponse;
import ru.test.todolist.model.responsemodel.CategoryResponseList;
import ru.test.todolist.model.responsemodel.StatusResponse;
import ru.test.todolist.model.responsemodel.StatusResponseList;
import ru.test.todolist.repository.StatusRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class StatusController {
    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/addstatus")
    private ResponseEntity<String> addStatus(@RequestBody StatusCategoryRequest request) throws JsonProcessingException {
        if (StringUtils.isEmpty(request.getName()))
            return ResponseEntity.badRequest().body("Укажите значение ключевого поля name");

        Long startNano = System.nanoTime();
        Optional<Status> fromDb = statusRepository.findById(request.getName());
        if (fromDb.isPresent())
            return ResponseEntity.badRequest().body(objectMapper.writeValueAsString(new StatusResponse(fromDb.get(), "Категория уже существует")));
        Status status = new Status(request.getName(), request.getDescription());
        statusRepository.save(status);
        Long endNano = System.nanoTime();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("querytime", (endNano - startNano) / 1000000 + "ms");

        return ResponseEntity.ok().headers(httpHeaders).body(objectMapper.writeValueAsString(new StatusResponse(status, "OK")));
    }

    @GetMapping("/getstatuses")
    private ResponseEntity<String> getStatuses() throws JsonProcessingException {
        Long startNano = System.nanoTime();
        List<Status> listFromDb = statusRepository.findAll();
        Long endNano = System.nanoTime();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("querytime", (endNano - startNano) / 1000000 + "ms");

        return ResponseEntity.ok().headers(httpHeaders).body(objectMapper.writeValueAsString(new StatusResponseList(listFromDb)));
    }

    @PostMapping("/changestatus")
    private ResponseEntity<String> changeStatus(@RequestBody StatusCategoryRequest request) throws JsonProcessingException {
        if (StringUtils.isEmpty(request.getName()))
            return ResponseEntity.badRequest().body("Укажите значение ключевого поля name");

        Long startNano = System.nanoTime();
        Optional<Status> fromDb = statusRepository.findById(request.getName());
        Status status = null;
        if (fromDb.isPresent()) {
            status = fromDb.get();
            status.setDescription(request.getDescription());
            statusRepository.save(status);
        }
        Long endNano = System.nanoTime();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("querytime", (endNano - startNano) / 1000000 + "ms");

        if (fromDb.isPresent())
            return ResponseEntity.ok().headers(httpHeaders).body(objectMapper.writeValueAsString(new StatusResponse(status, "OK")));
        return ResponseEntity.ok().headers(httpHeaders).body(objectMapper.writeValueAsString(new StatusResponse(null, "Категория не найдена")));
    }
}
