package project.wolox.infrastructure.shared;

import io.micronaut.context.annotation.Factory;
import project.wolox.infrastructure.service.AlbumsService;
import project.wolox.infrastructure.service.CommentsService;
import project.wolox.infrastructure.service.PhotosService;
import project.wolox.infrastructure.service.UsersService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class AppContext {

    @Inject
    UsersService usersServiceRepository;

    @Inject
    PhotosService photosServiceRepository;

    @Inject
    AlbumsService albumsServiceRepository;

    @Inject
    CommentsService commentsServiceRepository;

    @Singleton
    project.wolox.domain.service.UsersService usersService(){
        return new project.wolox.domain.service.UsersService(this.usersServiceRepository);
    }

    @Singleton
    project.wolox.domain.service.PhotosService photosService(){
        return new project.wolox.domain.service.PhotosService(this.photosServiceRepository);
    }

    @Singleton
    project.wolox.domain.service.AlbumsService albumsService(){
        return new project.wolox.domain.service.AlbumsService(this.albumsServiceRepository);
    }

    @Singleton
    project.wolox.domain.service.CommentsService commentsService(){
        return new project.wolox.domain.service.CommentsService(this.commentsServiceRepository);
    }
}