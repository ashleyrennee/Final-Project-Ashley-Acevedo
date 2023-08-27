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

    @BeforeEach
    public void setUp(){
        consoleRepository.deleteAll();
    }
    @Test
    public void shouldCreateConsole() throws Exception{
        Console console = new Console("ps5", "Sony", "50", "intel", new BigDecimal("330.00"),
                5);
        console = consoleRepository.save(console);
        Optional<Console> returnVal = consoleRepository.findById(console.getId());
        assertEquals (console, returnVal.get());
    }

    @Test
    public void shouldFindConsoleById() throws Exception{
        Console console = new Console("ps5", "Sony", "50", "intel", new BigDecimal("330.00"),
                5);
        console = consoleRepository.save(console);
        Optional<Console> returnVal = consoleRepository.findById(console.getId());
        assertEquals (console, returnVal.get());
    }

    @Test
    public void shouldGetAllConsoles() throws Exception{
        Console console = new Console("ps5", "Sony", "50", "intel", new BigDecimal("330.00"),
                5);
        Console console2 = new Console("ps4", "Sony", "50", "intel", new BigDecimal("130.00"),
                4);
        console = consoleRepository.save(console);
        console2 = consoleRepository.save(console2);
        List<Console> res = consoleRepository.findAll();
        assertEquals(res.size(), 2);
    }

    @Test
    public void shouldUpdateConsoleById() throws Exception{
        Console console = new Console("ps5", "Sony", "50", "intel", new BigDecimal("330.00"),
                5);
        console = consoleRepository.save(console);
        console.setQuantity(10);
        console.setPrice(new BigDecimal("300.00"));
        Console updatedConsole = consoleRepository.save(console);
        Optional<Console> resultConsole = consoleRepository.findById(console.getId());
        assertEquals(updatedConsole, resultConsole.get());
    }

    @Test
    public void shouldDeleteConsole()throws Exception{
        Console console = new Console("ps5", "Sony", "50", "intel", new BigDecimal("330.00"),
                5);
        console = consoleRepository.save(console);
        consoleRepository.deleteById(console.getId());
        assertFalse(consoleRepository.findById(console.getId()).isPresent());
    }

    @Test
    public void shouldFindConsoleByManufacturer() throws Exception{
        Console console = new Console("ps5", "Sony", "50", "intel", new BigDecimal("330.00"),
                5);
        console = consoleRepository.save(console);

        Console console2 = new Console("ps4", "Sony", "50", "intel", new BigDecimal("130.00"),
                4);
        console2 = consoleRepository.save(console2);
        Console console3 = new Console("xbox", "Microsoft", "50", "intel", new BigDecimal("110.00"),
                4);
        console3 = consoleRepository.save(console3);
        List<Console> returnCon = consoleRepository.findByManufacturer(console.getManufacturer());
        assertEquals(returnCon.size(), 2);
    }
}
