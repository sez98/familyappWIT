package com.engage.wit.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user")
public class User implements Serializable {

    private static final long SerialVersionUID = 10l;
    @Id
    private String username;
    private String name;

    private String gender;

    private String email;
    private String password;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date dob;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date doa;

    @Lob
    private ArrayList<String> children;

    private String mom_username;
    private String dad_username;

    private String married;
    private String spouse_username;

    private String bloodGroup;

    private String profession;
    //Give options of the top 10 main professions
    private String state;
    private String country;

    @Lob
    private ArrayList<String> disease;

    @Lob
    private ArrayList<String> hobbies;

    @Lob
    private ArrayList<Reviews> reviews;

    @Lob
    private ArrayList<Post> posts;

}
