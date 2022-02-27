package ru.test.todolist.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.test.todolist.model.Category;
import ru.test.todolist.model.Element;
import ru.test.todolist.model.Status;
import ru.test.todolist.model.requestmodel.AddElementRequest;
import ru.test.todolist.model.responsemodel.ElementResponse;
import ru.test.todolist.model.responsemodel.ElementResponseList;
import ru.test.todolist.repository.ElementRepository;
import ru.test.todolist.repository.StatusRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ElementController {
    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Функция для создания нового элемента списка
     *
     * @param request json, хранящий в себе имя нового элемента, его категорию, статус и описание.
     *                description - не обязательный параметр, status по умолчанию - Draft
     * @return id созданного элемента
     */
    @PostMapping("/addelement")
    public ResponseEntity<String> addElement(@RequestBody AddElementRequest request) throws JsonProcessingException {
        if (request == null || StringUtils.isEmpty(request.getName())
                || StringUtils.isEmpty(request.getCategoryName()))
            return ResponseEntity.badRequest().body("Укажите поля name и category");

        Element element = new Element();
        element.setId(UUID.randomUUID().toString());
        element.setName(request.getName());
        element.setDescription(request.getDescription());
        element.setStatus(new Status(request.getStatusName()));
        element.setCategory(new Category(request.getCategoryName()));

        Element savedElement = null;
        Long startNano = System.nanoTime();
        try {
            savedElement = elementRepository.save(element);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Убедитесь, что статус/категория нового элемента существуют");
        }

        Long endNano = System.nanoTime();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("querytime", (endNano - startNano) / 1000000 + "ms");

        return ResponseEntity.ok().headers(httpHeaders).body(objectMapper.writeValueAsString(new ElementResponse(savedElement)));
    }

    /**
     * Возвращает список элементов, хранящийся в базе данных
     *
     * @param limitParam    предельное количество возвращаемых элементов
     * @param pageParam     страница базы данных
     * @param statusParam   статус для выборки данных по статусу
     * @return список элементов
     */
    @GetMapping("/getelements")
    public ResponseEntity<String> getList(@RequestParam(value = "limit", required = false) String limitParam,
                                          @RequestParam(value = "page", required = false) String pageParam,
                                          @RequestParam(value = "status", required = false) String statusParam) throws JsonProcessingException {
        if ((!StringUtils.isEmpty(limitParam) && !parseInteger(limitParam))
                || (!StringUtils.isEmpty(pageParam) && !parseInteger(pageParam)))
            return ResponseEntity.badRequest().body("Параметры limit / page должны иметь целочисленное значение");

        int limit = (StringUtils.isEmpty(limitParam)) ? 100 : Integer.parseInt(limitParam);
        int page = (StringUtils.isEmpty(pageParam)) ? 0 : Integer.parseInt(pageParam);

        Pageable pageable = PageRequest.of(page, limit, Sort.by("category"));
        List<Element> elementList = null;

        Long startNano = System.nanoTime();
        if (StringUtils.isEmpty(statusParam))
            elementList = elementRepository.findAll(pageable).toList();
        else {
            Optional<Status> status = statusRepository.findById(statusParam);
            if (status.isPresent())
                elementList = elementRepository.findByStatus(status.get(), pageable);
        }
        Long endNano = System.nanoTime();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("querytime", (endNano - startNano) / 1000000 + "ms");

        return ResponseEntity.ok().headers(httpHeaders).body(objectMapper.writeValueAsString(new ElementResponseList(elementList)));
    }

    @PostMapping("/changeelement")
    public ResponseEntity<String> changeElement(@RequestBody AddElementRequest request) throws JsonProcessingException {
        if (request.getId() == null)
            return ResponseEntity.badRequest().body("Укажите id для идентификации элемента");

        Long startNano = System.nanoTime();
        Optional<Element> elementById = elementRepository.findById(request.getId());
        Long endNano = System.nanoTime();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("querytime", (endNano - startNano) / 1000000 + "ms");

        if (elementById.isPresent()) {
            Element element = elementById.get();
            if (request.getName() != null)
                element.setName(request.getName());
            if (request.getCategoryName() != null)
                element.setCategory(new Category(request.getCategoryName()));
            if (request.getDescription() != null)
                element.setDescription(request.getDescription());
            if (request.getStatusName() != null)
                element.setStatus(new Status(request.getStatusName()));

            Long saveStart = System.nanoTime();
            try {
                element = elementRepository.save(element);
            } catch (Exception ex) {
                return ResponseEntity.badRequest().body("Убедитесь, что статус/категория нового элемента существуют");
            }
            Long saveEnd = System.nanoTime();

            httpHeaders.add("savequerytime", (saveEnd - saveStart) / 1000000 + "ms");

            return ResponseEntity.ok().headers(httpHeaders).body(objectMapper.writeValueAsString(new ElementResponse(element)));
        }

        return ResponseEntity.ok().headers(httpHeaders).body(objectMapper.writeValueAsString(new ElementResponse()));
    }

    private boolean parseInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
