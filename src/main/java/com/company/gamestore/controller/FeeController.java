package com.company.gamestore.controller;

import com.company.gamestore.model.Fee;
import com.company.gamestore.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class FeeController {

    @Autowired
    FeeRepository feeRepository;

    @PostMapping(path = "/fee")
    @ResponseStatus(HttpStatus.CREATED)
    public Fee addFee(@RequestBody @Valid Fee fee) {
        return feeRepository.save(fee);
    }

    @GetMapping("/fee/{product_type}")
    @ResponseStatus(HttpStatus.OK)
    public Fee getFeeByProductType(@PathVariable(name = "product_type") String productType) {
        Optional<Fee> returnVal = feeRepository.findByProductType(productType);
        return returnVal.isPresent() ? returnVal.get() : null;
    }

    @PutMapping(path = "/fee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Fee updateFee(@RequestBody Fee fee) { return feeRepository.save(fee); }

    @DeleteMapping("/fee/{product_type}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeeByProductType(@PathVariable(name = "product_type") String productType) {
        feeRepository.deleteByProductType(productType);
    }

}
