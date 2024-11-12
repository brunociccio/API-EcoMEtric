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
import br.com.ecometric.assembler.MonitoramentoModelAssembler;
import br.com.ecometric.model.Monitoramento;
import br.com.ecometric.repository.MonitoramentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/monitoramento")
@Tag(name = "Monitoramento", description = "APIs relacionadas ao monitoramento")
@Slf4j
public class MonitoramentoController {

    @Autowired
    private MonitoramentoRepository repository;

    @Autowired
    private MonitoramentoModelAssembler assembler;

    @Autowired
    private PagedResourcesAssembler<Monitoramento> pagedAssembler;

    @GetMapping
    @Operation(summary = "Listar todos os Monitoramentos")
    public PagedModel<EntityModel<Monitoramento>> index(@ParameterObject Pageable pageable) {
        Page<Monitoramento> page = repository.findAll(pageable);
        return pagedAssembler.toModel(page, assembler);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar Monitoramento por ID")
    public EntityModel<Monitoramento> show(@PathVariable Long id) {
        var monitoramento = repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Monitoramento não encontrado"));
        return assembler.toModel(monitoramento);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Criar um novo Monitoramento")
    public ResponseEntity<EntityModel<Monitoramento>> create(@RequestBody @Valid Monitoramento monitoramento) {
        repository.save(monitoramento);
        var entityModel = assembler.toModel(monitoramento);
        return ResponseEntity.created(entityModel.getRequiredLink("self").toUri()).body(entityModel);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar um Monitoramento")
    public ResponseEntity<EntityModel<Monitoramento>> update(@PathVariable Long id, @RequestBody @Valid Monitoramento monitoramento) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Monitoramento não encontrado"));
        monitoramento.setIdMonitoramento(id);
        repository.save(monitoramento);
        return ResponseEntity.ok(assembler.toModel(monitoramento));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Excluir um Monitoramento")
    public void destroy(@PathVariable Long id) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Monitoramento não encontrado"));
        repository.deleteById(id);
    }
}
