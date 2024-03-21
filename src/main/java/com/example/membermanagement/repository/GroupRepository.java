package com.example.membermanagement.repository;

import com.example.membermanagement.domain.MemberGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<MemberGroup, Long> {
    Optional<MemberGroup> findMemberGroupByBidAndMasterUidAndUid(String boardId, String masterUid, String joinMemberId);
}
