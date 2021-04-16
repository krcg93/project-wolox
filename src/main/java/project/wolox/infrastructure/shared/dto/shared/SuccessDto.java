package project.wolox.infrastructure.shared.dto.shared;

public class SuccessDto {
    private int albumId;
    private int userId;

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
