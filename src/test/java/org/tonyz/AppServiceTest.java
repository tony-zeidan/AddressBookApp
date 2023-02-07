package org.tonyz;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.tonyz.restservice.AppRestController;


@WebMvcTest
public class AppServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppRestController postController;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(postController.addBuddy("1", "Tony", "6136080124")).thenReturn("Hello, Mock");
        this.mockMvc.perform(get("/buddy")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Tony")));
    }
}