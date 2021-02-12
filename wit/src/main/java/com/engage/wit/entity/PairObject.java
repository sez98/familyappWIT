package com.engage.wit.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PairObject {
    private User u1;
    private Boolean root;
    private Integer k;
    private Boolean flag;
}