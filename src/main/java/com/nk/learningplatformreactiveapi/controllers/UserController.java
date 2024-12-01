package com.nk.learningplatformreactiveapi.controllers;

import com.nk.learningplatformreactiveapi.dto.UserDTO;
import com.nk.learningplatformreactiveapi.entities.UserEntity;
import com.nk.learningplatformreactiveapi.services.ICourseService;
import com.nk.learningplatformreactiveapi.services.IUserService;
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
@RequestMapping("/users")
// @RequiredArgsConstructor
public class UserController {

    private final IUserService service;

    // @Qualifier("userModelMapper")
    private final ModelMapper modelMapper;

    public UserController(IUserService service, @Qualifier("userModelMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public Flux<UserDTO> readAll() {
        return service.readAll()
                .map(this::convertToDTO)
                .onErrorResume(e -> Flux.error(new IllegalStateException("Error retrieving users", e)));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserDTO>> readById(@PathVariable("id") Integer id) {
        return service.readById(id)
                .map(entity -> ResponseEntity.ok(convertToDTO(entity)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping
    public Mono<ResponseEntity<UserDTO>> create(@Valid @RequestBody UserDTO dto) {
        return service.save(convertToEntity(dto))
                .map(savedEntity -> ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedEntity)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserDTO>> update(@PathVariable("id") Integer id, @Valid @RequestBody UserDTO dto) {
        return service.readById(id) // Validate existence
                .flatMap(existing -> {
                    dto.setUserId(id);
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

    private UserDTO convertToDTO(UserEntity entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    private UserEntity convertToEntity(UserDTO dto) {
        return modelMapper.map(dto, UserEntity.class);
    }

}
