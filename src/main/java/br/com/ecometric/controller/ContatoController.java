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

import br.com.ecometric.assembler.ContatoModelAssembler;
import br.com.ecometric.model.Contato;
import br.com.ecometric.repository.ContatoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/contato")
@Tag(name = "Contato", description = "APIs relacionadas ao contato")
@Slf4j
public class ContatoController {

    @Autowired
    private ContatoRepository repository;

    @Autowired
    private ContatoModelAssembler assembler;

    @Autowired
    private PagedResourcesAssembler<Contato> pagedAssembler;

    @GetMapping
    @Operation(summary = "Listar todos os Contatos")
    public PagedModel<EntityModel<Contato>> index(@ParameterObject Pageable pageable) {
        Page<Contato> page = repository.findAll(pageable);
        return pagedAssembler.toModel(page, assembler);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar Contato por ID")
    public EntityModel<Contato> show(@PathVariable Long id) {
        var contato = repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Contato não encontrado"));
        return assembler.toModel(contato);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Criar um novo Contato")
    public ResponseEntity<EntityModel<Contato>> create(@RequestBody @Valid Contato contato) {
        repository.save(contato);
        var entityModel = assembler.toModel(contato);
        return ResponseEntity.created(entityModel.getRequiredLink("self").toUri()).body(entityModel);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar um Contato")
    public ResponseEntity<EntityModel<Contato>> update(@PathVariable Long id, @RequestBody @Valid Contato contato) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Contato não encontrado"));
        contato.setIdContato(id);
        repository.save(contato);
        return ResponseEntity.ok(assembler.toModel(contato));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Excluir um Contato")
    public void destroy(@PathVariable Long id) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Contato não encontrado"));
        repository.deleteById(id);
    }
}
