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
import br.com.ecometric.assembler.ConsumoEnergiaModelAssembler;
import br.com.ecometric.model.ConsumoEnergia;
import br.com.ecometric.repository.ConsumoEnergiaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/consumoEnergia")
@Tag(name = "ConsumoEnergia", description = "APIs relacionadas ao consumo de energia")
@Slf4j
public class ConsumoEnergiaController {

    @Autowired
    private ConsumoEnergiaRepository repository;

    @Autowired
    private ConsumoEnergiaModelAssembler assembler;

    @Autowired
    private PagedResourcesAssembler<ConsumoEnergia> pagedAssembler;

    @GetMapping
    @Operation(summary = "Listar todos os Consumos de Energia")
    public PagedModel<EntityModel<ConsumoEnergia>> index(@ParameterObject Pageable pageable) {
        Page<ConsumoEnergia> page = repository.findAll(pageable);
        return pagedAssembler.toModel(page, assembler);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar Consumo de Energia por ID")
    public EntityModel<ConsumoEnergia> show(@PathVariable Long id) {
        var consumo = repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Consumo de Energia não encontrado"));
        return assembler.toModel(consumo);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Criar um novo Consumo de Energia")
    public ResponseEntity<EntityModel<ConsumoEnergia>> create(@RequestBody @Valid ConsumoEnergia consumoEnergia) {
        repository.save(consumoEnergia);
        var entityModel = assembler.toModel(consumoEnergia);
        return ResponseEntity.created(entityModel.getRequiredLink("self").toUri()).body(entityModel);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar um Consumo de Energia")
    public ResponseEntity<EntityModel<ConsumoEnergia>> update(@PathVariable Long id, @RequestBody @Valid ConsumoEnergia consumoEnergia) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Consumo de Energia não encontrado"));
        consumoEnergia.setIdConsumoEnergia(id);
        repository.save(consumoEnergia);
        return ResponseEntity.ok(assembler.toModel(consumoEnergia));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Excluir um Consumo de Energia")
    public void destroy(@PathVariable Long id) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Consumo de Energia não encontrado"));
        repository.deleteById(id);
    }
}
