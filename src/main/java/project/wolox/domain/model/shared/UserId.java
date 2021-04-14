package project.wolox.domain.model.shared;

import project.wolox.domain.exception.Validate;
import reactor.core.publisher.Mono;

public class UserId {
    private Integer userId;

    public UserId(){}

    private UserId(Integer userId) {
        this.userId = userId;
    }

    public static Mono<UserId> create(Integer userId){
        UserId albumsByUser = new UserId(userId);
        return albumsByUser.validate().then(Mono.just(albumsByUser));
    }

    public Mono<Void> validate(){
        return Validate
                .nullEntityValidate(userId, "userId");
    }
    
}