package com.example.membermanagement.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "board_group")
public class MemberGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_group_seq")
    @SequenceGenerator(name = "board_group_seq", sequenceName = "board_group_seq", allocationSize = 1)
    private int id;
    private String bid;
    private String uid;
    private String masterUid;
}
