package org.activiti.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class SystemPropertiesService
{
  @Autowired
  protected Environment env;
  
  public boolean allowInvolveByEmail()
  {
    String allowInvolveByEmailProperty = this.env.getProperty("security.involvement.email");
    if ("false".equalsIgnoreCase(allowInvolveByEmailProperty)) {
      return false;
    }
    return true;
  }
  
  public boolean disableJavaScriptEventsInFormEditor()
  {
    String javascriptDisableProperty = this.env.getProperty("editor.form.javascript.disable");
    if ("true".equalsIgnoreCase(javascriptDisableProperty)) {
      return true;
    }
    return false;
  }
}
