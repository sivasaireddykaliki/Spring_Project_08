
package com.example.spring_project_08.service;

import com.example.spring_project_08.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AlbumService {

    private RestTemplate restTemplate = new RestTemplate();


    public ResponseEntity<Album> getAlbumById(int id)
    {
        try {
            String uri="https://jsonplaceholder.typicode.com/albums/";
            uri = uri + id;
            ResponseEntity<Album> albumValue = restTemplate.getForEntity(uri, Album.class);
            Album album = albumValue.getBody();
            return new ResponseEntity<>(album, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Album>> getAlbums(){

        try {
            String uri="https://jsonplaceholder.typicode.com/albums/";
            ResponseEntity<Album[]> albumValues = restTemplate.getForEntity(uri, Album[].class);
            List<Album> albums = Arrays.asList(albumValues.getBody());
            return new ResponseEntity<>(albums, HttpStatus.OK);

        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
