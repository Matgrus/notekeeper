package app.notekeeper.controller;

import app.notekeeper.model.Note;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NoteRepository noteRepository;

    Note note1 = new Note("test1", "content1");
    Note note2 = new Note("test2", "content2");
    Note note3 = new Note("test3", "content3");

    @BeforeAll
    private void init(){
        noteRepository.save(note1);
        noteRepository.save(note2);
        noteRepository.save(note3);
    }

    @Test
    void findAllTest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/notes"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

        Note[] notes = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Note[].class);

        assertThat(notes).isNotEmpty();
        assertThat(notes.length).isEqualTo(3);
        assertThat(notes[0].getContent()).isEqualTo("content1");
        assertThat(notes[1].getCreated()).isEqualTo(notes[1].getModified());

    }

}