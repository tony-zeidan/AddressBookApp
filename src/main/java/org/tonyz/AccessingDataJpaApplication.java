package org.tonyz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class AccessingDataJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class, args);
    }

    /*
    @Bean
    public CommandLineRunner tester(AddressBookRepository repo, BuddyInfoRepository repo2) {
        return (args) -> {
            AddressBook book = new AddressBook();
            book.addBuddy(new BuddyInfo("Tony", "6136080124"));
            book.addBuddy(new BuddyInfo("Ethan", "7777777777"));
            book.addBuddy(new BuddyInfo("Bruce", "8888888888"));
            book.addBuddy(new BuddyInfo("Tony", "8888888888"));

            Scanner scanner = new Scanner(System.in);

            // Find all books
            repo.save(book);
            log.info("Address books found with findAll():");
            log.info("-----------------------------------");
            for (AddressBook bk: repo.findAll()) {
                log.info(bk.toString());
            }

            // Find a book by ID
            System.out.println("Find book by ID --- Enter an ID: ");
            String idStr = scanner.nextLine();
            Long id = Long.valueOf(idStr);
            Optional<AddressBook> bookOpt = repo.findById(id);

            AddressBook bk = bookOpt.orElseGet(()->null);
            log.info("Input ID: " + idStr);
            log.info("Book found with the ID(" + idStr + "):");
            log.info("--------------------------------------");
            if (bookOpt.isPresent()) {
                log.info(bk.toString());
            } else {
                log.info("No book found with the ID: " + idStr);
            }


            // Find all buddies, they should have been persisted through the books
            log.info("Buddies found with findAll():");
            log.info("-----------------------------");
            for (BuddyInfo buddy: repo2.findAll()) {
                log.info(buddy.toString());
            }
            log.info("");


            // Find a buddy by ID
            System.out.println("Find buddy by ID --- Enter an ID: ");
            idStr = scanner.nextLine();
            id = Long.valueOf(idStr);

            Optional<BuddyInfo> buddyOpt = repo2.findById(id);

            BuddyInfo bud = buddyOpt.orElseGet(()->null);

            log.info("Input ID: " + idStr);
            log.info("Buddy found with the ID(" + idStr + "):");
            log.info("---------------------------------------");
            if (buddyOpt.isPresent()) {
                log.info(bud.toString());
            } else {
                log.info("No buddy found with the ID: " + idStr);
            }

            // Find buddies with a name
            System.out.println("Find by Name --- Enter a name: ");
            String nameStr = scanner.nextLine();

            log.info("Input name: " + nameStr);
            log.info("Buddy found with the name (" + nameStr + "):");
            log.info("---------------------------------------");
            for (BuddyInfo buddy: repo2.findByName(nameStr)) {
                log.info(buddy.toString());
            }

            // Find buddies with a phone number (in the future this should be unique?)
            System.out.println("Find by Phone# --- Enter a phone number: ");
            String phoneStr = scanner.nextLine();

            log.info("Input phone number: " + phoneStr);
            log.info("Buddies found with the phone# (" + phoneStr + "):");
            log.info("---------------------------------------");
            for (BuddyInfo buddy: repo2.findByPhoneNumber(phoneStr)) {
                log.info(buddy.toString());
            }
            log.info("");
        };
    }*/
}
