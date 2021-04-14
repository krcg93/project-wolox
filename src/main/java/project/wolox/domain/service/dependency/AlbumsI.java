package project.wolox.domain.service.dependency;

import project.wolox.domain.model.albums_photos.Albums;
import project.wolox.domain.model.shared.UserId;
import reactor.core.publisher.Mono;

import java.util.List;


public interface AlbumsI {

    Mono<List<Albums>> getAlbums();

    Mono<List<Albums>> getAlbumsByUser(UserId userId);

}
