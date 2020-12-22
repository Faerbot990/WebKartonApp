package com.example.WebKartonApp.controller;


import com.example.WebKartonApp.dto.AuthenticationRequestDTO;
import com.example.WebKartonApp.model.Product;
import com.example.WebKartonApp.model.User;
import com.example.WebKartonApp.security.JwtProvider;
import com.example.WebKartonApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthenticationController {
//    @GetMapping("/login")
//    public String getLoginPage() {
//        log.info("Logining");
//        return "login";
//    }
//
//    @GetMapping("/success")
//    public String getSuccessPage() {
//        return "admin_panel";
//    }


    private final AuthenticationManager authenticationManager;


    private final UserService userService;


    private final JwtProvider jwtProvider;


    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }


    @PostMapping ("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            log.info("got login request: {}", request);
//authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = userService.findByUsername(request.getUsername());
            String userRole = user.getRoles().iterator().next().name();
            String token = jwtProvider.createToken(request.getUsername(), userRole);
            List<Product> productList = user.getProductList();

            Map<Object, Object> response = new HashMap<>();
            response.put("username", request.getUsername());
            response.put("token", token);
            response.put("userRole", userRole);
            response.put("productList", productList);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Error username and password", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
