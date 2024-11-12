package br.com.ecometric.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.ecometric.assembler.LoginModelAssembler;
import br.com.ecometric.model.Login;
import br.com.ecometric.repository.LoginRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/login")
@Tag(name = "Login", description = "APIs relacionadas ao login")
@Slf4j
public class LoginController {

    @Autowired
    private LoginRepository repository;

    @Autowired
    private LoginModelAssembler assembler;

    @Autowired
    private PagedResourcesAssembler<Login> pagedAssembler;

    @GetMapping
    @Operation(summary = "Listar todos os Logins")
    public PagedModel<EntityModel<Login>> index(@ParameterObject Pageable pageable) {
        Page<Login> page = repository.findAll(pageable);
        return pagedAssembler.toModel(page, assembler);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar Login por ID")
    public EntityModel<Login> show(@PathVariable Long id) {
        var login = repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Login não encontrado"));
        return assembler.toModel(login);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Criar um novo Login")
    public ResponseEntity<EntityModel<Login>> create(@RequestBody @Valid Login login) {
        repository.save(login);
        var entityModel = assembler.toModel(login);
        return ResponseEntity.created(entityModel.getRequiredLink("self").toUri()).body(entityModel);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar um Login")
    public ResponseEntity<EntityModel<Login>> update(@PathVariable Long id, @RequestBody @Valid Login login) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Login não encontrado"));
        login.setIdLogin(id);
        repository.save(login);
        return ResponseEntity.ok(assembler.toModel(login));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Excluir um Login")
    public void destroy(@PathVariable Long id) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Login não encontrado"));
        repository.deleteById(id);
    }
}
