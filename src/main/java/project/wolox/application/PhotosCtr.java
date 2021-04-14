package project.wolox.application;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import project.wolox.domain.model.shared.UserId;
import project.wolox.domain.service.PhotosService;
import project.wolox.infrastructure.shared.dto.albums_photos.PhotosDto;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.util.List;

@Controller("/photos-services")
public class PhotosCtr {

    @Inject
    private ModelMapper mapper;

    @Inject
    private PhotosService photosService;

    @Get(value = "photos", produces = MediaType.APPLICATION_JSON)
    public Mono<List<PhotosDto>> getUsers() {
        return photosService.getPhotos()
                .map(photos -> mapper.map(photos, new TypeToken<List<PhotosDto>>() {
                }.getType()));
    }

    @Get(value = "photos-by-user/{userId}", produces = MediaType.APPLICATION_JSON)
    public Mono<List<List<PhotosDto>>> getPhotosByUser(Integer userId) {
        return UserId.create(userId)
                .flatMap(photosService::getPhotosByUser)
                .map(photos -> mapper.map(photos, new TypeToken<List<List<PhotosDto>>>() {
                }.getType()));
    }
}
