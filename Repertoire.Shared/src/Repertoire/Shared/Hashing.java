/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author rndmorris
 */
public class Hashing {
    private static MessageDigest getDigest() {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace(System.err);
        }
        return digest;
    }
    public static String Sha256ToBase64(String input) {
        return Base64.getEncoder().encodeToString(getDigest().digest(input.getBytes(StandardCharsets.UTF_8)));
    }
    public static String Sha256ToBase64(Long input) {
        return Hashing.Sha256ToBase64(input.toString());
    }
    public static String Sha256ToBase16(String input) {
        byte[] bytes = getDigest().digest(input.getBytes(StandardCharsets.UTF_8));
        return ToBase16(bytes);
    }
    public static String Sha256ToBase16(Long input) {
        return Sha256ToBase16(input.toString());
    }
    
    private static String ToBase16(byte[] input) {
        StringBuilder output = new StringBuilder();
        for (byte b : input) {
            output.append(String.format("%02X", b));
        }
        return output.toString();
    }
}
