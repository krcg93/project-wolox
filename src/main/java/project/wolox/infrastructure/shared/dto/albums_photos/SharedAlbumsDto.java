package project.wolox.infrastructure.shared.dto.albums_photos;

public class SharedAlbumsDto {
    private Integer userId;
    private Integer albumId;
    private Boolean seeAlbum;
    private Boolean writeAlbum;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Boolean getSeeAlbum() {
        return seeAlbum;
    }

    public void setSeeAlbum(Boolean seeAlbum) {
        this.seeAlbum = seeAlbum;
    }

    public Boolean getWriteAlbum() {
        return writeAlbum;
    }

    public void setWriteAlbum(Boolean writeAlbum) {
        this.writeAlbum = writeAlbum;
    }
}
