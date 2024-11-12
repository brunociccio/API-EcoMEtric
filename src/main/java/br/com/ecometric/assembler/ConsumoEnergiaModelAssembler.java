package br.com.ecometric.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.ecometric.controller.ConsumoEnergiaController;
import br.com.ecometric.model.ConsumoEnergia;
import org.springframework.data.domain.Pageable;

@Component
public class ConsumoEnergiaModelAssembler implements RepresentationModelAssembler<ConsumoEnergia, EntityModel<ConsumoEnergia>> {

    @Override
    public EntityModel<ConsumoEnergia> toModel(ConsumoEnergia consumoEnergia) {
        return EntityModel.of(consumoEnergia,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoEnergiaController.class).show(consumoEnergia.getIdConsumoEnergia())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoEnergiaController.class).index(Pageable.unpaged())).withRel("consumoEnergia"));
    }
}
