package com.company.gamestore.repository;


import com.company.gamestore.model.TShirt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class TShirtRepositoryTest {
    @Autowired
    TShirtRepository tShirtRepo;

    private TShirt tShirt;

    @BeforeEach
    public void setUp(){
        tShirtRepo.deleteAll();

        tShirt = new TShirt();
        tShirt.settShirtId(1);
        tShirt.setSize("Small");
        tShirt.setColor("Blue");
        tShirt.setPrice(BigDecimal.valueOf(20.99));
        tShirt.setQuantity(3);
        tShirt.setDescription("100% cotton t-shirt made in USA");

        tShirt = tShirtRepo.save(tShirt);
    }

    //Get all tshirts
    @Test
    void findAllTShirts(){
        List<TShirt> tShirts = tShirtRepo.findAll();
        assertEquals(1,tShirts.size());
    }

    //Get tshirts by id
    @Test
    void findTShirtsById(){
        Optional<TShirt> tShirt2 = tShirtRepo.findById(tShirt.gettShirtId());
        assertEquals(tShirt2.get(),tShirt);
    }


    //Get tshirt by color
    @Test
    void findByColor(){
        List<TShirt> tShirt2 = tShirtRepo.findByColor(tShirt.getColor());
        assertEquals("Blue",tShirt.getColor());
    }

    //Get tshirt by size
    @Test
    void findBySize(){
        List<TShirt> tShirt2 = tShirtRepo.findBySize(tShirt.getSize());
        assertEquals("Small",tShirt.getSize());
    }

    //Create tshirt
    @Test
    void createTShirt(){
        Optional<TShirt> tShirt2 = tShirtRepo.findById(tShirt.gettShirtId());
        assertEquals(tShirt2.get(),tShirt);
    }

    //Update tshirt
    @Test
    void updateBook(){
        tShirt.setDescription("UPDATED");

        tShirtRepo.save(tShirt);

        Optional<TShirt> tShirt2 =tShirtRepo.findById(tShirt.gettShirtId());
        assertEquals(tShirt2.get(),tShirt);
    }

    //Delete tshirt
    @Test
    void deleteTShirtById(){
        tShirtRepo.deleteById(tShirt.gettShirtId());

        Optional<TShirt> tShirt2 = tShirtRepo.findById(tShirt.gettShirtId());
        assertFalse(tShirt2.isPresent());
    }
}
