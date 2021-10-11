package com.example.demo.model.req;

import lombok.Data;

@Data
public class StudentRegisterReq {
    private String name;
    private String gender;
    private String age;
    private String address;
    private String pob;
    private String dob;
    private String description;
}
