package com.twitter.activity.service;

import com.twitter.activity.model.Event;
import com.twitter.activity.repository.EventRepository;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class TwitterCrcService implements ITwitterCrcService {

    @Value("${twitter.consumer.secret}")
    private String consumerSecret;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public String validateCrc(String crcToken) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(consumerSecret.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secretKey);
        String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(crcToken.getBytes("UTF-8")));
        Map<String, String> response = new HashMap<>(1);
        response.put("response_token", "sha256="+hash);
        JSONObject jsonObject = new JSONObject(response);
        return jsonObject.toString();
    }

    @Override
    public void receiveEvents(String json) {
        Event event = new Event();
        event.setJsonEntity(json);
        event.setDate(new Timestamp(System.currentTimeMillis()));
        eventRepository.save(event);
        System.out.println(json);
    }

}

