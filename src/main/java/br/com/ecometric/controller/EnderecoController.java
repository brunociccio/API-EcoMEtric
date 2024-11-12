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
import br.com.ecometric.assembler.EnderecoModelAssembler;
import br.com.ecometric.model.Endereco;
import br.com.ecometric.repository.EnderecoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/endereco")
@Tag(name = "Endereco", description = "APIs relacionadas ao endereço")
@Slf4j
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private EnderecoModelAssembler assembler;

    @Autowired
    private PagedResourcesAssembler<Endereco> pagedAssembler;

    @GetMapping
    @Operation(summary = "Listar todos os Endereços")
    public PagedModel<EntityModel<Endereco>> index(@ParameterObject Pageable pageable) {
        Page<Endereco> page = repository.findAll(pageable);
        return pagedAssembler.toModel(page, assembler);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar Endereço por ID")
    public EntityModel<Endereco> show(@PathVariable Long id) {
        var endereco = repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Endereço não encontrado"));
        return assembler.toModel(endereco);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Criar um novo Endereço")
    public ResponseEntity<EntityModel<Endereco>> create(@RequestBody @Valid Endereco endereco) {
        repository.save(endereco);
        var entityModel = assembler.toModel(endereco);
        return ResponseEntity.created(entityModel.getRequiredLink("self").toUri()).body(entityModel);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar um Endereço")
    public ResponseEntity<EntityModel<Endereco>> update(@PathVariable Long id, @RequestBody @Valid Endereco endereco) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Endereço não encontrado"));
        endereco.setIdEndereco(id);
        repository.save(endereco);
        return ResponseEntity.ok(assembler.toModel(endereco));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Excluir um Endereço")
    public void destroy(@PathVariable Long id) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Endereço não encontrado"));
        repository.deleteById(id);
    }
}
