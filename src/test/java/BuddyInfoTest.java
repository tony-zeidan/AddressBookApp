import org.junit.Test;
import org.tonyz.BuddyInfo;

import static org.junit.Assert.assertEquals;


public class BuddyInfoTest {

    @Test
    public void testGetName() {
        BuddyInfo b1 = new BuddyInfo("Tony", "1991999191");
        assertEquals("Tony", b1.getName());
    }

    @Test
    public void testGetPhoneNumber() {
        BuddyInfo b1 = new BuddyInfo("Tony", "1991999191");
        assertEquals("1991999191", b1.getPhoneNumber());
    }
}