package com.simple.Simple.util;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ByteArrayMapper {

    public String byteArrayToBase64(byte[] bytes) {
        return bytes != null ? Base64.getEncoder().encodeToString(bytes) : null;
    }

    public byte[] base64ToByteArray(String base64) {
        return base64 != null ? Base64.getDecoder().decode(base64) : null;
    }
}
