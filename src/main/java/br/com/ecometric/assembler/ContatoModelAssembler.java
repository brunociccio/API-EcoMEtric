package br.com.ecometric.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.ecometric.controller.ContatoController;
import br.com.ecometric.model.Contato;
import org.springframework.data.domain.Pageable;

@Component
public class ContatoModelAssembler implements RepresentationModelAssembler<Contato, EntityModel<Contato>> {

    @Override
    public EntityModel<Contato> toModel(Contato contato) {
        return EntityModel.of(contato,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ContatoController.class).show(contato.getIdContato())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ContatoController.class).index(Pageable.unpaged())).withRel("contato"));
    }
}
