package ir.maktab.maktabprojectstep2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktab.maktabprojectstep2.dto.request.OfferSaveRequest;
import ir.maktab.maktabprojectstep2.service.order.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;


@SpringBootTest
@AutoConfigureMockMvc
class OfferControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    OrderService orderService;


    @Test
    void saveNotOrder() throws Exception {
        OfferSaveRequest offerSaveRequest = OfferSaveRequest.builder()
                .periodOfTime(LocalDateTime.now())
                .proposedPrice(213)
                .startTime(LocalDateTime.now())
                .orderId(120L)
                .build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(UriComponentsBuilder.fromUriString("/offer")
                                .build().toUri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(offerSaveRequest)))
                .andReturn();
        Assertions.assertEquals(HttpStatus.CONFLICT.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    void save() throws Exception {
        OfferSaveRequest offerSaveRequest = OfferSaveRequest.builder()
                .periodOfTime(LocalDateTime.now())
                .proposedPrice(213)
                .startTime(LocalDateTime.now())
                .orderId(1L)
                .build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(UriComponentsBuilder.fromUriString("/offer")
                                .build().toUri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(offerSaveRequest)))
                .andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    void getAllByOrder() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(UriComponentsBuilder.fromUriString("/offer/order/1")
                                .build().toUri())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    void assignOffer() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(UriComponentsBuilder.fromUriString("/offer/1/order/1")
                                .build().toUri())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
}