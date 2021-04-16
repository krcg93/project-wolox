package project.wolox.application;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import project.wolox.domain.model.albums_photos.SharedAlbums;
import project.wolox.domain.model.shared.UserId;
import project.wolox.domain.service.AlbumsService;
import project.wolox.infrastructure.shared.dto.albums_photos.AlbumsDto;
import project.wolox.infrastructure.shared.dto.albums_photos.SharedAlbumsDto;
import project.wolox.infrastructure.shared.dto.shared.SuccessDto;
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

    @Post(value = "shared-album-user", produces = MediaType.APPLICATION_JSON)
    public Mono<SuccessDto> sharedAlbumUser(@Body SharedAlbumsDto sharedAlbumsDto) {
        return Mono.just(mapper.map(sharedAlbumsDto, SharedAlbums.class))
                .flatMap(albumsService::sharedAlbumUser)
                .map(success -> mapper.map(success, SuccessDto.class));
    }

    @Put(value = "update-shared-album-user", produces = MediaType.APPLICATION_JSON)
    public Mono<SuccessDto> updateSharedAlbumUser(@Body SharedAlbumsDto sharedAlbumsDto) {
        return Mono.just(mapper.map(sharedAlbumsDto, SharedAlbums.class))
                .flatMap(albumsService::updateSharedAlbumUser)
                .map(success -> mapper.map(success, SuccessDto.class));
    }
}
