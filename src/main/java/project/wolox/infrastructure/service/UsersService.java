package project.wolox.infrastructure.service;

import org.modelmapper.TypeToken;
import project.wolox.domain.model.shared.AlbumId;
import project.wolox.domain.model.users.Users;
import project.wolox.domain.model.users.UsersShared;
import project.wolox.domain.service.dependency.UsersI;
import project.wolox.infrastructure.client.database.Firebase;
import project.wolox.infrastructure.client.rest.ServicesRest;
import org.modelmapper.ModelMapper;
import project.wolox.infrastructure.shared.converter.ConvertUsers;
import project.wolox.infrastructure.shared.dto.shared.AlbumIdDto;
import project.wolox.infrastructure.shared.dto.users.UsersDto;
import project.wolox.infrastructure.shared.dto.users.UsersSharedDto;
import reactor.core.publisher.Flux;
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

    @Override
    public Mono<List<UsersShared>> getUsersShared(AlbumId albumId) {
        return getAlbumsByUser(albumId)
                .zipWhen(albumsList ->
                        Flux.fromIterable(ConvertUsers.mapperAlbums(albumsList))
                                .flatMap(albums ->
                                        getUsersById(albums.getUserId())
                                                .map(usersDto -> {
                                                    return ConvertUsers.mapperUsers(albums, usersDto.get(0));
                                                }))
                                .collectList()
                )
                .map(tuple -> {
                    List<UsersSharedDto> usersSharedDtos = tuple.getT2();
                    return mapper.map(usersSharedDtos, new TypeToken<List<UsersShared>>() {
                    }.getType());
                });

    }

    private Mono<Object> getAlbumsByUser(AlbumId albumId) {
        return Mono.just(mapper.map(albumId, AlbumIdDto.class))
                .flatMap(albumIdDto ->  firebase.getUsersByAlbum(albumIdDto.getAlbumId()))
                .map(sharedAlbumsDtos -> sharedAlbumsDtos);
    }

    public Mono<List<UsersDto>> getUsersById(Integer id) {
        return servicesRest.getUsersById(id)
                .map(users -> mapper.map(users, new TypeToken<List<UsersDto>>() {
                }.getType()));
    }

}
