package com.company.gamestore.controller;

import com.company.gamestore.model.TShirt;
import com.company.gamestore.repository.TShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TShirtController {

    @Autowired
    TShirtRepository tShirtRepo;

    @PostMapping("/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt addTShirt(@RequestBody @Valid TShirt tShirt){return tShirtRepo.save(tShirt);}

    @GetMapping("/tshirts")
    public List<TShirt> getAllTShirts(){return tShirtRepo.findAll();}

    @GetMapping("/tshirts/{id}")
    public TShirt getTShirtById(@PathVariable int id){
        Optional<TShirt> returnVal = tShirtRepo.findById(id);
        if(returnVal.isPresent()){
            return returnVal.get();
        }
        return null;
    }

    @GetMapping("/tshirts/color/{color}")
    public List<TShirt> getTShirtByColor(@PathVariable String color){
       return tShirtRepo.findByColor(color);
    }

    @GetMapping("/tshirts/size/{size}")
    public List<TShirt> getTShirtBySize(@PathVariable String size){return tShirtRepo.findBySize(size);}

    @PutMapping("/tshirts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@RequestBody @Valid TShirt tShirt){tShirtRepo.save(tShirt);}

    @DeleteMapping("/tshirts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirtById(@PathVariable int id){tShirtRepo.deleteById(id);}

}
