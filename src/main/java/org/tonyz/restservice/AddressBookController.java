package org.tonyz.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tonyz.AddressBook;
import org.tonyz.AddressBookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class AddressBookController {

    @Autowired
    public final AddressBookRepository bookRepo;

    public AddressBookController(AddressBookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepo.findAll());
        return "books";
    }

    @GetMapping(value="/{id}")
    public String getBook(@PathVariable String id, Model model) {
        AddressBook bk = bookRepo.findById(Long.parseLong(id));
        List<AddressBook> bkList = new ArrayList<>();
        bkList.add(bk);
        model.addAttribute("books", bkList);
        return "books";
    }

    @GetMapping(value="/addbook")
    public String addBook() {
        AddressBook bk = new AddressBook();
        bookRepo.save(bk);
        return "index";
    }
}