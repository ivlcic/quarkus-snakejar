package com.dropchop.quarkus.snakejar.example;

import com.dropchop.snakejar.Invocation;
import com.dropchop.snakejar.InvokeClass;
import com.dropchop.snakejar.Invoker;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nikola Ivačič <nikola.ivacic@dropchop.org> on 15. 11. 21.
 */
@ApplicationScoped
public class LanguageDetectionService {

  public static class InvokeLangIdClass extends InvokeClass<HashMap<String, Double>> {
    @SuppressWarnings("unchecked")
    public InvokeLangIdClass() {
      super("lang_detect", "lang_id", "LanguageDetect", (Class<HashMap<String, Double>>)(Class<?>)HashMap.class);
    }
  }

  public static final Invocation<HashMap<String, Double>> LANG_ID_CLASS = new InvokeLangIdClass();

  @Inject
  Invoker invoker;

  public Map<String, Double> detectLanguage(String text, int numRet) throws Exception {
    Map<String, Double> result = invoker.apply(LANG_ID_CLASS,
      () -> new Object[]{
        text,
        numRet
      }).get();
    return result;
  }
}
