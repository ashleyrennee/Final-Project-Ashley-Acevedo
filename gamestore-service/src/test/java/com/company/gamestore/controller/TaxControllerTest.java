package com.company.gamestore.controller;

import com.company.gamestore.model.Tax;
import com.company.gamestore.repository.TaxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaxController.class)
public class TaxControllerTest {

    @MockBean
    private TaxRepository taxRepository;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    private Tax tax;

    @BeforeEach
    public void setUp() throws Exception {
        tax = new Tax("NY", BigDecimal.valueOf(0.06));
    }

    @Test
    void addTax() throws Exception {
        String inputJson = mapper.writeValueAsString(tax);

        mockMvc.perform(post("/tax")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getTaxByState() throws Exception {
        mockMvc.perform(get("/tax/NY"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateTax() throws Exception {
        tax.setRate(BigDecimal.valueOf(0.09));

        String inputJson = mapper.writeValueAsString(tax);

        mockMvc.perform(put("/tax")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteTaxByState() throws Exception {
        mockMvc.perform(delete("/tax/NY"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn422Error() throws Exception{
        Tax tax1 = new Tax();
        String inputJson = mapper.writeValueAsString(tax1);
        mockMvc.perform(
                        post("/tax").content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}
