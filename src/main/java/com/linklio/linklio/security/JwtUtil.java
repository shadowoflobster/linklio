package com.linklio.linklio.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.text.ParseException;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret-key}")
    private String secretKey;

    private final long expirationMillis = 3600_00;

    public String generateToken(String email){
        try {
            JWSSigner signer = new MACSigner(secretKey);

            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + expirationMillis);

            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(email)
                    .issueTime(now)
                    .expirationTime(expiryDate)
                    .build();

            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claims
            );

            signedJWT.sign(signer);
            return signedJWT.serialize();
        }catch (JOSEException e){
            throw new RuntimeException("Error creating JWT", e);
        }

    }

    public String extractEmail(String token){
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            return jwt.getJWTClaimsSet().getSubject();
        }catch (ParseException e){
            throw new RuntimeException("Invalid Token", e);
        }
    }

    public Boolean validateToken(String token){
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(secretKey);
            return jwt.verify(verifier) && !isExpired(jwt);
        }catch (Exception e){
            return false;
        }
    }

    private boolean isExpired(SignedJWT jwt) throws ParseException {
        Date expirationTime = jwt.getJWTClaimsSet().getExpirationTime();
        return expirationTime.before(new Date());
    }
}
