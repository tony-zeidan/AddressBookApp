import org.junit.Test;
import org.tonyz.AddressBook;
import org.tonyz.BuddyInfo;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddressBookTest {

    @Test
    public void testAddBuddy() {
        AddressBook abook = new AddressBook();
        abook.addBuddy("Tony", "6136080124");
        List<BuddyInfo> bookBuddies = abook.getBuddies();
        assertEquals(1, bookBuddies.size());
        assertEquals("Tony", bookBuddies.get(0).getName());
        assertEquals("6136080124", bookBuddies.get(0).getPhoneNumber());
    }
}