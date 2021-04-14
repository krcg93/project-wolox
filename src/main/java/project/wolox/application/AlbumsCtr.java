package project.wolox.application;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import project.wolox.domain.model.shared.UserId;
import project.wolox.domain.service.AlbumsService;
import project.wolox.infrastructure.shared.dto.albums_photos.AlbumsDto;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.util.List;

@Controller("/albums-services")
public class AlbumsCtr {

    @Inject
    private ModelMapper mapper;

    @Inject
    private AlbumsService albumsService;

    @Get(value = "albums", produces = MediaType.APPLICATION_JSON)
    public Mono<List<AlbumsDto>> getAlbums() {
        return albumsService.getAlbums()
                .map(albums -> mapper.map(albums, new TypeToken<List<AlbumsDto>>() {
                }.getType()));
    }

    @Get(value = "albums-by-user/{userId}", produces = MediaType.APPLICATION_JSON)
    public Mono<List<AlbumsDto>> getAlbumsByUser(Integer userId) {
        return UserId.create(userId)
                .flatMap(albumsService::getAlbumsByUser)
                .map(albums -> mapper.map(albums, new TypeToken<List<AlbumsDto>>() {
                }.getType()));
    }
}
