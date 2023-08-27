package com.company.gamestore.controller;

import com.company.gamestore.model.Fee;
import com.company.gamestore.repository.FeeRepository;
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

@WebMvcTest(FeeController.class)
public class FeeControllerTest {

    @MockBean
    private FeeRepository feeRepository;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    private Fee fee;

    @BeforeEach
    public void setUp() throws Exception {
        fee = new Fee("Console", BigDecimal.valueOf(14.99));
    }

    @Test
    void addFee() throws Exception {
        String inputJson = mapper.writeValueAsString(fee);

        mockMvc.perform(post("/fee")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getFeeByProductType() throws Exception {
        mockMvc.perform(get("/fee/Console"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateFee() throws Exception {
        fee.setFee(BigDecimal.valueOf(24.99));

        String inputJson = mapper.writeValueAsString(fee);

        mockMvc.perform(put("/fee")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteFeeByProductType() throws Exception {
        mockMvc.perform(delete("/fee/Console"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn422Error() throws Exception{
       Fee fee1 = new Fee();
       String inputJson = mapper.writeValueAsString(fee1);
       mockMvc.perform(
               post("/fee").content(inputJson)
                       .contentType(MediaType.APPLICATION_JSON)
       ).andDo(print())
               .andExpect(status().isUnprocessableEntity());
    }

}
