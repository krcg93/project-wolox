package project.wolox.application;

import org.modelmapper.TypeToken;
import project.wolox.domain.model.users.Users;
import project.wolox.domain.service.UsersService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.modelmapper.ModelMapper;
import project.wolox.infrastructure.shared.dto.users.UsersDto;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.util.List;

@Controller("/users-services")
public class UsersCtr {

    @Inject
    private ModelMapper mapper;

    @Inject
    private UsersService usersService;

    @Get(value = "users", produces = MediaType.APPLICATION_JSON)
    public Mono<List<UsersDto>> getUsers() {
        return usersService.getUsers()
                .map(users -> mapper.map(users, new TypeToken<List<UsersDto>>() {
                }.getType()));
    }
}
