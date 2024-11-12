package br.com.ecometric.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.ecometric.controller.MonitoramentoController;
import br.com.ecometric.model.Monitoramento;
import org.springframework.data.domain.Pageable;

@Component
public class MonitoramentoModelAssembler implements RepresentationModelAssembler<Monitoramento, EntityModel<Monitoramento>> {

    @Override
    public EntityModel<Monitoramento> toModel(Monitoramento monitoramento) {
        return EntityModel.of(monitoramento,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MonitoramentoController.class).show(monitoramento.getIdMonitoramento())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MonitoramentoController.class).index(Pageable.unpaged())).withRel("monitoramento"));
    }
}
