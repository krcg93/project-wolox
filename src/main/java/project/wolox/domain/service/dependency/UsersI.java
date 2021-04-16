package project.wolox.domain.service.dependency;

import project.wolox.domain.model.shared.AlbumId;
import project.wolox.domain.model.users.Users;
import project.wolox.domain.model.users.UsersShared;
import reactor.core.publisher.Mono;

import java.util.List;


public interface UsersI {

    Mono<List<Users>> getUsers();

    Mono<List<UsersShared>> getUsersShared(AlbumId albumId);

}
