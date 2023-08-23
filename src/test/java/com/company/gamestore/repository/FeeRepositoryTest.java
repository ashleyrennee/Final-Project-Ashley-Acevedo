package com.company.gamestore.repository;

import com.company.gamestore.model.Fee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FeeRepositoryTest {

    @Autowired
    FeeRepository feeRepository;

    @BeforeEach
    public void setUp() throws Exception {
        feeRepository.deleteAll();
        feeRepository.save(new Fee("Console", BigDecimal.valueOf(14.99)));
        feeRepository.save(new Fee("T-Shirt", BigDecimal.valueOf(1.98)));
        feeRepository.save(new Fee("Game", BigDecimal.valueOf(1.49)));
    }

    @Test
    public void addFee() {
        Optional<Fee> newFee = feeRepository.findByProductType("Console");
        Fee expectedFee = new Fee("Console", BigDecimal.valueOf(14.99));
        assertEquals(expectedFee, newFee.get());
    }

    @Test
    public void getAllFees() {
        assertEquals(3, feeRepository.findAll().size());
    }

    @Test
    public void updateFee() {
        assertEquals(BigDecimal.valueOf(14.99), feeRepository.findByProductType("Console").get().getFee());
        Fee updatedFee = new Fee("Console", BigDecimal.valueOf(24.99));
        feeRepository.save(updatedFee);
        Optional<Fee> newFee = feeRepository.findByProductType("Console");
        assertEquals(BigDecimal.valueOf(24.99), newFee.get().getFee());
    }

    @Test
    @Transactional
    public void deleteFee() {
        assertEquals(true, feeRepository.findByProductType("Console").isPresent());
        feeRepository.deleteByProductType("Console");
        assertEquals(false, feeRepository.findByProductType("Console").isPresent());
    }

}
