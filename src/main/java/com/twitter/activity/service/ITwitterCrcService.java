package com.twitter.activity.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface ITwitterCrcService {
    String validateCrc(String crcToken) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException;
    void receiveEvents(String json);
}
