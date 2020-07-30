package com.github.viktornar.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexRestController {
    @Value("${app.name}")
    private String name;
    @Value("${app.version}")
    private String version;

    @GetMapping("/")
    VersionResponse getVersion() {
        return new VersionResponse(name, version);
    }

    @Data
    static class VersionResponse {
        private final String name;
        private final String version;
    }
}
