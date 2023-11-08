package com.example.teamconnect.utils;

import com.example.teamconnect.dto.EmployeeDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;
/*
you might have a StringUtils class or a DateUtils class that contains utility functions related to string manipulation or date formatting.
 */
@Component
public class JwtUtils {

    private static String secret = "secretkey";

    long millisecond = System.currentTimeMillis();
    long expiryTime = millisecond+(60*60)*1000; // 60*60 represents 1 hour and 1000 is for cobersion of millisecond to seconds
    Date issueAt = new Date(millisecond);
    Date expiryAt = new Date(expiryTime);
    public String generateJWT(EmployeeDto employeeDto){
        Claims claims = Jwts.claims().setIssuer(employeeDto.getEmail())
                .setIssuedAt(issueAt).setExpiration(expiryAt);


        // these are the optional claims .You can add this when u think its needed
        claims.put("phoneNumber",employeeDto.getPhoneNumber());
        claims.put("employeeId",employeeDto.getEmpId());

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256,secret).compact(); // compact is used to covert the JwtsBuilder to string.
      /*   if we give to String instead of compact that gives object instead of token eg :
        {
           "code": "OK",
            "message": "Logged in successfully",
            "data": "io.jsonwebtoken.impl.DefaultJwtBuilder@35c60cd8"
        }*/
    }

    // claims are the builder method becoz we can give . and add many methods. Example : .setIssuerAt(),setIssueId()

    /* eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzdWJyYW1hbmkuZEB2dmRudGVjaC5pbiIsImlhdCI6MTY5MzU2MTUxMSwiZXhwIjoxNjkzNTY1MTExLCJwaG9uZU51bWJlciI6OTM0NDA2NzY1MSwiZW1wbG95ZWVJZCI6MTgxMjAxMX0.9ZNKt3O6tabM1dKncHdsC6fheC4-DqUvjFEUGAGYbmE */

    // -------In this till first full stop its header , till second its payload and till third its signature
    //--------Headers will be same for all time
    //--------payload based on start and end time they always change
    //--------Signature will not be the same sign it has salt algorithm which always gives different values in token


    public String getUsernameFromToken(String token){
        Claims claims= Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getIssuer();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;    }
        catch (Exception e) {        // Invalid or expired token
              return false;    }}

    public boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        Date expirationDate = claims.getExpiration();
        return expirationDate.before(new Date());
    }




}
