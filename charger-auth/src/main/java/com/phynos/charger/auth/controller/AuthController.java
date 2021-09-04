package com.phynos.charger.auth.controller;

import com.phynos.charger.auth.dto.AuthDTO;
import com.phynos.charger.auth.vo.JwtResponse;
import com.phynos.charger.auth.security.JwtUserDetailsService;
import com.phynos.charger.common.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author by lupc
 * @date 2020-08-27 15:10
 */
@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private JwtUserDetailsService jwtInMemoryUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping({"/auth/test"})
    public String welcomePage() {
        return "Welcome!";
    }

    @PostMapping(value = "/auth/token")
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody AuthDTO dto)
            throws Exception {

        authenticate(dto.getUsername(), dto.getPassword());

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(dto.getUsername());

        final String token = jwtService.createToken(userDetails.getUsername());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
