package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private ConsoleRepository consoleRepository;

    private Console console;

    @BeforeEach
    public void setUp() {
        console = new Console("ps5", "Sony", "50", "intel", new BigDecimal("330.00"),
                5);
    }

    @Test
    public void shouldReturnCreatedStatus() throws Exception{
        String inputJson = mapper.writeValueAsString(console);
        mockMvc.perform(
                post("/console").content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)

        ).andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetConsoleById() throws Exception{
        mockMvc.perform(
                get("/console/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateConsoleById() throws Exception{
        String inputJson = mapper.writeValueAsString(console);
        mockMvc.perform(
                put("/console/1").contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)
        ).andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteConsoleById() throws Exception{
        mockMvc.perform(
                delete("/console/1")
        ).andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFindConsoleByManufacturer() throws Exception{
        mockMvc.perform(
                get("/console/manufacturer/Sony")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn422Error() throws Exception{
        Console console = new Console();
        String inputJson = mapper.writeValueAsString(console);
        mockMvc.perform(
                post("/console").contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)
        ).andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}
