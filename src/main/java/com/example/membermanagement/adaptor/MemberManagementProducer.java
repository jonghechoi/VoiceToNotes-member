package com.example.membermanagement.adaptor;

import com.example.membermanagement.domain.Member;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface MemberManagementProducer {

    void createUser(Member member) throws JsonProcessingException;
}
