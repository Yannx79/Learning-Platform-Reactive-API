package com.nk.learningplatformreactiveapi.controllers;

import com.nk.learningplatformreactiveapi.dto.EnrollmentDTO;
import com.nk.learningplatformreactiveapi.entities.EnrollmentEntity;
import com.nk.learningplatformreactiveapi.services.IEnrollmentService;
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
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final IEnrollmentService service;

    @Qualifier("enrollmentModelMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public Flux<EnrollmentDTO> readAll() {
        return service.readAll()
                .map(this::convertToDTO)
                .onErrorResume(e -> Flux.error(new IllegalStateException("Error retrieving enrollments", e)));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<EnrollmentDTO>> readById(@PathVariable("id") Integer id) {
        return service.readById(id)
                .map(entity -> ResponseEntity.ok(convertToDTO(entity)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping
    public Mono<ResponseEntity<EnrollmentDTO>> create(@Valid @RequestBody EnrollmentDTO dto) {
        return service.save(convertToEntity(dto))
                .map(savedEntity -> ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedEntity)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<EnrollmentDTO>> update(@PathVariable("id") Integer id, @Valid @RequestBody EnrollmentDTO dto) {
        return service.readById(id) // Validate existence
                .flatMap(existing -> {
                    dto.setEnrollmentId(id);
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

    private EnrollmentDTO convertToDTO(EnrollmentEntity entity) {
        return modelMapper.map(entity, EnrollmentDTO.class);
    }

    private EnrollmentEntity convertToEntity(EnrollmentDTO dto) {
        return modelMapper.map(dto, EnrollmentEntity.class);
    }

}
