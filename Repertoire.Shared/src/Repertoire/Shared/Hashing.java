/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
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
}
