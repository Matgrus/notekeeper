package app.notekeeper.client;

import app.notekeeper.model.ArchiveNote;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ArchiveNoteClient {

    public ArchiveNoteClient() {
    }

    public List<ArchiveNote> getAllById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/archivelist/" + id;
        ResponseEntity<ArchiveNote[]> response = restTemplate.getForEntity(url, ArchiveNote[].class);

        return Arrays.asList(response.getBody());
    }

    public void addArchiveNote(ArchiveNote note) {
        String url = "http://localhost:8080/archivelist";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<ArchiveNote> entity = new HttpEntity<>(note, httpHeaders);
        restTemplate.postForEntity(url, entity, ArchiveNote.class);
    }

}
