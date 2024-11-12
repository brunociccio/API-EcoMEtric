package br.com.ecometric.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.ecometric.controller.CadastroController;
import br.com.ecometric.model.Cadastro;
import org.springframework.data.domain.Pageable;

@Component
public class CadastroModelAssembler implements RepresentationModelAssembler<Cadastro, EntityModel<Cadastro>> {

    @Override
    public EntityModel<Cadastro> toModel(Cadastro cadastro) {
        return EntityModel.of(cadastro,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CadastroController.class).show(cadastro.getIdCadastro())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CadastroController.class).index(Pageable.unpaged())).withRel("cadastro"));
    }
}
