package com.lbt.construction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ConstructionApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createsASiteAndRejectsSupplyForAnUnknownSite() throws Exception {
        mockMvc.perform(post("/api/sites")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "siteCode": "SITE-1",
                                  "name": "Duplex Block A",
                                  "address": "Bhubaneswar",
                                  "phase": "FOUNDATION"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.siteCode").value("SITE-1"));

        mockMvc.perform(post("/api/material-supplies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "siteCode": "MISSING",
                                  "materialType": "CEMENT",
                                  "quantity": 50,
                                  "unit": "BAG",
                                  "supplierName": "Local Yard"
                                }
                                """))
                .andExpect(status().isNotFound());
    }
}
