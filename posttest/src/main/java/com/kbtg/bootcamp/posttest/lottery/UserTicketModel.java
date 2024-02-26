package com.kbtg.bootcamp.posttest.lottery;

import jakarta.persistence.*;

@Entity
@Table(name = "user_ticket")
public class UserTicketModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String name;

}
