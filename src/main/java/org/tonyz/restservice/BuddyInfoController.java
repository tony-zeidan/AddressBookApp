package org.tonyz.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tonyz.AddressBook;
import org.tonyz.AddressBookRepository;
import org.tonyz.BuddyInfo;
import org.tonyz.BuddyInfoRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BuddyInfoController {

    @Autowired
    public final BuddyInfoRepository buddyRepo;

    @Autowired
    public final AddressBookRepository bookRepo;

    public BuddyInfoController(BuddyInfoRepository buddyRepo, AddressBookRepository bookRepo) {
        this.buddyRepo = buddyRepo;
        this.bookRepo = bookRepo;
    }

    @GetMapping("/getbuddies")
    public String getBuddies(Model model) {
        model.addAttribute("buddies", buddyRepo.findAll());
        return "buddies";
    }

    @GetMapping(value="/getbuddy")
    public String getBuddy() {
        return "getbuddy";
    }

    @GetMapping(value="/findbuddy")
    public String getBuddySubmit(@RequestParam(value="id") String id, Model model) {
        BuddyInfo bud = buddyRepo.findById(Long.parseLong(id));
        List<BuddyInfo> budList = new ArrayList<>();
        budList.add(bud);
        model.addAttribute("buddies", budList);
        return "buddies";
    }

    @GetMapping(value="/addbuddy")
    public String addBuddy() {
        return "addbuddy";
    }

    @PostMapping(value="/addbuddy")
    public String addBuddySubmit(@ModelAttribute BuddyInfoRequest buddy, Model model) {
        System.out.println(buddy);
        AddressBook book = bookRepo.findById(Long.parseLong(buddy.getBookID()));
        BuddyInfo bud = new BuddyInfo();
        bud.setName(buddy.getName());
        bud.setPhoneNumber(buddy.getPhoneNumber());
        buddyRepo.save(bud);
        book.addBuddy(bud);
        bookRepo.save(book);
        model.addAttribute("buddies", buddyRepo.findAll());
        return "buddies";
    }

    @GetMapping(value="/deletebuddy")
    public String removeBuddy() {
        return "deletebuddy";
    }

    @PostMapping(value="/deletebuddy")
    public String removeBuddySubmit(@ModelAttribute BuddyInfoRequest buddy, Model model) {
        AddressBook book = bookRepo.findById(Long.parseLong(buddy.getBookID()));
        BuddyInfo bud = buddyRepo.findById(Long.parseLong(String.valueOf(buddy.getBuddyID())));
        book.removeBuddy(bud);
        bookRepo.save(book);
        buddyRepo.delete(bud);
        model.addAttribute("buddies", buddyRepo.findAll());
        return "buddies";
    }
}