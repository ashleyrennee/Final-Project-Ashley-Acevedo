package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GameRepositoryTest {
    @Autowired
    GameRepository gameRepository;

    Game game;

    @BeforeEach
    public void setUp() throws Exception {
        gameRepository.deleteAll();

        game = new Game();
        game.setTitle("Guilty Gear Xrd REV2");
        game.setEsrbRating("T");
        game.setDescription("Astonishing 3D cell animations and overwhelming content volume, the next-generation fighting game GUILTY GEAR Xrd REV 2 is now out on Steam!");
        game.setPrice(BigDecimal.valueOf(29.99));
        game.setStudio("Arc System Works");
        game.setQuantity(500);

        game = gameRepository.save(game);
    }

    @Test
    public void addGame() {
        Optional<Game> newGame = gameRepository.findById(game.getId());
        assertEquals(newGame.get(), game);
    }

    @Test
    public void getGameById() {
        Optional<Game> newGame = gameRepository.findById(game.getId());
        assertEquals(newGame.get(), game);
    }

    @Test
    public void getAllGames() {
        assertEquals(gameRepository.findAll().size(), 1);
    }

    @Test
    public void updateGame() {
        game.setQuantity(469);
        gameRepository.save(game);

        Optional<Game> newGame = gameRepository.findById(game.getId());
        assertEquals(newGame.get(), game);
    }

    @Test
    public void deleteGameById() {
        gameRepository.deleteById(game.getId());
        Optional<Game> newGame = gameRepository.findById(game.getId());
        assertFalse(newGame.isPresent());
    }

    @Test
    public void findGamesByStudio() {
        List<Game> games = gameRepository.findByStudio(game.getStudio());
        assertEquals(1,games.size());
    }

    @Test
    public void findGamesByEsrbRating() {
        List<Game> games = gameRepository.findByEsrbRating(game.getEsrbRating());
        assertEquals(1,games.size());
    }

    @Test
    public void findGamesByTitle() {
        List<Game> games = gameRepository.findByTitle(game.getTitle());
        assertEquals(1,games.size());
    }

}
