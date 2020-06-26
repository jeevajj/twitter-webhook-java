package com.twitter.activity.controller;

import com.twitter.activity.service.ITwitterCrcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    @Autowired
    private ITwitterCrcService twitterCrcService;

    @GetMapping("/webhook")
    public ResponseEntity<?> validateToken(@RequestParam("crc_token") String token){
        try{
            return new ResponseEntity<>(twitterCrcService.validateCrc(token), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/webhook")
    public ResponseEntity<?> consumeEvents(@RequestBody String json){
        try{
            twitterCrcService.receiveEvents(json);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

