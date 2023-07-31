package com.example.demo;

import com.example.demo.dto.UserResp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequiredArgsConstructor
public class API {
    private final EnvVariables envs;

    @GetMapping("/")
    public ResponseEntity<String> aa() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/env1")
    public ResponseEntity<String> aa1() {
        return ResponseEntity.ok(envs.getProperty1());
    }

    @GetMapping("/env2")
    public ResponseEntity<String> aa2() {
        return ResponseEntity.ok(envs.getProperty2());
    }

    @GetMapping("/env3")
    public ResponseEntity<String> aa3() {
        return ResponseEntity.ok(envs.getProperty3());
    }

    @GetMapping("/env4")
    public ResponseEntity<String> aa4() {
        return ResponseEntity.ok(envs.getProperty4());
    }

    @GetMapping("/all-envs")
    public ResponseEntity<EnvVariables> envs() {
        return ResponseEntity.ok(this.envs);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResp> aa5(@RequestBody UserResp req) {
        var a = UserResp.builder().email("invalid api call").password("invalid api call").build();
        UserResp block = WebClient.create("http://%s".formatted(envs.getAuthAddress()))
                .post()
                .uri("/token")
                .bodyValue(req)
                .retrieve()
                .bodyToMono(UserResp.class)
                .onErrorComplete()
                .block();
        return ResponseEntity.ok(block == null ? a : block);
    }
}

