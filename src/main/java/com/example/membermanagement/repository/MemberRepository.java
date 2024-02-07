package com.example.membermanagement.repository;

import com.example.membermanagement.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findMemberByUid(String uid);
}
