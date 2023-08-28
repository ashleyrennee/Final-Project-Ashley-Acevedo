package com.company.gamestore.repository;

import com.company.gamestore.model.*;
import com.company.gamestore.service.InvoiceServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InvoiceRepositoryTest {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    TaxRepository taxRepository;

    @Autowired
    FeeRepository feeRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    InvoiceServiceLayer invoiceServiceLayer;

    private InvoiceViewModel ivm;

    private Tax tax;

    private Fee fee;

    private Game game;

    @BeforeEach
    public void setUp() throws Exception {
        invoiceRepository.deleteAll();
        taxRepository.deleteAll();
        feeRepository.deleteAll();
        gameRepository.deleteAll();

        fee = new Fee("game",BigDecimal.valueOf(1.49));
        feeRepository.save(fee);

        tax = new Tax("NY", BigDecimal.valueOf(.06));
        taxRepository.save(tax);

        game = new Game();
        game.setTitle("Guilty Gear Xrd REV2");
        game.setEsrbRating("T");
        game.setDescription("Astonishing 3D cell animations and overwhelming content volume, the next-generation fighting game GUILTY GEAR Xrd REV 2 is now out on Steam!");
        game.setPrice(BigDecimal.valueOf(29.99));
        game.setStudio("Arc System Works");
        game.setQuantity(500);
        game = gameRepository.save(game);

        ivm = new InvoiceViewModel();
        ivm.setName("James Bosch");
        ivm.setStreet("55 Quick Street");
        ivm.setCity("Manhattan");
        ivm.setState(tax.getState());
        ivm.setZipcode("10150");
        ivm.setItemType("Game");
        ivm.setItemId(game.getId());
        ivm.setQuantity(5);

        ivm = invoiceServiceLayer.saveInvoice(ivm);
    }

    @Test
    public void createInvoice() throws Exception {
        InvoiceViewModel newInvoice = invoiceServiceLayer.getInvoiceById(ivm.getId());
        assertEquals(newInvoice, ivm);
        assertEquals(495, gameRepository.findById(game.getId()).get().getQuantity()); // Confirming game quantity lowers correctly
    }

    @Test
    public void shouldCalculateCorrectly() throws Exception {
        InvoiceViewModel newInvoice = invoiceServiceLayer.getInvoiceById(ivm.getId());
        assertEquals(newInvoice, ivm);
        assertEquals(BigDecimal.valueOf(160.44), newInvoice.getTotal());

        ivm.setQuantity(15);
        ivm = invoiceServiceLayer.saveInvoice(ivm);
        newInvoice = invoiceServiceLayer.getInvoiceById(ivm.getId());
        assertEquals(newInvoice, ivm);
        assertEquals(BigDecimal.valueOf(493.82), newInvoice.getTotal());
    }

    @Test
    public void getInvoiceById() throws Exception {
        InvoiceViewModel newInvoice = invoiceServiceLayer.getInvoiceById(ivm.getId());
        assertEquals(newInvoice, ivm);
    }

    @Test
    public void getAllInvoices() throws Exception {
        assertEquals(1, invoiceRepository.findAll().size());
    }

    @Test
    public void findInvoicesByCustomerName() throws Exception {
        List<InvoiceViewModel> invoices = invoiceServiceLayer.findInvoicesByCustomerName(ivm.getName());
        assertEquals(1, invoices.size());

        invoices = invoiceServiceLayer.findInvoicesByCustomerName(" ");
        assertEquals(0, invoices.size());
    }

}
