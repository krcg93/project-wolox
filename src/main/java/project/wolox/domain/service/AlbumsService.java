package project.wolox.domain.service;

import project.wolox.domain.exception.Validate;
import project.wolox.domain.model.albums_photos.Albums;
import project.wolox.domain.model.albums_photos.SharedAlbums;
import project.wolox.domain.model.shared.Success;
import project.wolox.domain.model.shared.UserId;
import project.wolox.domain.service.dependency.AlbumsI;
import reactor.core.publisher.Mono;

import java.util.List;


public class AlbumsService {

    private AlbumsI albumsI;

    public AlbumsService(AlbumsI albumsI) {
        this.albumsI = albumsI;
    }

    public Mono<List<Albums>> getAlbums(){
        return  albumsI.getAlbums()
                .onErrorResume(Mono::error);
    }

    public Mono<List<Albums>> getAlbumsByUser(UserId userId){
        return  Validate.nullEntityValidate(userId,"Album por usuario")
                .then(Mono.defer(() ->{
                    return albumsI.getAlbumsByUser(userId);
                }))
                .onErrorResume(Mono::error);
    }

    public Mono<Success> sharedAlbumUser(SharedAlbums sharedAlbums){
        return  Validate.nullEntityValidate(sharedAlbums,"Album compartido por usuario")
                .then(Mono.defer(() ->{
                    return albumsI.sharedAlbumUser(sharedAlbums);
                }))
                .onErrorResume(Mono::error);
    }

    public Mono<Success> updateSharedAlbumUser(SharedAlbums sharedAlbums){
        return  Validate.nullEntityValidate(sharedAlbums,"Album compartido actualizado por usuario")
                .then(Mono.defer(() ->{
                    return albumsI.updateSharedAlbumUser(sharedAlbums);
                }))
                .onErrorResume(Mono::error);
    }
}
