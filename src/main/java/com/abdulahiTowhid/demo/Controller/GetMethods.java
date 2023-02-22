package com.abdulahiTowhid.demo.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GetMethods {
    @GetMapping("/api/data")
    public People test() {

        return new People(
                List.of(new testRecord("Abdulahi",1), new testRecord("Ikran",2)));
    }

    record testRecord(String name, Integer num){}
    record People (List <testRecord> testRecord){}
}
