package com.nk.learningplatformreactiveapi.controllers;

import com.nk.learningplatformreactiveapi.dto.CourseDTO;
import com.nk.learningplatformreactiveapi.entities.CourseEntity;
import com.nk.learningplatformreactiveapi.services.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/courses")
// @RequiredArgsConstructor // manual injection by docker conflicts
public class CourseController {

    private final ICourseService service;

    // @Qualifier("courseModelMapper")
    private final ModelMapper modelMapper;

    public CourseController(ICourseService service, @Qualifier("courseModelMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public Flux<CourseDTO> readAll() {
        return service.readAll()
                .map(this::convertToDTO)
                .onErrorResume(e -> Flux.error(new IllegalStateException("Error retrieving courses", e)));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CourseDTO>> readById(@PathVariable("id") Integer id) {
        return service.readById(id)
                .map(entity -> ResponseEntity.ok(convertToDTO(entity)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping
    public Mono<ResponseEntity<CourseDTO>> create(@Valid @RequestBody CourseDTO dto) {
        return service.save(convertToEntity(dto))
                .map(savedEntity -> ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedEntity)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<CourseDTO>> update(@PathVariable("id") Integer id, @Valid @RequestBody CourseDTO dto) {
        return service.readById(id) // Validate existence
                .flatMap(existing -> {
                    dto.setCourseId(id);
                    return service.update(convertToEntity(dto), id)
                            .map(updatedEntity -> ResponseEntity.ok(convertToDTO(updatedEntity)));
                })
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Integer id) {
        return service.readById(id)
                .flatMap(existing -> service.delete(id)
                        .then(Mono.just(ResponseEntity.noContent().<Void>build())))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    private CourseDTO convertToDTO(CourseEntity entity) {
        return modelMapper.map(entity, CourseDTO.class);
    }

    private CourseEntity convertToEntity(CourseDTO dto) {
        return modelMapper.map(dto, CourseEntity.class);
    }

}
