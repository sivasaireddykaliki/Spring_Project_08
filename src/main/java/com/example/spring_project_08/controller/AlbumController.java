package com.example.spring_project_08.controller;

import com.example.spring_project_08.entity.Album;
import com.example.spring_project_08.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/open-api")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    // get albums
    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getAlbums()
    {
        return albumService.getAlbums();
    }

    // get album by id
    @GetMapping("/album/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable int id)
    {
        return albumService.getAlbumById(id);
    }
}
