package com.example.membermanagement.controller;

import com.example.membermanagement.domain.MemberGroup;
import com.example.membermanagement.domain.dto.MemberRequestDto;
import com.example.membermanagement.exception.JoinException;
import com.example.membermanagement.exception.enumeration.ErrorCode;
import com.example.membermanagement.service.GroupService;
import com.example.membermanagement.service.JoinService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class MemberRestController {
    private final JoinService joinService;
    private final GroupService groupService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@Valid @RequestBody MemberRequestDto memberRequestDto) throws JsonProcessingException {
        if(joinService.joinMember(memberRequestDto)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            throw new JoinException(ErrorCode.INVALID_INPUT_VALUE);
        }
    }

    @PostMapping("/{masterUid}/group")
    public ResponseEntity<String> memberAdd(@PathVariable String masterUid,
                                            @RequestBody MemberGroup memberGroup,
                                            HttpServletRequest request) {
        if (!request.getAttribute("uid").equals(masterUid)) {
            return new ResponseEntity<>("요청한 유저와 토큰에 담긴 유저가 다릅니다.", HttpStatus.FORBIDDEN);
        }

        memberGroup.setMasterUid(masterUid);
        if(groupService.addMemberToGroup(memberGroup)) {
            return new ResponseEntity<>("멤버 추가 성공", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("멤버가 이미 그룹에 추가되어 있습니다.", HttpStatus.FORBIDDEN);
        }
    }
}
