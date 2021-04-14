package project.wolox.application;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import project.wolox.domain.model.shared.Name;
import project.wolox.domain.model.shared.UserId;
import project.wolox.domain.service.CommentsService;
import project.wolox.infrastructure.shared.dto.posts_comments.CommentsDto;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.util.List;

@Controller("/comments-services")
public class CommentsCtr {

    @Inject
    private ModelMapper mapper;

    @Inject
    private CommentsService commentsService;

    @Get(value = "comments-by-user/{userId}", produces = MediaType.APPLICATION_JSON)
    public Mono<List<List<CommentsDto>>> getPhotosByUser(Integer userId) {
        return UserId.create(userId)
                .flatMap(commentsService::getCommentsByUser)
                .map(comments -> mapper.map(comments, new TypeToken<List<List<CommentsDto>>>() {
                }.getType()));
    }

    @Get(value = "comments-by-name/{name}", produces = MediaType.APPLICATION_JSON)
    public Mono<List<CommentsDto>> getPhotosByName(String name) {
        return Name.create(name)
                .flatMap(commentsService::getCommentsByName)
                .map(comments -> mapper.map(comments, new TypeToken<List<CommentsDto>>() {
                }.getType()));
    }
}
