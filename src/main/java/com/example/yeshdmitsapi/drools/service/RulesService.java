package com.example.yeshdmitsapi.drools.service;

import com.example.yeshdmitsapi.drools.model.Result;
import com.example.yeshdmitsapi.drools.model.User;
import com.example.yeshdmitsapi.drools.rules.FeatureProvider;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RulesService {

  private final FeatureProvider featureProvider;

  public List<String> getEligibleFeatures(User user) {

    KieSession instance = featureProvider.getInstance();
    instance.insert(user);

    HashSet<Result> results = new HashSet<>();
    instance.insert(results);
    instance.setGlobal("results", results);


    instance.fireAllRules();
    instance.dispose();

    return results.stream().map(Result::getFeature).toList();
  }
}
