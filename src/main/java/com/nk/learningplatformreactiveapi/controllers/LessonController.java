package com.nk.learningplatformreactiveapi.controllers;

import com.nk.learningplatformreactiveapi.dto.LessonDTO;
import com.nk.learningplatformreactiveapi.entities.LessonEntity;
import com.nk.learningplatformreactiveapi.services.ILessonService;
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
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final ILessonService service;

    @Qualifier("lessonModelMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public Flux<LessonDTO> readAll() {
        return service.readAll()
                .map(this::convertToDTO)
                .onErrorResume(e -> Flux.error(new IllegalStateException("Error retrieving lessons", e)));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<LessonDTO>> readById(@PathVariable("id") Integer id) {
        return service.readById(id)
                .map(entity -> ResponseEntity.ok(convertToDTO(entity)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping
    public Mono<ResponseEntity<LessonDTO>> create(@Valid @RequestBody LessonDTO dto) {
        return service.save(convertToEntity(dto))
                .map(savedEntity -> ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedEntity)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<LessonDTO>> update(@PathVariable("id") Integer id, @Valid @RequestBody LessonDTO dto) {
        return service.readById(id) // Validate existence
                .flatMap(existing -> {
                    dto.setLessonId(id);
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

    private LessonDTO convertToDTO(LessonEntity entity) {
        return modelMapper.map(entity, LessonDTO.class);
    }

    private LessonEntity convertToEntity(LessonDTO dto) {
        return modelMapper.map(dto, LessonEntity.class);
    }

}
