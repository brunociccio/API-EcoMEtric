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
import br.com.ecometric.assembler.RelatorioModelAssembler;
import br.com.ecometric.model.Relatorio;
import br.com.ecometric.repository.RelatorioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/relatorio")
@Tag(name = "Relatorio", description = "APIs relacionadas aos relatórios")
@Slf4j
public class RelatorioController {

    @Autowired
    private RelatorioRepository repository;

    @Autowired
    private RelatorioModelAssembler assembler;

    @Autowired
    private PagedResourcesAssembler<Relatorio> pagedAssembler;

    @GetMapping
    @Operation(summary = "Listar todos os Relatórios")
    public PagedModel<EntityModel<Relatorio>> index(@ParameterObject Pageable pageable) {
        Page<Relatorio> page = repository.findAll(pageable);
        return pagedAssembler.toModel(page, assembler);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar Relatório por ID")
    public EntityModel<Relatorio> show(@PathVariable Long id) {
        var relatorio = repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Relatório não encontrado"));
        return assembler.toModel(relatorio);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Criar um novo Relatório")
    public ResponseEntity<EntityModel<Relatorio>> create(@RequestBody @Valid Relatorio relatorio) {
        repository.save(relatorio);
        var entityModel = assembler.toModel(relatorio);
        return ResponseEntity.created(entityModel.getRequiredLink("self").toUri()).body(entityModel);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar um Relatório")
    public ResponseEntity<EntityModel<Relatorio>> update(@PathVariable Long id, @RequestBody @Valid Relatorio relatorio) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Relatório não encontrado"));
        relatorio.setIdRelatorio(id);
        repository.save(relatorio);
        return ResponseEntity.ok(assembler.toModel(relatorio));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Excluir um Relatório")
    public void destroy(@PathVariable Long id) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Relatório não encontrado"));
        repository.deleteById(id);
    }
}
