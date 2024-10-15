package com.szymonkornik.springai;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GymCoachAIRestController {

    private final GymCoachAIService gymCoachAIService;

    @GetMapping("ask-gym-advice")
    public ResponseEntity<String> returnResponseRelatedToCoaching(@RequestParam String question) {
        return ResponseEntity.ok(gymCoachAIService.returnResponseRelatedToCoaching(question));
    }
}
