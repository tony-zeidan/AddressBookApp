package org.tonyz.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tonyz.AddressBook;
import org.tonyz.AddressBookRepository;
import org.tonyz.BuddyInfo;
import org.tonyz.BuddyInfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class AddressBookController {

    @Autowired
    public final AddressBookRepository bookRepo;

    @Autowired
    public final BuddyInfoRepository buddyRepo;

    public AddressBookController(AddressBookRepository bookRepo, BuddyInfoRepository buddyRepo) {
        this.bookRepo = bookRepo;
        this.buddyRepo = buddyRepo;
    }

    @GetMapping("/getbooks")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepo.findAll());
        return "books";
    }

    @GetMapping(value="/getbook")
    public String getBook() {
        return "getbook";
    }

    @GetMapping(value="/getbook/{id}")
    public String getBookSubmit(@PathVariable String id, Model model) {
        AddressBook bk = bookRepo.findById(Long.parseLong(id));
        List<AddressBook> bkList = new ArrayList<>();
        bkList.add(bk);
        model.addAttribute("books", bkList);
        return "books";
    }

    @GetMapping(value="/createbook")
    public String addBook() {
        return "addbook";
    }

    @PostMapping(value="/createbook")
    public String addBookSubmit(Model model) {
        AddressBook bk = new AddressBook();
        bookRepo.save(bk);
        model.addAttribute("books", bookRepo.findAll());
        return "books";
    }

    @GetMapping(value="/deletebook")
    public String deleteBook() {
        return "deletebook";
    }

    @PostMapping(value="/deletebook")
    public String deleteBookSubmit(@ModelAttribute AddressBookRequest book, Model model) {
        AddressBook bk = bookRepo.findById(Long.parseLong(book.getId()));
        List<BuddyInfo> buddies = new ArrayList<>(bk.getBuddies());
        for (BuddyInfo bud: buddies) {
            bk.removeBuddy(bud);
            buddyRepo.delete(bud);
        }
        bookRepo.delete(bk);
        model.addAttribute("buddies", buddyRepo.findAll());
        return "buddies";
    }
}