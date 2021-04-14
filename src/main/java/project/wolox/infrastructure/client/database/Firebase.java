package project.wolox.infrastructure.client.database;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import project.wolox.infrastructure.shared.dto.albums_photos.SharedAlbumsDto;
import reactor.adapter.rxjava.RxJava2Adapter;
import reactor.core.publisher.Mono;

import javax.inject.Singleton;

@Singleton
public class Firebase {

    protected RxHttpClient client = null;

    public Firebase(@Client("${firebase.path}") RxHttpClient client) {
        this.client = client;
    }

    @Put
    public Mono<SharedAlbumsDto> putSharedAlbums(int albumId, SharedAlbumsDto sharedAlbumsDto) {
        HttpRequest httpRequest = HttpRequest.PUT("/sharedalbums/"+albumId+".json", sharedAlbumsDto);
        Flowable<SharedAlbumsDto> response = client.retrieve(httpRequest, SharedAlbumsDto.class);
        Maybe<SharedAlbumsDto> estadoAsistenciaDtoMaybe = response.firstElement();
        return RxJava2Adapter.maybeToMono(estadoAsistenciaDtoMaybe);
    }

    @Patch
    public Mono<SharedAlbumsDto> patchSharedAlbums(int albumId, SharedAlbumsDto sharedAlbumsDto) {
        HttpRequest httpRequest = HttpRequest.PATCH("/sharedalbums/"+albumId+".json", sharedAlbumsDto);
        Flowable<SharedAlbumsDto> response = client.retrieve(httpRequest, SharedAlbumsDto.class);
        Maybe<SharedAlbumsDto> albumsDtoMaybe = response.firstElement();
        return RxJava2Adapter.maybeToMono(albumsDtoMaybe);
    }

}
