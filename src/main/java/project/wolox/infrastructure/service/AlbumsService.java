package project.wolox.infrastructure.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import project.wolox.domain.model.albums_photos.Albums;
import project.wolox.domain.model.albums_photos.SharedAlbums;
import project.wolox.domain.model.shared.Success;
import project.wolox.domain.model.shared.UserId;
import project.wolox.domain.service.dependency.AlbumsI;
import project.wolox.infrastructure.client.database.Firebase;
import project.wolox.infrastructure.client.rest.ServicesRest;
import project.wolox.infrastructure.shared.converter.ConvertSuccess;
import project.wolox.infrastructure.shared.dto.albums_photos.SharedAlbumsDto;
import project.wolox.infrastructure.shared.dto.shared.UserIdDto;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AlbumsService implements AlbumsI {

    @Inject
    ServicesRest servicesRest;

    @Inject
    Firebase firebase;

    @Inject
    private ModelMapper mapper;

    @Override
    public Mono<List<Albums>> getAlbums() {
        return servicesRest.getAlbums()
                .map(albums -> mapper.map(albums, new TypeToken<List<Albums>>() {
                }.getType()));

    }

    @Override
    public Mono<List<Albums>> getAlbumsByUser(UserId userId) {
        return Mono.just(mapper.map(userId, UserIdDto.class))
                .flatMap(userIdDto ->  servicesRest.getAlbumsByUser(userIdDto.getUserId()))
                .map(albums -> mapper.map(albums, new TypeToken<List<Albums>>() {
                }.getType()));
    }

    @Override
    public Mono<Success> sharedAlbumUser(SharedAlbums sharedAlbums) {
        return Mono.just(mapper.map(sharedAlbums, SharedAlbumsDto.class))
                .flatMap(sharedAlbumsDto ->  firebase.putSharedAlbums(sharedAlbumsDto))
                .map(sharedAlbumsDto -> {
                    return mapper.map(ConvertSuccess.mapperSuccess(sharedAlbumsDto), Success.class);
                });
    }

    @Override
    public Mono<Success> updateSharedAlbumUser(SharedAlbums sharedAlbums) {
        return Mono.just(mapper.map(sharedAlbums, SharedAlbumsDto.class))
                .flatMap(sharedAlbumsDto ->  firebase.patchSharedAlbums(sharedAlbumsDto))
                .map(sharedAlbumsDto -> mapper.map(ConvertSuccess.mapperSuccess(sharedAlbumsDto), Success.class));
    }
}
