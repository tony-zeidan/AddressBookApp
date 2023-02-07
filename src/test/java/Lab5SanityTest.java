import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.tonyz.restservice.AddressBookController;
import org.tonyz.restservice.AppRestController;
import org.tonyz.restservice.BuddyInfoController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Lab5SanityTest {

    @Autowired
    private AppRestController postController;

    @Autowired
    private AddressBookController addrGetController;

    @Autowired
    private BuddyInfoController budGetController;

    @Test
    void contextLoads() throws Exception {
        assertThat(postController).isNotNull();
        assertThat(addrGetController).isNotNull();
        assertThat(budGetController).isNotNull();
    }

}