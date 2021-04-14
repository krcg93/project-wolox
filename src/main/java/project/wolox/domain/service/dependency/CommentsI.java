package project.wolox.domain.service.dependency;

import project.wolox.domain.model.posts_comments.Comments;
import project.wolox.domain.model.shared.Name;
import project.wolox.domain.model.shared.UserId;
import reactor.core.publisher.Mono;

import java.util.List;


public interface CommentsI {

    Mono<List<List<Comments>>> getCommentsByUser(UserId userId);

    Mono<List<Comments>> getCommentsByName(Name name);

}
