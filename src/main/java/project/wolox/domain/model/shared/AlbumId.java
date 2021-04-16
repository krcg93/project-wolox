package project.wolox.domain.model.shared;

import project.wolox.domain.exception.Validate;
import reactor.core.publisher.Mono;

public class AlbumId {
    private Integer albumId;

    public AlbumId(){}

    private AlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public static Mono<AlbumId> create(Integer albumId){
        AlbumId albumsByUser = new AlbumId(albumId);
        return albumsByUser.validate().then(Mono.just(albumsByUser));
    }

    public Mono<Void> validate(){
        return Validate
                .nullEntityValidate(albumId, "albumId");
    }
    
}