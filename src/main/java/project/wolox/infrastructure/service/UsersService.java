package project.wolox.infrastructure.service;

import org.modelmapper.TypeToken;
import project.wolox.domain.model.users.Users;
import project.wolox.domain.service.dependency.UsersI;
import project.wolox.infrastructure.client.database.Firebase;
import project.wolox.infrastructure.client.rest.ServicesRest;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class UsersService implements UsersI {

    @Inject
    ServicesRest servicesRest;

    @Inject
    Firebase firebase;

    @Inject
    private ModelMapper mapper;

    @Override
    public Mono<List<Users>> getUsers() {
        return servicesRest.getUsers()
                .map(users -> mapper.map(users, new TypeToken<List<Users>>() {
                }.getType()));
    }

}
