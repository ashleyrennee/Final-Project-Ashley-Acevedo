package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
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
public class ConsoleRepositoryTests {
    @Autowired
    ConsoleRepository consoleRepository;

    private Console console1;
    private Console console2;
    private Console console3;

    @BeforeEach
    public void setUp(){
        consoleRepository.deleteAll();
        console1 = new Console("ps5", "Sony", "50", "intel", new BigDecimal("330.00"),
                5);
        console2 = new Console("ps4", "Sony", "50", "intel", new BigDecimal("130.00"),
                4);
        console3 = new Console("xbox", "Microsoft", "50", "intel", new BigDecimal("110.00"),
                4);
        console1 = consoleRepository.save(console1);
        console2 = consoleRepository.save(console2);
        console3 = consoleRepository.save(console3);
    }

    @Test
    public void shouldCreateConsole() throws Exception{
        Optional<Console> returnVal = consoleRepository.findById(console1.getId());
        assertEquals (console1, returnVal.get());
    }

    @Test
    public void shouldFindConsoleById() throws Exception{
        Optional<Console> returnVal = consoleRepository.findById(console1.getId());
        assertEquals (console1, returnVal.get());
    }

    @Test
    public void shouldGetAllConsoles() throws Exception{
        List<Console> res = consoleRepository.findAll();
        assertEquals(res.size(), 3);
    }

    @Test
    public void shouldUpdateConsoleById() throws Exception{
        console1.setQuantity(10);
        console1.setPrice(new BigDecimal("300.00"));
        Console updatedConsole = consoleRepository.save(console1);
        Optional<Console> resultConsole = consoleRepository.findById(console1.getId());
        assertEquals(updatedConsole, resultConsole.get());
    }

    @Test
    public void shouldDeleteConsole()throws Exception{
        consoleRepository.deleteById(console1.getId());
        assertFalse(consoleRepository.findById(console1.getId()).isPresent());
    }

    @Test
    public void shouldFindConsoleByManufacturer() throws Exception{
        List<Console> returnCon = consoleRepository.findByManufacturer(console1.getManufacturer());
        assertEquals(returnCon.size(), 2);
    }

}
