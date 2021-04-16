package project.wolox.domain.model.albums_photos;

import project.wolox.domain.exception.Validate;
import reactor.core.publisher.Mono;

public class SharedAlbums {
    private Integer userId;
    private Integer albumId;
    private Boolean seeAlbum;
    private Boolean writeAlbum;

   public SharedAlbums(){}

   private SharedAlbums(Integer userId,
                        Integer albumId,
                        Boolean seeAlbum,
                        Boolean writeAlbum) {
       this.userId = userId;
       this.albumId = albumId;
       this.seeAlbum = seeAlbum;
       this.writeAlbum = writeAlbum;
   }

   public static Mono<SharedAlbums> create(Integer userId,
                                           Integer albumId,
                                           Boolean seeAlbum,
                                           Boolean writeAlbum){
       SharedAlbums sharedAlbums = new SharedAlbums(userId, albumId, seeAlbum, writeAlbum);
        return sharedAlbums.validate().then(Mono.just(sharedAlbums));
    }

    public Mono<Void> validate(){
        return Validate
                .nullEntityValidate(userId, "userId")
                .switchIfEmpty(Validate.nullEntityValidate(albumId,"albumId"))
                .switchIfEmpty(Validate.nullEntityValidate(seeAlbum,"seeAlbum"))
                .switchIfEmpty(Validate.nullEntityValidate(writeAlbum,"writeAlbum"));
    }
}
