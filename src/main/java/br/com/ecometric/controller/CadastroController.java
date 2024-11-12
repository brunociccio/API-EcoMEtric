package br.com.ecometric.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import br.com.ecometric.assembler.CadastroModelAssembler;
import br.com.ecometric.model.Cadastro;
import br.com.ecometric.repository.CadastroRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/cadastro")
@Tag(name = "Cadastro", description = "APIs relacionadas ao cadastro")
@Slf4j
public class CadastroController {

    @Autowired
    private CadastroRepository repository;

    @Autowired
    private CadastroModelAssembler assembler;

    @Autowired
    private PagedResourcesAssembler<Cadastro> pagedAssembler;

    @GetMapping
    @Operation(summary = "Listar todos os Cadastros")
    public PagedModel<EntityModel<Cadastro>> index(@ParameterObject Pageable pageable) {
        Page<Cadastro> page = repository.findAll(pageable);
        return pagedAssembler.toModel(page, assembler);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar Cadastro por ID")
    public EntityModel<Cadastro> show(@PathVariable Long id) {
        var cadastro = repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cadastro não encontrado"));
        return assembler.toModel(cadastro);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Criar um novo Cadastro")
    public ResponseEntity<EntityModel<Cadastro>> create(@RequestBody @Valid Cadastro cadastro) {
        repository.save(cadastro);
        var entityModel = assembler.toModel(cadastro);
        return ResponseEntity.created(entityModel.getRequiredLink("self").toUri()).body(entityModel);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar um Cadastro")
    public ResponseEntity<EntityModel<Cadastro>> update(@PathVariable Long id, @RequestBody @Valid Cadastro cadastro) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cadastro não encontrado"));
        cadastro.setIdCadastro(id);
        repository.save(cadastro);
        return ResponseEntity.ok(assembler.toModel(cadastro));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Excluir um Cadastro")
    public void destroy(@PathVariable Long id) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cadastro não encontrado"));
        repository.deleteById(id);
    }
}
