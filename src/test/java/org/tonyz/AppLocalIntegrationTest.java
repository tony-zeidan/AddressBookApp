package org.tonyz;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.tonyz.restservice.AddressBookController;
import org.tonyz.restservice.AppRestController;
import org.tonyz.restservice.BuddyInfoController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest
public class AppLocalIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private AppRestController restController;

    @Autowired
    private AddressBookController addrController;

    @Autowired
    private BuddyInfoController buddyController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(restController).isNotNull();
        assertThat(addrController).isNotNull();
        assertThat(buddyController).isNotNull();
    }

    /**
     * This test ensures that the application can run with the mockMVC approach.
     */
    @Test
    public void testLocalOperation() throws Exception {

        // Create book and expect ok result
        this.mockMvc.perform(post("/book")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("book saved")));

        // Add the test buddy and expect ok result
        this.mockMvc.perform(post("/buddy?id=1&name=Tony&phoneNumber=6136080124")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("buddy added")));

        // Expect that when getting books, the book ID and buddy are present
        this.mockMvc.perform(get("/book")).andDo(print()).andExpect(status().isOk())
                .andExpectAll(
                        content().string(containsString("BOOK ID: 1")),
                        content().string(containsString("Tony")),
                        content().string(containsString("6136080124"))
                );

        // Expect that when getting the buddies, the test buddy is present
        this.mockMvc.perform(get("/buddy")).andDo(print()).andExpect(status().isOk())
                .andExpectAll(
                        content().string(containsString("Tony")),
                        content().string(containsString("6136080124"))
                );

        this.mockMvc.perform(delete("/buddy?book_id=1&buddy_id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("buddy deleted")));


        this.mockMvc.perform(get("/book")).andDo(print()).andExpect(status().isOk())
        .andExpectAll(
                content().string(containsString("BOOK ID: 1")),
                content().string(not(containsString("Tony"))),
                content().string(not(containsString("6136080124")))
        );
    }
}