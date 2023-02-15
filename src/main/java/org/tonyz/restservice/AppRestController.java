package org.tonyz.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tonyz.AddressBook;
import org.tonyz.AddressBookRepository;
import org.tonyz.BuddyInfo;
import org.tonyz.BuddyInfoRepository;

import java.util.Map;

@RestController
public class AppRestController {

    @Autowired
    public final AddressBookRepository bookRepo;

    @Autowired
    public final BuddyInfoRepository buddyRepo;

    public AppRestController(AddressBookRepository bookRepo, BuddyInfoRepository buddyRepo) {
        this.bookRepo = bookRepo;
        this.buddyRepo = buddyRepo;
    }

    @PostMapping(value="/book")
    public String addBook() {
        AddressBook book = new AddressBook();
        bookRepo.save(book);
        return "book saved";
    }

    @PostMapping(value="/buddy")
    public ResponseEntity<BuddyInfo> addBuddy(@RequestBody Map<String, String> json) {
        AddressBook book = bookRepo.findById(Long.parseLong(json.get("id")));
        BuddyInfo buddy = new BuddyInfo();
        buddy.setName(json.get("name"));
        buddy.setPhoneNumber(json.get("phoneNumber"));
        book.addBuddy(buddy);
        buddyRepo.save(buddy);
        bookRepo.save(book);
        return new ResponseEntity<>(buddy, HttpStatus.CREATED);
    }
    /*public String addBuddy(@RequestParam(name="id") String id, @RequestParam(name="name", defaultValue = "UNKNOWN") String name, @RequestParam(name="phoneNumber", defaultValue = "UNKNOWN") String phoneNumber) {
        AddressBook book = bookRepo.findById(Long.parseLong(id));
        BuddyInfo buddy = new BuddyInfo();
        buddy.setName(name);
        buddy.setPhoneNumber(phoneNumber);
        book.addBuddy(buddy);
        buddyRepo.save(buddy);
        bookRepo.save(book);
        return "buddy added";
    }*/

    @DeleteMapping(value="/buddy")
    public ResponseEntity<BuddyInfo> removeBuddy(@RequestBody Map<String, String> json) {
        AddressBook book = bookRepo.findById(Long.parseLong(json.get("book_id")));
        BuddyInfo buddy = buddyRepo.findById(Long.parseLong(json.get("buddy_id")));
        book.removeBuddy(buddy);
        bookRepo.save(book);
        buddyRepo.delete(buddy);
        return new ResponseEntity<BuddyInfo>(buddy, HttpStatus.OK);
    }
    /*
    public String removeBuddy(@RequestParam(name="book_id") String bookId, @RequestParam(name="buddy_id") String buddyId) {
        AddressBook book = bookRepo.findById(Long.parseLong(bookId));
        BuddyInfo buddy = buddyRepo.findById(Long.parseLong(buddyId));
        book.removeBuddy(buddy);
        bookRepo.save(book);
        buddyRepo.delete(buddy);
        return "buddy deleted";
    }*/
}