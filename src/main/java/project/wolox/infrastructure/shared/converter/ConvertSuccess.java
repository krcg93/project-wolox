package project.wolox.infrastructure.shared.converter;

import project.wolox.infrastructure.shared.dto.albums_photos.SharedAlbumsDto;
import project.wolox.infrastructure.shared.dto.shared.SuccessDto;


public class ConvertSuccess {

    public static SuccessDto mapperSuccess(SharedAlbumsDto sharedAlbumsDto)
    {
        SuccessDto successDto = new SuccessDto();
        successDto.setAlbumId(sharedAlbumsDto.getAlbumId());
        successDto.setUserId(sharedAlbumsDto.getUserId());
        return successDto;
    }
}
