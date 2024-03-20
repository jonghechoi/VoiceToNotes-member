package com.example.membermanagement.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "BOARD_GROUP")
public class MemberGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_group_seq")
    @SequenceGenerator(name = "board_group_seq", sequenceName = "board_group_seq", allocationSize = 1)
    private Long id;
    private String bid;
    private String masterUid;
    private String uid;
}
