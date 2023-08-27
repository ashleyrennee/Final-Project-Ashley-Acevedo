package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ConsoleRepository consoleRepository;

    @QueryMapping List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    @QueryMapping
    public Game findGameById(@Argument int id) {
        Optional<Game> returnVal = gameRepository.findById(id);
        return returnVal.isPresent() ? returnVal.get() : null;
    }

    @QueryMapping
    public List<Game> findGamesByTitle(@Argument String title) {
        return gameRepository.findByTitle(title);
    }

    @QueryMapping
    public List<Game> findGamesByEsrbRating(@Argument String esrbRating) {
        return gameRepository.findByEsrbRating(esrbRating);
    }

    @QueryMapping
    public List<Game> findGamesByStudio(@Argument String studio) {
        return gameRepository.findByStudio(studio);
    }

    @QueryMapping List<Console> findAllConsoles() {
        return consoleRepository.findAll();
    }

    @QueryMapping
    public Console findConsoleById(@Argument int id) {
        Optional<Console> returnVal = consoleRepository.findById(id);
        return returnVal.isPresent() ? returnVal.get() : null;
    }

    @QueryMapping
    public List<Console> findConsolesByManufacturer(@Argument String manufacturer) {
        return consoleRepository.findByManufacturer(manufacturer);
    }

}
