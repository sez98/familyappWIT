package com.engage.wit.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reviews implements Serializable {
    private String username;
    private String name;
    private String topic;
    private String description;
}
//Topics can be- "Food, Places, Hospitals, Restraunts, etc"
