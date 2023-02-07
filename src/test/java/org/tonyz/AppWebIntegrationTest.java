package org.tonyz;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.tonyz.restservice.AddressBookController;
import org.tonyz.restservice.AppRestController;
import org.tonyz.restservice.BuddyInfoController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppWebIntegrationTest {


    @Autowired
    private AppRestController restController;

    @Autowired
    private AddressBookController addrController;

    @Autowired
    private BuddyInfoController buddyController;

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
        assertThat(restController).isNotNull();
        assertThat(addrController).isNotNull();
        assertThat(buddyController).isNotNull();
    }

    /**
     * This test ensures that the application can run with the mockMVC approach.
     */
    @Test
    public void testLocalOperation() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BuddyInfo> requestEntity = new HttpEntity<>(null, headers);

        // Create book and expect ok result
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/book", requestEntity, String.class)).contains("book saved");

        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/buddy?id=1&name=Tony&phoneNumber=6136080124", requestEntity, String.class)).contains("buddy added");


        String resp = this.restTemplate.getForObject("http://localhost:" + port + "/book", String.class);
        assertThat(resp).contains("BOOK ID: 1");
        assertThat(resp).contains("Tony");
        assertThat(resp).contains("6136080124");


        resp = this.restTemplate.getForObject("http://localhost:" + port + "/buddy/1", String.class);
        assertThat(resp).contains("Tony");
        assertThat(resp).contains("6136080124");


        this.restTemplate.delete("http://localhost:" + port + "/buddy?book_id=1&buddy_id=1");
        resp = this.restTemplate.getForObject("http://localhost:" + port + "/book", String.class);
        assertThat(resp).contains("BOOK ID: 1");
        assertThat(resp).doesNotContain("Tony");
        assertThat(resp).doesNotContain("6136080124");
    }
}