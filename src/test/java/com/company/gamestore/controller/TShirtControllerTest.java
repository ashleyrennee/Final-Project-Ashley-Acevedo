package com.company.gamestore.controller;

import com.company.gamestore.model.TShirt;
import com.company.gamestore.repository.TShirtRepository;
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

@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {
    @MockBean
    private TShirtRepository tShirtRepo;

    @Autowired
    private MockMvc mockMvc;

    private TShirt tShirt;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        tShirt = new TShirt();
        tShirt.setSize("Small");
        tShirt.setColor("Blue");
        tShirt.setPrice(BigDecimal.valueOf(20.99));
        tShirt.setQuantity(3);
        tShirt.setDescription("100% cotton t-shirt made in USA");
    }

    @Test
    void addTShirt() throws Exception{
        String input = mapper.writeValueAsString(tShirt);

        mockMvc.perform(post("/tshirts")
                .content(input)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getTShirtById() throws Exception{
        mockMvc.perform(get("/tshirts/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void getAllTShirts() throws Exception{
        mockMvc.perform(get("/tshirts"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllShirtsByColor() throws Exception{
        mockMvc.perform(get("/tshirts/color/Blue"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllShirtsBySize() throws Exception{
        mockMvc.perform(get("/tshirts/size/Small"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateTShirt() throws Exception{
        tShirt.setColor("Pink");

        String input = mapper.writeValueAsString(tShirt);

        mockMvc.perform(put("/tshirts")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteTShirt() throws Exception{
        mockMvc.perform(delete("/tshirts/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn422Error() throws Exception{
        TShirt tShirt1 = new TShirt();
        String inputJson = mapper.writeValueAsString(tShirt1);
        mockMvc.perform(
                        post("/tshirts").content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}
