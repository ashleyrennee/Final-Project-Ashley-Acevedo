package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
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

    @QueryMapping
    public Game findGameById(@Argument int id) {
        Optional<Game> returnVal = gameRepository.findById(id);
        return returnVal.isPresent() ? returnVal.get() : null;
    }

    @QueryMapping
    public List<Game> findGameByTitle(@Argument String title) {
        List<Game> returnVal = gameRepository.findByTitle(title);
        return returnVal.size() > 0 ? returnVal : null;
    }

    @QueryMapping
    public List<Game> findGameByEsrbRating(@Argument String esrbRating) {
        List<Game> returnVal = gameRepository.findByEsrbRating(esrbRating);
        return returnVal.size() > 0 ? returnVal : null;
    }

    @QueryMapping
    public List<Game> findGameByStudio(@Argument String studio) {
        List<Game> returnVal = gameRepository.findByStudio(studio);
        return returnVal.size() > 0 ? returnVal : null;
    }

    @QueryMapping
    public Console findConsoleById(@Argument int id) {
        Optional<Console> returnVal = consoleRepository.findById(id);
        return returnVal.isPresent() ? returnVal.get() : null;
    }

    @QueryMapping
    public List<Console> findConsoleByManufacturer(@Argument int manufacturer) {
        List<Console> returnVal = consoleRepository.findByManufacturer(manufacturer);
        return returnVal.size() > 0 ? returnVal : null;
    }

}
