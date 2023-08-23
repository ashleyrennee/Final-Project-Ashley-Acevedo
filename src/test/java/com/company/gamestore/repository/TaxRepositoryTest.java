package com.company.gamestore.repository;

import com.company.gamestore.model.Tax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaxRepositoryTest {

    @Autowired
    TaxRepository taxRepository;

    @BeforeEach
    public void setUp() throws Exception {
        taxRepository.deleteAll();
        taxRepository.save(new Tax("NY", BigDecimal.valueOf(0.06)));
        taxRepository.save(new Tax("NJ", BigDecimal.valueOf(0.05)));
        taxRepository.save(new Tax("CA", BigDecimal.valueOf(0.06)));
        taxRepository.save(new Tax("FL", BigDecimal.valueOf(0.06)));
    }

    @Test
    public void addTax() {
        Optional<Tax> newTax = taxRepository.findByState("NY");
        Tax expectedTax = new Tax("NY", BigDecimal.valueOf(0.06));
        assertEquals(expectedTax, newTax.get());
    }

    @Test
    public void getAllTaxes() {
        assertEquals(4, taxRepository.findAll().size());
    }

    @Test
    public void updateTax() {
        assertEquals(BigDecimal.valueOf(0.06), taxRepository.findByState("NY").get().getRate());
        Tax updatedTax = new Tax("NY", BigDecimal.valueOf(0.09));
        taxRepository.save(updatedTax);
        Optional<Tax> newTax = taxRepository.findByState("NY");
        assertEquals(BigDecimal.valueOf(0.09), newTax.get().getRate());
    }

    @Test
    @Transactional
    public void deleteTax() {
        assertEquals(true, taxRepository.findByState("NY").isPresent());
        taxRepository.deleteByState("NY");
        assertEquals(false, taxRepository.findByState("NY").isPresent());
    }

}
