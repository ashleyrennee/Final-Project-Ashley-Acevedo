package com.company.gamestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ConsoleRepository consoleRepository;

    @QueryMapping
    public Game findGameById(@Argument int id) {
        Optional<Game> returnVal = gameRepository.findById(id);
        return returnVal.isPresent() ? returnVal.get(): null;
    }

    @QueryMapping
    public Game findGameByTitle(@Argument String title) {
        Optional<Game> returnVal = gameRepository.findByTitle(title);
        return returnVal.isPresent() ? returnVal.get(): null;
    }

    @QueryMapping
    public Game findGameByEsrbRating(@Argument String esrbRating) {
        Optional<Game> returnVal = gameRepository.findByEsrbRating(esrbRating);
        return returnVal.isPresent() ? returnVal.get(): null;
    }

    @QueryMapping
    public Game findGameByStudio(@Argument String studio) {
        Optional<Game> returnVal = gameRepository.findByStudio(studio);
        return returnVal.isPresent() ? returnVal.get(): null;
    }

    @QueryMapping
    public Console findConsoleById(@Argument int id) {
        Optional<Console> returnVal = consoleRepository.findById(id);
        return returnVal.isPresent() ? returnVal.get(): null;
    }

    @QueryMapping
    public Console findConsoleByManufacturer(@Argument int manufacturer) {
        Optional<Console> returnVal = consoleRepository.findByManufacturer(manufacturer);
        return returnVal.isPresent() ? returnVal.get(): null;
    }

}
