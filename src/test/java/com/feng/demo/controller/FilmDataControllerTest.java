package com.feng.demo.controller;

import com.feng.demo.AbstractContextControllerTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
public class FilmDataControllerTest extends AbstractContextControllerTests {
    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
    }

    @Test
    public void locationByTitle() throws Exception {
        this.mockMvc.perform(get("/data/getLocationByTitle?title=180"))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$", hasSize(8)));
    }

    @Test
    public void title() throws Exception {
        this.mockMvc.perform(get("/data/title"))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(187)));
    }

    @Test
    public void locations() throws Exception {
        this.mockMvc.perform(get("/data/locations"))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(847)));
    }

    @Test
    public void director() throws Exception {
        this.mockMvc.perform(get("/data/director"))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(997)));
    }

    @Test
    public void writer() throws Exception {
        this.mockMvc.perform(get("/data/writer"))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(161)));
    }
}
