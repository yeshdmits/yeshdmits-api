package com.example.yeshdmitsapi.drools;

import lombok.SneakyThrows;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;

public abstract class RulesProvider {
  private static final KieServices KIE_SERVICES = KieServices.Factory.get();

  @SneakyThrows
  protected KieContainer initKieContainer(Resource decisionTableSource) {
    KieFileSystem kieFileSystem = KIE_SERVICES.newKieFileSystem();
    kieFileSystem.write(decisionTableSource);
    KieBuilder kb = KIE_SERVICES.newKieBuilder(kieFileSystem);
    kb.buildAll();
    KieModule kieModule = kb.getKieModule();

    return KIE_SERVICES.newKieContainer(kieModule.getReleaseId());
  }
}
