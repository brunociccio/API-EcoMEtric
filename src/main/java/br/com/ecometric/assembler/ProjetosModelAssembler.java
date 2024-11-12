package br.com.ecometric.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.ecometric.controller.ProjetosController;
import br.com.ecometric.model.Projetos;
import org.springframework.data.domain.Pageable;

@Component
public class ProjetosModelAssembler implements RepresentationModelAssembler<Projetos, EntityModel<Projetos>> {

    @Override
    public EntityModel<Projetos> toModel(Projetos projeto) {
        return EntityModel.of(projeto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProjetosController.class).show(projeto.getIdProjeto())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProjetosController.class).index(Pageable.unpaged())).withRel("projetos"));
    }
}
