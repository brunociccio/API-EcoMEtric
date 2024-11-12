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
import br.com.ecometric.assembler.ProjetosModelAssembler;
import br.com.ecometric.model.Projetos;
import br.com.ecometric.repository.ProjetosRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/projetos")
@Tag(name = "Projetos", description = "APIs relacionadas aos projetos")
@Slf4j
public class ProjetosController {

    @Autowired
    private ProjetosRepository repository;

    @Autowired
    private ProjetosModelAssembler assembler;

    @Autowired
    private PagedResourcesAssembler<Projetos> pagedAssembler;

    @GetMapping
    @Operation(summary = "Listar todos os Projetos")
    public PagedModel<EntityModel<Projetos>> index(@ParameterObject Pageable pageable) {
        Page<Projetos> page = repository.findAll(pageable);
        return pagedAssembler.toModel(page, assembler);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar Projeto por ID")
    public EntityModel<Projetos> show(@PathVariable Long id) {
        var projeto = repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Projeto não encontrado"));
        return assembler.toModel(projeto);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Criar um novo Projeto")
    public ResponseEntity<EntityModel<Projetos>> create(@RequestBody @Valid Projetos projeto) {
        repository.save(projeto);
        var entityModel = assembler.toModel(projeto);
        return ResponseEntity.created(entityModel.getRequiredLink("self").toUri()).body(entityModel);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar um Projeto")
    public ResponseEntity<EntityModel<Projetos>> update(@PathVariable Long id, @RequestBody @Valid Projetos projeto) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Projeto não encontrado"));
        projeto.setIdProjeto(id);
        repository.save(projeto);
        return ResponseEntity.ok(assembler.toModel(projeto));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Excluir um Projeto")
    public void destroy(@PathVariable Long id) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Projeto não encontrado"));
        repository.deleteById(id);
    }
}
