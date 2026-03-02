package dev.codebaker.omni_rental.controllers.v1;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/general")
public class GeneralController {

    @GetMapping("/version")

    public ResponseEntity<String> get() {

        return ResponseEntity.ok("1.0.0-SNAPSHOT");
    }
}
