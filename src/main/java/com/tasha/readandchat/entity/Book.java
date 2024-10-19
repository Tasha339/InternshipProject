package com.tasha.readandchat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(unique = true)
    @Lob
    private byte[] bookData;

//    @ManyToMany(mappedBy = "userBooks")
//    private List<User> readers;
}
