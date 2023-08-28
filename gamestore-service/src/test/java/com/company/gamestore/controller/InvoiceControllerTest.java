package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.FeeRepository;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.repository.TaxRepository;
import com.company.gamestore.service.InvoiceServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
    @MockBean
    private InvoiceRepository invoiceRepository;

    @MockBean
    private TaxRepository taxRepository;

    @MockBean
    private FeeRepository feeRepository;

    @MockBean
    private GameRepository gameRepository;

    @MockBean
    InvoiceServiceLayer invoiceServiceLayer;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    private InvoiceViewModel ivm;

    private Game game;

    @BeforeEach
    public void setUp() throws Exception {
        game = new Game();
        game.setTitle("Guilty Gear Xrd REV2");
        game.setEsrbRating("T");
        game.setDescription("Astonishing 3D cell animations and overwhelming content volume, the next-generation fighting game GUILTY GEAR Xrd REV 2 is now out on Steam!");
        game.setPrice(BigDecimal.valueOf(29.99));
        game.setStudio("Arc System Works");
        game.setQuantity(500);

        ivm = new InvoiceViewModel();
        ivm.setName("James Bosch");
        ivm.setStreet("55 Quick Street");
        ivm.setCity("Manhattan");
        ivm.setState("NY");
        ivm.setZipcode("10150");
        ivm.setItemType("game");
        ivm.setItemId(game.getId());
        ivm.setQuantity(5);
    }

    @Test
    void createInvoice() throws Exception {
        String inputJson = mapper.writeValueAsString(ivm);

        mockMvc.perform(post("/invoice").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getInvoiceById() throws Exception {
        mockMvc.perform(get("/invoice/1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAllInvoices() throws Exception {
        mockMvc.perform(get("/invoice"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void findInvoicesByCustomerName() throws Exception {
        mockMvc.perform(get("/invoice/name/James"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldReturn422Error() throws Exception{
        InvoiceViewModel ivm1 = new InvoiceViewModel();
        String inputJson = mapper.writeValueAsString(ivm1);
        mockMvc.perform(
                        post("/invoice").content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}
