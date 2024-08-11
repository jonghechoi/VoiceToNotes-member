package com.example.membermanagement.adaptor;

public interface EmailProducer {

    boolean checkDuplicateEmail(String email);
    void templateMessage();
    void sendEmail(String email);
}
