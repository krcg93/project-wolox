package project.wolox.domain.service.dependency;

import project.wolox.domain.model.albums_photos.Photos;
import project.wolox.domain.model.shared.UserId;
import reactor.core.publisher.Mono;

import java.util.List;


public interface PhotosI {

    Mono<List<Photos>> getPhotos();

    Mono<List<List<Photos>>> getPhotosByUser(UserId userId);


}
