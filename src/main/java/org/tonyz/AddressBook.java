package org.tonyz;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade= CascadeType.ALL)
    private List<BuddyInfo> book;

    public AddressBook() {
        this.book = new ArrayList<>();
    }

    public void addBuddy(String name, String phoneNumber) {
        this.addBuddy(new BuddyInfo(name, phoneNumber));
    }

    public void addBuddy(BuddyInfo buddy) {
        this.book.add(buddy);
    }

    public void removeBuddy(BuddyInfo buddy) {
        this.book.remove(buddy);
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setBuddies(List<BuddyInfo> buddies) {
        this.book = buddies;
    }


    public List<BuddyInfo> getBuddies() {
        return this.book;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BOOK: ");
        for (BuddyInfo b : this.book) {
            sb.append(b.toString()).append("\t\t");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AddressBook abook = new AddressBook();
        abook.setId((long) 1);
        abook.addBuddy("Tony", "6136080124");
        abook.addBuddy("Ethan", "6136086124");
        abook.addBuddy("Anthony", "6137780124");
        System.out.println(abook);
    }
}
