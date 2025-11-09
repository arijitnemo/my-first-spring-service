package com.bose.my_services.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity // Maps this class to a database table
@Getter
@Setter
// This class represents the data structure for messages to be stored in the database table.
public class MessageEntity {

    @Id // Primary key for the H2 in memory database table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented since its primary key
    private Long id;
    private String content;

    // Default constructor required by Java Persistence API (JPA) to write and read data into the database.
    public MessageEntity() {}
}