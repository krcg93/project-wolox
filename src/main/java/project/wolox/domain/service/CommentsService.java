package project.wolox.domain.service;

import project.wolox.domain.model.posts_comments.Comments;
import project.wolox.domain.model.shared.Name;
import project.wolox.domain.model.shared.UserId;
import project.wolox.domain.service.dependency.CommentsI;
import reactor.core.publisher.Mono;

import java.util.List;


public class CommentsService {

    private CommentsI commentsI;

    public CommentsService(CommentsI commentsI) {
        this.commentsI = commentsI;
    }

    public Mono<List<List<Comments>>> getCommentsByUser(UserId userId){
        return commentsI.getCommentsByUser(userId)
                .onErrorResume(Mono::error);
    }

    public Mono<List<Comments>> getCommentsByName(Name name){
        return commentsI.getCommentsByName(name)
                .onErrorResume(Mono::error);
    }
}
