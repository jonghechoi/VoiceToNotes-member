package com.example.membermanagement.adaptor;

import com.example.membermanagement.domain.Member;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MemberKafkaProducer {

    void createUser(Member member) throws JsonProcessingException;
}
