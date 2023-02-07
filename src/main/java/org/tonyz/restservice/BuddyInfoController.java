package org.tonyz.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tonyz.BuddyInfo;
import org.tonyz.BuddyInfoRepository;

import java.util.Optional;

@Controller
public class BuddyInfoController {

    @Autowired
    public final BuddyInfoRepository buddyRepo;

    public BuddyInfoController(BuddyInfoRepository buddyRepo) {
        this.buddyRepo = buddyRepo;
    }

    @GetMapping("/buddy")
    public String getBuddies(Model model) {
        model.addAttribute("buddies", buddyRepo.findAll().toString());
        return "buddies";
    }

    @GetMapping(value="/buddy/{id}")
    public String getBuddy(@PathVariable Long id, Model model) {
        Optional<BuddyInfo> bud = buddyRepo.findById(id);
        BuddyInfo buddy = bud.orElseGet(null);
        if (bud.isPresent()) {
            model.addAttribute("name", buddy.getName());
            model.addAttribute("phoneNumber", buddy.getPhoneNumber());
        } else {
            model.addAttribute("name", "UNKNOWN");
            model.addAttribute("phoneNumber", "UNKNOWN");
        }
        return "buddy";
    }
}