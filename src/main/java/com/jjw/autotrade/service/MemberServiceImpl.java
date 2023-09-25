package com.jjw.autotrade.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jjw.autotrade.domain.Member;
import com.jjw.autotrade.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{


    private final MemberRepository repository;


    @Override
    public Member join(Member member) {
        repository.save(member);
        return member;
    }

    @Override
    public Member findMember(Long memberId) {
        return  repository.findById(memberId);
    }


    @Override
    public String getMemberInfo(String accessKey, String secretKey, String queryString) {
        try {

            String authenticationToken = getAuthenticationToken(accessKey,secretKey,queryString);
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("https://api.upbit.com" + "/v1/accounts");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();


            return EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getAuthenticationToken(String accessKey, String secretKey, String queryString)  {
        String jwtToken = "";
        String authenticationToken = "Bearer ";
        if(queryString == null){
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            jwtToken = JWT.create()
                    .withClaim("access_key", accessKey)
                    .withClaim("nonce", UUID.randomUUID().toString())
                    .sign(algorithm);

            authenticationToken += jwtToken;
        }
        else{
            try{
                MessageDigest md = MessageDigest.getInstance("SHA-512");
                md.update(queryString.getBytes("utf8"));

                String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

                Algorithm algorithm = Algorithm.HMAC256(secretKey);
                jwtToken = JWT.create()
                        .withClaim("access_key", accessKey)
                        .withClaim("nonce", UUID.randomUUID().toString())
                        .withClaim("query_hash", queryHash)
                        .withClaim("query_hash_alg", "SHA512")
                        .sign(algorithm);

                authenticationToken += jwtToken;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


        }

        return authenticationToken;
    }


}
