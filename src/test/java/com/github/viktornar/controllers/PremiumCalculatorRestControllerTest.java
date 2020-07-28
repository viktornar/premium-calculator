package com.github.viktornar.controllers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(PremiumCalculatorRestController.class)
class PremiumCalculatorRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final static String CONTRACT_JSON = "{\n" +
            "    \"id\":\"LT2078-5252-55\",\n" +
            "    \"statusType\":\"APPROVED\",\n" +
            "    \"customers\":[\n" +
            "       {\n" +
            "          \"name\":\"Jonas Jonaitis\",\n" +
            "          \"costumerType\":\"FATHER\",\n" +
            "          \"cards\":[\n" +
            "             {\n" +
            "                \"cardType\":\"DEBIT\",\n" +
            "                \"insuredSum\":100.0,\n" +
            "                \"riskType\":\"FRAUD\"\n" +
            "             },\n" +
            "             {\n" +
            "                \"cardType\":\"DEBIT\",\n" +
            "                \"insuredSum\":8.0,\n" +
            "                \"riskType\":\"THEFT\"\n" +
            "             }\n" +
            "          ]\n" +
            "       }\n" +
            "    ]\n" +
            " }";

    @Test
    void shouldReturnGivenContractPremium() throws Exception {
        ResultActions actions = mockMvc
                .perform(post("/api/v1/premiumForGivenContract")
                        .content(CONTRACT_JSON).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        actions.andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.premium").value(2.28));
    }
}