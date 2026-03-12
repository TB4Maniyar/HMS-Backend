////package com.maniyar.hms.controller;
////
////import com.maniyar.hms.entity.User;
////import com.maniyar.hms.repository.UserRepository;
////import com.maniyar.hms.util.JwtUtil;
////import lombok.*;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.web.bind.annotation.*;
////
////@RestController
////@RequestMapping("/api/auth")
////@RequiredArgsConstructor
////public class AuthController {
////
////    private final UserRepository userRepository;
////    private final PasswordEncoder passwordEncoder;
////    private final JwtUtil jwtUtil;
////
////    @PostMapping("/register")
////    public String register(@RequestBody User user) {
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
////        userRepository.save(user);
////        return "User Registered";
////    }
////
////    @PostMapping("/login")
////    public String login(@RequestBody User user) {
////
////        var dbUser = userRepository
////                .findByUsername(user.getUsername())
////                .orElseThrow();
////
////        if (passwordEncoder.matches(
////                user.getPassword(),
////                dbUser.getPassword())) {
////
////            return jwtUtil.generateToken(dbUser.getUsername());
////        }
////
////        throw new RuntimeException("Invalid credentials");
////    }
////}
////
//////package com.manihar.hms.controller;
//////
//////import com.manihar.hms.service.AuthService;
//////import lombok.RequiredArgsConstructor;
//////import org.springframework.web.bind.annotation.*;
//////
//////        import java.util.Map;
//////
//////@RestController
//////@RequestMapping("/api/auth")
//////@RequiredArgsConstructor
//////public class AuthController {
//////
//////    private final AuthService authService;
//////
//////    @PostMapping("/login")
//////    public String login(@RequestBody Map<String, String> body) {
//////
//////        String username = body.get("username");
//////        String password = body.get("password");
//////
//////        return authService.login(username, password);
//////    }
//////}
//
//
//package com.maniyar.hms.controller;
//
//import com.maniyar.hms.service.AuthService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final AuthService authService;
//
//    @PostMapping("/login")
//    public String login(@RequestBody Map<String, String> body) {
//
//        String username = body.get("username");
//        String password = body.get("password");
//
//        return authService.login(username, password);
//    }
//}

package com.maniyar.hms.controller;

import com.maniyar.hms.entity.User;
import com.maniyar.hms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return authService.login(user.getUsername(), user.getPassword());
    }
}