package com.example.membermanagement.service.impl;

import com.example.membermanagement.domain.MemberGroup;
import com.example.membermanagement.repository.GroupRepository;
import com.example.membermanagement.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public boolean addMemberToGroup(MemberGroup memberGroup) {
        Optional<MemberGroup> optionalMemberGroup =  groupRepository.findMemberGroupByBidAndMasterUidAndUid(memberGroup.getBid(), memberGroup.getMasterUid(), memberGroup.getUid());

        if(optionalMemberGroup.isPresent()) {
            return false;
        }
        else {
            groupRepository.save(memberGroup);
            return true;
        }
    }
}
