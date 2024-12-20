package br.com.ecometric.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.ecometric.controller.RelatorioController;
import br.com.ecometric.model.Relatorio;
import org.springframework.data.domain.Pageable;

@Component
public class RelatorioModelAssembler implements RepresentationModelAssembler<Relatorio, EntityModel<Relatorio>> {

    @Override
    public EntityModel<Relatorio> toModel(Relatorio relatorio) {
        return EntityModel.of(relatorio,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RelatorioController.class).show(relatorio.getIdRelatorio())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RelatorioController.class).index(Pageable.unpaged())).withRel("relatorio"));
    }
}
