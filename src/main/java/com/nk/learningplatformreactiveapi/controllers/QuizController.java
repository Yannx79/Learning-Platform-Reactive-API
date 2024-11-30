package com.nk.learningplatformreactiveapi.controllers;

import com.nk.learningplatformreactiveapi.dto.QuizDTO;
import com.nk.learningplatformreactiveapi.entities.QuizEntity;
import com.nk.learningplatformreactiveapi.services.IQuizService;
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
@RequestMapping("/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final IQuizService service;

    @Qualifier("quizModelMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public Flux<QuizDTO> readAll() {
        return service.readAll()
                .map(this::convertToDTO)
                .onErrorResume(e -> Flux.error(new IllegalStateException("Error retrieving quizs", e)));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<QuizDTO>> readById(@PathVariable("id") Integer id) {
        return service.readById(id)
                .map(entity -> ResponseEntity.ok(convertToDTO(entity)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping
    public Mono<ResponseEntity<QuizDTO>> create(@Valid @RequestBody QuizDTO dto) {
        return service.save(convertToEntity(dto))
                .map(savedEntity -> ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedEntity)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<QuizDTO>> update(@PathVariable("id") Integer id, @Valid @RequestBody QuizDTO dto) {
        return service.readById(id) // Validate existence
                .flatMap(existing -> {
                    dto.setQuizId(id);
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

    private QuizDTO convertToDTO(QuizEntity entity) {
        return modelMapper.map(entity, QuizDTO.class);
    }

    private QuizEntity convertToEntity(QuizDTO dto) {
        return modelMapper.map(dto, QuizEntity.class);
    }

}
