package br.com.ecometric.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.ecometric.controller.LoginController;
import br.com.ecometric.model.Login;
import org.springframework.data.domain.Pageable;

@Component
public class LoginModelAssembler implements RepresentationModelAssembler<Login, EntityModel<Login>> {

    @Override
    public EntityModel<Login> toModel(Login login) {
        return EntityModel.of(login,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LoginController.class).show(login.getIdLogin())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LoginController.class).index(Pageable.unpaged())).withRel("login"));
    }
}
