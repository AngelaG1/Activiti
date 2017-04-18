package org.activiti.app.domain.idm;

public enum EndpointProtocol
{
  HTTP("http"),  HTTPS("https");
  
  private String protocol;
  
  private EndpointProtocol(String protocol)
  {
    this.protocol = protocol;
  }
  
  public String getProtocol()
  {
    return this.protocol;
  }
}
