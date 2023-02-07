package org.tonyz.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tonyz.AddressBook;
import org.tonyz.AddressBookRepository;

import java.util.Optional;

@Controller
public class AddressBookController {

    @Autowired
    public final AddressBookRepository bookRepo;

    public AddressBookController(AddressBookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/book")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepo.findAll());
        return "books";
    }

    @GetMapping(value="/book/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        Optional<AddressBook> bk = bookRepo.findById(id);
        AddressBook book = bk.orElseGet(null);
        if (bk.isPresent()) {
            model.addAttribute("book", book.getBuddies().toString());
        } else {
            model.addAttribute("book", "UNKNOWN");
        }
        return "book";
    }
}