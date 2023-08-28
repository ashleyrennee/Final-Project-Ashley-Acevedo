package com.company.gamestore.controller;

import com.company.gamestore.model.Tax;
import com.company.gamestore.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class TaxController {

    @Autowired
    TaxRepository taxRepository;

    @PostMapping(path = "/tax")
    @ResponseStatus(HttpStatus.CREATED)
    public Tax addTax(@RequestBody @Valid Tax tax) {
        return taxRepository.save(tax);
    }

    @GetMapping("/tax/{state}")
    @ResponseStatus(HttpStatus.OK)
    public Tax getTaxByState(@PathVariable(name = "state") String state) {
        Optional<Tax> returnVal = taxRepository.findByState(state);
        return returnVal.isPresent() ? returnVal.get() : null;
    }

    @PutMapping(path = "/tax")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Tax updateTax(@RequestBody @Valid Tax tax) { return taxRepository.save(tax); }

    @DeleteMapping("/tax/{state}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaxByState(@PathVariable String state) {
        taxRepository.deleteByState(state);
    }

}
