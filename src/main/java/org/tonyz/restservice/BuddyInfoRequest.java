package org.tonyz.restservice;

public class BuddyInfoRequest {

    private String bookID;

    private String buddyID;

    private String name;

    private String phoneNumber;

    public BuddyInfoRequest() {
        this.bookID = null;
        this.buddyID = null;
        this.name = null;
        this.phoneNumber = null;
    }


    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBuddyID() {
        return buddyID;
    }

    public void setBuddyID(String buddyID) {
        this.buddyID = buddyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
