package com.example.spring_project_08.helper;

import com.example.spring_project_08.entity.Album;
import com.example.spring_project_08.entity.Book;

public class AlbumHelper {


    public Album getDummyAlbum(int id, String title, int userId)
    {
        Album album= Album.builder()
                .id(id)
                .title(title)
                .userId(userId)
                .build();

        return album;

    }

}
