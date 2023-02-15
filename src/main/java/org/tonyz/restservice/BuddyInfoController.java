package org.tonyz.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tonyz.BuddyInfo;
import org.tonyz.BuddyInfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/buddy")
public class BuddyInfoController {

    @Autowired
    public final BuddyInfoRepository buddyRepo;

    public BuddyInfoController(BuddyInfoRepository buddyRepo) {
        this.buddyRepo = buddyRepo;
    }

    @GetMapping("")
    public String getBuddies(Model model) {
        model.addAttribute("buddies", buddyRepo.findAll());
        return "buddies";
    }

    @GetMapping(value="/{id}")
    public String getBuddy(@PathVariable String id, Model model) {
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

    @GetMapping(value="/deletebuddy")
    public String deleteBuddy() {
        return "deletebuddy";
    }
}