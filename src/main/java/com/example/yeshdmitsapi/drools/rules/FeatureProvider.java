package com.example.yeshdmitsapi.drools.rules;

import com.example.yeshdmitsapi.drools.RulesProvider;
import lombok.RequiredArgsConstructor;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeatureProvider extends RulesProvider {
  @Value("${rules.feature.path}")
  private String defaultDecisionTable;

  public KieSession getInstance() {
    Resource resource = ResourceFactory.newClassPathResource(defaultDecisionTable);
    resource.setResourceType(ResourceType.DTABLE);
    return this.initKieContainer(resource).newKieSession();
  }
}
