package com.example.spring_project_08;

import com.example.spring_project_08.entity.Album;
import com.example.spring_project_08.entity.Book;
import com.example.spring_project_08.helper.AlbumHelper;
import com.example.spring_project_08.service.AlbumService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class AlbumServiceTest {


    @InjectMocks
    private AlbumService albumService;

    @Mock
    private RestTemplate restTemplate;

    private AlbumHelper albumHelper;

    private Album[] albums;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        albumHelper=new AlbumHelper();


        Album album1 = albumHelper.getDummyAlbum(1,"ABC",1);
        Album album2 = albumHelper.getDummyAlbum(2,"DEF",4);
        Album album3 = albumHelper.getDummyAlbum(3,"XYZ",5);

        albums = new Album[3];
        albums[0]=album1;
        albums[1]=album2;
        albums[2]=album3;

    }



    @Test
    public void testGetAlbumById(){

        int id=1;

        String uri="https://jsonplaceholder.typicode.com/albums/"+id;


       when(restTemplate.getForEntity(uri, Album.class)).thenReturn(new ResponseEntity<>(Arrays.stream(albums).filter(album -> album.getId() == id).findFirst().get(),HttpStatus.OK));


        Album album = albumService.getAlbumById(id).getBody();

        Assertions.assertNotNull(album);

    }

    @Test
    public void testGetAlbums()
    {
        String uri="https://jsonplaceholder.typicode.com/albums/";
        when(restTemplate.getForEntity(uri, Album[].class)).thenReturn(new ResponseEntity<>(albums,HttpStatus.OK));

        List<Album> albumList = albumService.getAlbums().getBody();

        Assertions.assertNotNull(albumList);

    }
}
