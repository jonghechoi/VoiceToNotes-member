package com.example.membermanagement.adaptor.impl;

import com.example.membermanagement.adaptor.EmailProducer;

public class EmailProducerImpl implements EmailProducer {

    @Override
    public boolean checkDuplicateEmail(String email) {
        return false;
    }

    @Override
    public void templateMessage() {

    }

    @Override
    public void sendEmail(String email) {

    }
}
