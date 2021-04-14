package project.wolox.domain.service;

import project.wolox.domain.model.users.Users;
import project.wolox.domain.service.dependency.UsersI;
import reactor.core.publisher.Mono;

import java.util.List;


public class UsersService {

    private UsersI usersI;

    public UsersService(UsersI usersI) {
        this.usersI = usersI;
    }

    public Mono<List<Users>> getUsers(){
        return  usersI.getUsers()
                .onErrorResume(Mono::error);
    }
}
