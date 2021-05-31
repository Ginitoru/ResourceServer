package com.iordache.security;



import com.nimbusds.jose.shaded.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.stream.Collectors;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {

    http.oauth2ResourceServer(                                          //DSL customizer
                c -> c.jwt(
                            token -> token.decoder(decoder())
                                          .jwtAuthenticationConverter(converter())
                )
    );

    http.authorizeRequests().anyRequest().authenticated();
    }


//    @Bean
//    public JwtDecoder decoder(){
//       return NimbusJwtDecoder
//                        .withJwkSetUri("http://localhost:8080/oauth/token_key")
//
//                        .jwsAlgorithm(SignatureAlgorithm.RS256)
//
//                        .build();
//    }



    @Bean
    public JwtDecoder decoder(){
        String key = "sdfasdfasfasdfadsfasdfasdfadsfasdfasdfasdfasdfasdf"; //cheia trebuie sa aiba o anumita lungime -> incazul de fata am folosit simetric key
        SecretKey secretKey = new SecretKeySpec(key.getBytes(),0 ,key.getBytes().length, "AES");
        return NimbusJwtDecoder.withSecretKey(secretKey)  //daca vresa sa folosim asimetric key o sa folosim metoda withPublicKey()
                .build();
    }


    @Bean
    public JwtAuthenticationConverter converter(){                      //in converter configuram JWT
        var converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt ->{

                                    JSONArray auth = (JSONArray) jwt.getClaims().get("authorities");
                                    return auth.stream()
                                                .map(String::valueOf)
                                                .map(a -> new SimpleGrantedAuthority(a))
                                                .collect(Collectors.toSet());
        });

        return converter;
    }
}
