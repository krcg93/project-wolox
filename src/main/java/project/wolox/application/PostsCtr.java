package project.wolox.application;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.modelmapper.ModelMapper;
import project.wolox.domain.service.UsersService;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@Controller("/balance-transactio")
public class PostsCtr {

    @Inject
    private ModelMapper mapper;

    @Inject
    private UsersService usersService;


}
