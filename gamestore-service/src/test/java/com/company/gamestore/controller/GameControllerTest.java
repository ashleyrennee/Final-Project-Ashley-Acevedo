package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
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

@WebMvcTest(GameController.class)
public class GameControllerTest {
    @MockBean
    private GameRepository gameRepository;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    private Game game;

    @BeforeEach
    public void setUp() throws Exception {
        game = new Game();
        game.setTitle("Street Fighter 6");
        game.setEsrbRating("T");
        game.setDescription("Here comes Capcom’s newest challenger! Street Fighter 6 spans three distinct game modes, including World Tour, Fighting Ground and Battle Hub.");
        game.setPrice(BigDecimal.valueOf(59.99));
        game.setStudio("Capcom");
        game.setQuantity(500);
    }

    @Test
    void addGame() throws Exception {
        String inputJson = mapper.writeValueAsString(game);

        mockMvc.perform(post("/game")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getGameById() throws Exception {
        mockMvc.perform(get("/game/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllGames() throws Exception {
        mockMvc.perform(get("/game"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateGame() throws Exception {
        game.setEsrbRating("M");
        game.setQuantity(459);

        String inputJson = mapper.writeValueAsString(game);

        mockMvc.perform(put("/game")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteGameById() throws Exception {
        mockMvc.perform(delete("/game/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void findGamesByStudio() throws Exception {
        mockMvc.perform(get("/game/studio/capcom"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findGamesByEsrbRating() throws Exception {
        mockMvc.perform(get("/game/esrbrating/t"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findGamesByTitle() throws Exception {
        mockMvc.perform(get("/game/title/Street"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn422Error() throws Exception{
        Game game1 = new Game();
        game1.setTitle("Fortnite");
        String inputJson = mapper.writeValueAsString(game1);
        mockMvc.perform(
                        post("/game").content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
