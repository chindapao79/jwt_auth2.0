package com.example.demo.controller;

import com.example.demo.model.entities.StudentRegister;
import com.example.demo.model.req.StudentRegisterReq;
import com.example.demo.service.StudentRegisterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/api/v1/")
public class StudentRegisterController {
    @Autowired
    private StudentRegisterService studentRegisterService;

    @GetMapping(value = "all")
    public ResponseEntity<Map<String, Object>> getAll() {
        return studentRegisterService.getAll();
    }

    @GetMapping(value = "getone/{id}")
    public ResponseEntity<StudentRegister> getOne(@RequestParam Integer id) {
        return studentRegisterService.getById(id);
    }

    @PostMapping(value = "post")
    public ResponseEntity<StudentRegister> create(@RequestBody StudentRegisterReq item) {
        return studentRegisterService.create(item);
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<StudentRegister> update(@PathVariable Integer id, @RequestBody StudentRegisterReq item) {
        return studentRegisterService.update(id, item);
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        return studentRegisterService.delete(id);
    }

}
