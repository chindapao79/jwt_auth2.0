package com.example.demo.service;

import com.example.demo.model.entities.StudentRegister;
import com.example.demo.model.req.StudentRegisterReq;
import com.example.demo.repository.StudentRegisterRepository;
import com.example.demo.security.FormatString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentRegisterService {

    @Autowired
    private StudentRegisterRepository studentRegisterRepository;

    @Autowired
    private FormatString formatString;

    public ResponseEntity<Map<String, Object>> getAll() {
        List<StudentRegister> studentRegister = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        studentRegisterRepository.findAll().forEach(studentRegister::add);
        map.put("REC", studentRegister);
        map.put("STATUS", true);
        try {
            if (studentRegister.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<StudentRegister> getById(Integer id) {
        Optional<StudentRegister> existingItemOptional = studentRegisterRepository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<StudentRegister>(new StudentRegister(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<StudentRegister> create(StudentRegisterReq item) {
        StudentRegister studentRegister = new StudentRegister();
        studentRegister.setName(formatString.fmString(item.getName()));
        studentRegister.setGender(formatString.fmString(item.getGender().toLowerCase()));
        studentRegister.setAge(formatString.fmString(item.getAddress()));
        studentRegister.setAddress(formatString.fmString(item.getAddress().toLowerCase()));
        studentRegister.setDob(formatString.fmString(item.getDob().toLowerCase()));
        studentRegister.setPob(formatString.fmString(item.getPob().toLowerCase()));
        studentRegister.setDescription(formatString.fmString(item.getDescription().toLowerCase()));
        try {
            return new ResponseEntity<>(studentRegisterRepository.save(studentRegister), HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<StudentRegister> update(Integer id, StudentRegisterReq item) {
        Optional<StudentRegister> existingItemOptional = studentRegisterRepository.findById(id);
        if (existingItemOptional.isPresent()) {
            StudentRegister existingItem = existingItemOptional.get();
            existingItem.setName(formatString.fmString(item.getName()));
            existingItem.setGender(formatString.fmString(item.getGender().toLowerCase()));
            existingItem.setAge(formatString.fmString(item.getAddress()));
            existingItem.setAddress(formatString.fmString(item.getAddress().toLowerCase()));
            existingItem.setDob(formatString.fmString(item.getDob().toLowerCase()));
            existingItem.setPob(formatString.fmString(item.getPob().toLowerCase()));
            existingItem.setDescription(formatString.fmString(item.getDescription().toLowerCase()));
            return new ResponseEntity<>(studentRegisterRepository.save(existingItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<StudentRegister>(new StudentRegister(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> delete(Integer id) {
        try {
            studentRegisterRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
