package project.wolox.infrastructure.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import project.wolox.domain.model.posts_comments.Comments;
import project.wolox.domain.model.shared.Name;
import project.wolox.domain.model.shared.UserId;
import project.wolox.domain.service.dependency.CommentsI;
import project.wolox.infrastructure.client.database.Firebase;
import project.wolox.infrastructure.client.rest.ServicesRest;
import project.wolox.infrastructure.shared.converter.ConvertComments;
import project.wolox.infrastructure.shared.dto.posts_comments.CommentsDto;
import project.wolox.infrastructure.shared.dto.posts_comments.PostsDto;
import project.wolox.infrastructure.shared.dto.shared.NameDto;
import project.wolox.infrastructure.shared.dto.shared.UserIdDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CommentsService implements CommentsI {

    @Inject
    ServicesRest servicesRest;

    @Inject
    Firebase firebase;

    @Inject
    private ModelMapper mapper;

    @Override
    public Mono<List<List<Comments>>> getCommentsByUser(UserId userId) {
        return getPostByUser(userId)
                .zipWhen(postsDtos ->
                        Flux.fromIterable(postsDtos)
                                .flatMap(postsDto ->
                                        getCommentsByPost(postsDto.getId())
                                                .map(commentsDtos -> ConvertComments.mapperComments(commentsDtos)))
                                .collectList()
                )
                .map(tuple -> {
                    List<List<CommentsDto>> lists = tuple.getT2();
                    return mapper.map(lists, new TypeToken<List<List<Comments>>>() {
                    }.getType());
                });

    }

    @Override
    public Mono<List<Comments>> getCommentsByName(Name name) {
        return Mono.just(mapper.map(name, NameDto.class))
                .flatMap(nameDto ->  servicesRest.getCommentsByName(nameDto.getName()))
                .map(commentsDtos -> mapper.map(commentsDtos, new TypeToken<List<Comments>>() {
                }.getType()));
    }

    private Mono<List<PostsDto>> getPostByUser(UserId userId) {
        return Mono.just(mapper.map(userId, UserIdDto.class))
                .flatMap(userIdDto ->  servicesRest.getPostsByUser(userIdDto.getUserId()))
                .map(albums -> mapper.map(albums, new TypeToken<List<PostsDto>>() {
                }.getType()));
    }

    private Mono<List<CommentsDto>> getCommentsByPost(Integer postId) {
        return servicesRest.getCommentsByPost(postId)
                .onErrorResume(Mono::error);
    }

}
