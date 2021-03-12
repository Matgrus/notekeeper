package app.notekeeper.client;

import app.notekeeper.model.Note;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteClient {

    public NoteClient(){
    }

    public List<Note> getAll(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/notes";
        ResponseEntity<Note[]> response = restTemplate.getForEntity(url, Note[].class);

        return Arrays.asList(response.getBody());
    }

    public Note addOrUpdateNote(Note note){
        String url = "http://localhost:8080/notes";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Note> entity = new HttpEntity<>(note, httpHeaders);
        return restTemplate.postForEntity(url, entity, Note.class).getBody();
    }

    public void deleteNote(Long id){
        String url = "http://localhost:8080/notes/" + id;
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, params);
    }

}
