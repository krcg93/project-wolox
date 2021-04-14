package project.wolox.infrastructure.shared.converter;

import project.wolox.infrastructure.shared.dto.albums_photos.PhotosDto;

import java.net.URLEncoder;

public class ConvertUrl {

    public static String mapperUrl(String url)
    {
        String urlMapper = "";
        try {
            urlMapper = URLEncoder.encode(url, "UTF-8");
        }catch (Exception e){
            return e.toString();
        }
        return urlMapper;
    }
}
