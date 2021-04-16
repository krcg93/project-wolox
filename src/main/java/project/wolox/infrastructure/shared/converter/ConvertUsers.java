package project.wolox.infrastructure.shared.converter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import project.wolox.infrastructure.shared.dto.albums_photos.SharedAlbumsDto;
import project.wolox.infrastructure.shared.dto.users.UsersDto;
import project.wolox.infrastructure.shared.dto.users.UsersSharedDto;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class ConvertUsers {

    public static UsersSharedDto mapperUsers(SharedAlbumsDto sharedAlbumsDto, UsersDto usersDto)
    {
        UsersSharedDto usersSharedDto = new UsersSharedDto();
        usersSharedDto.setId(usersDto.getId());
        usersSharedDto.setName(usersDto.getName());
        usersSharedDto.setUsername(usersDto.getUsername());
        usersSharedDto.setEmail(usersDto.getEmail());
        usersSharedDto.setAddress(usersDto.getAddress());
        usersSharedDto.setPhone(usersDto.getPhone());
        usersSharedDto.setWebsite(usersDto.getWebsite());
        usersSharedDto.setCompany(usersDto.getCompany());
        usersSharedDto.setSeeAlbum(sharedAlbumsDto.getSeeAlbum());
        usersSharedDto.setWriteAlbum(sharedAlbumsDto.getWriteAlbum());
        return usersSharedDto;
    }

    public static List<SharedAlbumsDto> mapperAlbums(Object sharedAlbumsDto)
    {
        Gson gson = new Gson();
        List<SharedAlbumsDto> sharedAlbumsDtos = new ArrayList<>();
        LinkedHashMap linkedHashMap = (LinkedHashMap) sharedAlbumsDto;
        linkedHashMap.forEach((key, value) -> {
            String json = gson.toJson(value,LinkedHashMap.class);
            SharedAlbumsDto data = new Gson().fromJson(json, SharedAlbumsDto.class);
            sharedAlbumsDtos.add(data);
        });
        return sharedAlbumsDtos;
    }
}
