package com.example.yeshdmitsapi.drools.controller;

import com.example.yeshdmitsapi.drools.model.User;
import com.example.yeshdmitsapi.drools.service.RulesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RulesController {

  private final RulesService rulesService;

  @GetMapping("/rules")
  public ResponseEntity<List<String>> test() {
    User user = new User();
    user.setLogin("whitelisted");
    user.setRole("ADMIN");
    List<String> eligibleFeatures = rulesService.getEligibleFeatures(user);
    return ResponseEntity.ok(eligibleFeatures);
  }
}
