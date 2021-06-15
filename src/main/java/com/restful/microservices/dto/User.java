package com.restful.microservices.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {

    private Integer id;
    private String name;
    private Date birthDate;

}
