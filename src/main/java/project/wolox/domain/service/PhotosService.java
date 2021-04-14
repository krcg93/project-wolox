package project.wolox.domain.service;

import project.wolox.domain.exception.Validate;
import project.wolox.domain.model.albums_photos.Photos;
import project.wolox.domain.model.shared.UserId;
import project.wolox.domain.service.dependency.PhotosI;
import reactor.core.publisher.Mono;

import java.util.List;


public class PhotosService {

    private PhotosI photosI;

    public PhotosService(PhotosI photosI) {
        this.photosI = photosI;
    }

    public Mono<List<Photos>> getPhotos(){
        return  photosI.getPhotos()
                .onErrorResume(Mono::error);
    }

    public Mono<List<List<Photos>>> getPhotosByUser(UserId userId){
        return  Validate.nullEntityValidate(userId,"fotos por usuario")
                .then(Mono.defer(() ->{
                    return photosI.getPhotosByUser(userId);
                }))
                .onErrorResume(Mono::error);
    }
}
