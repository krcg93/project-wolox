package project.wolox.domain.service.dependency;

import project.wolox.domain.model.users.Users;
import project.wolox.infrastructure.shared.dto.users.UsersDto;
import reactor.core.publisher.Mono;

import java.util.List;


public interface UsersI {

    Mono<List<Users>> getUsers();

}
