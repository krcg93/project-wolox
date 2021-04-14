package project.wolox.infrastructure.client.rest;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import project.wolox.domain.model.users.Users;
import project.wolox.infrastructure.shared.converter.ConvertUrl;
import project.wolox.infrastructure.shared.dto.albums_photos.AlbumsDto;
import project.wolox.infrastructure.shared.dto.albums_photos.PhotosDto;
import project.wolox.infrastructure.shared.dto.posts_comments.CommentsDto;
import project.wolox.infrastructure.shared.dto.posts_comments.PostsDto;
import project.wolox.infrastructure.shared.dto.users.UsersDto;
import reactor.adapter.rxjava.RxJava2Adapter;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URLEncoder;
import java.util.List;

@Singleton
public class ServicesRest {

    protected RxHttpClient client = null;

    public ServicesRest(@Client("${services.path}") RxHttpClient client) {
        this.client = client;
    }

    @Get(single = true)
    public Mono<List<UsersDto>> getUsers() {
        HttpRequest httpRequest = HttpRequest.GET("/users");
        Flowable<List<UsersDto>> response = client.retrieve(httpRequest, Object.class);
        List<UsersDto> dao = response.blockingFirst();
        Maybe<List<UsersDto>> usersDtoMaybe = response.firstElement();
        Mono<List<UsersDto>> data = RxJava2Adapter.maybeToMono(usersDtoMaybe);
        return data;
    }

    @Get(single = true)
    public Mono<List<PhotosDto>> getPhotos() {
        HttpRequest httpRequest = HttpRequest.GET("/photos");
        Flowable<List<PhotosDto>> response = client.retrieve(httpRequest, Object.class);
        List<PhotosDto> dao = response.blockingFirst();
        Maybe<List<PhotosDto>> listMaybe = response.firstElement();
        Mono<List<PhotosDto>> data = RxJava2Adapter.maybeToMono(listMaybe);
        return data;
    }

    @Get(single = true)
    public Mono<List<AlbumsDto>> getAlbums() {
        HttpRequest httpRequest = HttpRequest.GET("/albums");
        Flowable<List<AlbumsDto>> response = client.retrieve(httpRequest, Object.class);
        List<AlbumsDto> dao = response.blockingFirst();
        Maybe<List<AlbumsDto>> listMaybe = response.firstElement();
        Mono<List<AlbumsDto>> data = RxJava2Adapter.maybeToMono(listMaybe);
        return data;
    }

    @Get(single = true)
    public Mono<List<AlbumsDto>> getAlbumsByUser(Integer userId) {
        HttpRequest httpRequest = HttpRequest.GET("/albums?userId="+ userId);
        Flowable<List<AlbumsDto>> response = client.retrieve(httpRequest, Object.class);
        List<AlbumsDto> dao = response.blockingFirst();
        Maybe<List<AlbumsDto>> listMaybe = response.firstElement();
        Mono<List<AlbumsDto>> data = RxJava2Adapter.maybeToMono(listMaybe);
        return data;
    }

    @Get(single = true)
    public Mono<List<PhotosDto>> getPhotosByalbum(Integer albumId) {
        HttpRequest httpRequest = HttpRequest.GET("/photos?albumId="+albumId);
        Flowable<List<PhotosDto>> response = client.retrieve(httpRequest, Object.class);
        List<PhotosDto> dao = response.blockingFirst();
        Maybe<List<PhotosDto>> listMaybe = response.firstElement();
        Mono<List<PhotosDto>> data = RxJava2Adapter.maybeToMono(listMaybe);
        return data;
    }

    @Get(single = true)
    public Mono<List<CommentsDto>> getCommentsByName(String name) {
        HttpRequest httpRequest = HttpRequest.GET("/comments?name="+ConvertUrl.mapperUrl(name));
        Flowable<List<CommentsDto>> response = client.retrieve(httpRequest, Object.class);
        List<CommentsDto> dao = response.blockingFirst();
        Maybe<List<CommentsDto>> listMaybe = response.firstElement();
        Mono<List<CommentsDto>> data = RxJava2Adapter.maybeToMono(listMaybe);
        return data;
    }

    @Get(single = true)
    public Mono<List<PostsDto>> getPostsByUser(Integer userId) {
        HttpRequest httpRequest = HttpRequest.GET("/posts?userId="+userId);
        Flowable<List<PostsDto>> response = client.retrieve(httpRequest, Object.class);
        List<PostsDto> dao = response.blockingFirst();
        Maybe<List<PostsDto>> listMaybe = response.firstElement();
        Mono<List<PostsDto>> data = RxJava2Adapter.maybeToMono(listMaybe);
        return data;
    }

    @Get(single = true)
    public Mono<List<CommentsDto>> getCommentsByPost(Integer postId) {
        HttpRequest httpRequest = HttpRequest.GET("/comments?postId="+postId);
        Flowable<List<CommentsDto>> response = client.retrieve(httpRequest, Object.class);
        List<CommentsDto> dao = response.blockingFirst();
        Maybe<List<CommentsDto>> listMaybe = response.firstElement();
        Mono<List<CommentsDto>> data = RxJava2Adapter.maybeToMono(listMaybe);
        return data;
    }
}
