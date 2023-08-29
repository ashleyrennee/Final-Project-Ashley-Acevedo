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

    // Create
    @PostMapping("/tshirt")
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt addTShirt(@RequestBody @Valid TShirt tShirt){return tShirtRepo.save(tShirt);}

    // Read
    @GetMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirt getTShirtById(@PathVariable int id){
        Optional<TShirt> returnVal = tShirtRepo.findById(id);
        if(returnVal.isPresent()){
            return returnVal.get();
        }
        return null;
    }

    // Read All
    @GetMapping("/tshirt")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getAllTShirts(){return tShirtRepo.findAll();}

    // Update
    @PutMapping("/tshirt")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@RequestBody @Valid TShirt tShirt){tShirtRepo.save(tShirt);}

    // Delete
    @DeleteMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirtById(@PathVariable int id){tShirtRepo.deleteById(id);}

    // By Color
    @GetMapping("/tshirt/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtByColor(@PathVariable String color){
       return tShirtRepo.findByColor(color);
    }

    // By Size
    @GetMapping("/tshirt/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtBySize(@PathVariable String size){return tShirtRepo.findBySize(size);}

}
