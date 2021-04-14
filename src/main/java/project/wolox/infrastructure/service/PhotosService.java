package project.wolox.infrastructure.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import project.wolox.domain.model.albums_photos.Photos;
import project.wolox.domain.model.shared.UserId;
import project.wolox.domain.service.dependency.PhotosI;
import project.wolox.infrastructure.client.database.Firebase;
import project.wolox.infrastructure.client.rest.ServicesRest;
import project.wolox.infrastructure.shared.converter.ConvertPhotos;
import project.wolox.infrastructure.shared.dto.albums_photos.AlbumsDto;
import project.wolox.infrastructure.shared.dto.albums_photos.PhotosDto;
import project.wolox.infrastructure.shared.dto.shared.UserIdDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class PhotosService implements PhotosI {

    @Inject
    ServicesRest servicesRest;

    @Inject
    Firebase firebase;

    @Inject
    private ModelMapper mapper;

    @Override
    public Mono<List<Photos>> getPhotos() {
        return servicesRest.getPhotos()
                .map(photos -> mapper.map(photos, new TypeToken<List<Photos>>() {
                }.getType()));

    }

    @Override
    public Mono<List<List<Photos>>> getPhotosByUser(UserId userId) {
        return getAlbumsByUser(userId)
                .zipWhen(albumsList ->
                        Flux.fromIterable(albumsList)
                                .flatMap(albums ->
                                        getPhotosByAlbum(albums.getId())
                                                .map(photosDtos -> ConvertPhotos.mapperPhotos(photosDtos)))
                                .collectList()
                )
                .map(tuple -> {
                    List<List<PhotosDto>> photosDtos = tuple.getT2();
                    return mapper.map(photosDtos, new TypeToken<List<List<Photos>>>() {
                    }.getType());
                });

    }

    private Mono<List<AlbumsDto>> getAlbumsByUser(UserId userId) {
        return Mono.just(mapper.map(userId, UserIdDto.class))
                .flatMap(userIdDto ->  servicesRest.getAlbumsByUser(userIdDto.getUserId()))
                .map(albums -> mapper.map(albums, new TypeToken<List<AlbumsDto>>() {
                }.getType()));
    }

    private Mono<List<PhotosDto>> getPhotosByAlbum(Integer albumId) {
        return servicesRest.getPhotosByalbum(albumId)
                .onErrorResume(Mono::error);
    }

}
