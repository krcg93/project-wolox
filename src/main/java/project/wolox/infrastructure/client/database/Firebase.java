package project.wolox.infrastructure.client.database;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import project.wolox.infrastructure.shared.dto.albums_photos.AlbumsDto;
import project.wolox.infrastructure.shared.dto.albums_photos.SharedAlbumsDto;
import reactor.adapter.rxjava.RxJava2Adapter;
import reactor.core.publisher.Mono;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class Firebase {

    protected RxHttpClient client = null;

    public Firebase(@Client("${firebase.path}") RxHttpClient client) {
        this.client = client;
    }

    @Put
    public Mono<SharedAlbumsDto> putSharedAlbums(SharedAlbumsDto sharedAlbumsDto) {
        HttpRequest httpRequest = HttpRequest.PUT("/sharedalbums/"+sharedAlbumsDto.getAlbumId()+"/"+sharedAlbumsDto.getUserId()+".json", sharedAlbumsDto);
        Flowable<SharedAlbumsDto> response = client.retrieve(httpRequest, SharedAlbumsDto.class);
        Maybe<SharedAlbumsDto> estadoAsistenciaDtoMaybe = response.firstElement();
        return RxJava2Adapter.maybeToMono(estadoAsistenciaDtoMaybe);
    }

    @Patch
    public Mono<SharedAlbumsDto> patchSharedAlbums(SharedAlbumsDto sharedAlbumsDto) {
        HttpRequest httpRequest = HttpRequest.PATCH("/sharedalbums/"+sharedAlbumsDto.getAlbumId()+"/"+sharedAlbumsDto.getUserId()+".json", sharedAlbumsDto);
        Flowable<SharedAlbumsDto> response = client.retrieve(httpRequest, SharedAlbumsDto.class);
        Maybe<SharedAlbumsDto> albumsDtoMaybe = response.firstElement();
        return RxJava2Adapter.maybeToMono(albumsDtoMaybe);
    }

    @Get
    public Mono<Object> getUsersByAlbum(Integer idAlbum) {
        HttpRequest httpRequest = HttpRequest.GET("/sharedalbums/"+idAlbum+".json");
        Flowable<Object> response = client.retrieve(httpRequest, Object.class);
        Object dao = response.blockingFirst();
        Maybe<Object> albumsDtoMaybe = response.firstElement();
        return RxJava2Adapter.maybeToMono(albumsDtoMaybe);
    }

}
