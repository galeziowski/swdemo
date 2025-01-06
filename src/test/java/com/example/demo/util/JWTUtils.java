package com.example.demo.util;

import com.example.demo.cucumber.CucumberSpringConfiguration;
import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.util.Date;

public class JWTUtils {

    private static final String KEY_ID = "test-key";
    private static final RSAKey rsaKey;

    static {
        try {
            rsaKey = new RSAKeyGenerator(2048)
                    .keyUse(KeyUse.SIGNATURE)
                    .algorithm(new Algorithm("RS256"))
                    .keyID(KEY_ID)
                    .generate();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public static RSAKey getPublicRsaKey() {
        return rsaKey.toPublicJWK();
    }

    public static String getSignedJwt(String selectedUser) throws Exception {
        var signer = new RSASSASigner(rsaKey);
        var claimsSet = new JWTClaimsSet.Builder()
                .subject(selectedUser)
                .expirationTime(new Date(new Date().getTime() + 60 * 1000))
                .build();
        var signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256)
                .keyID(rsaKey.getKeyID()).build(), claimsSet);
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }
}
