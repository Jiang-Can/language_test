package com.langtest.langtest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.langtest.langtest.dto.TextPostDto;
import com.langtest.langtest.service.CheckTextService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.w3c.dom.Text;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CheckTextControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CheckTextService checkTextService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void init(){
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(
                new CheckTextController(checkTextService),
                new ControllerExceptionHandler()
        ).build();
    }

    @Test
    void returnResultWhenInputIsValid() throws Exception {
        TextPostDto textPostDto = new TextPostDto();
        textPostDto.setText("testdx sd t  dsd  a a s dff");
        mockMvc.perform(post("/text")
                .content(objectMapper.writeValueAsBytes(textPostDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void throwExceptionWhenInputIsInvalid() throws Exception {
        TextPostDto textPostDto = new TextPostDto();
        textPostDto.setText("   ");
        mockMvc.perform(post("/text")
                        .content(objectMapper.writeValueAsBytes(textPostDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
