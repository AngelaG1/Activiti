package org.activiti.app.model.idm;

import org.activiti.app.domain.idm.EndpointBasicAuth;
import org.activiti.app.domain.idm.EndpointConfiguration;
import org.activiti.app.domain.idm.EndpointProtocol;
import java.util.List;

public class EndpointConfigurationRepresentation
{
  private Long id;
  private Long tenantId;
  private String name;
  private String protocol;
  private String host;
  private String port;
  private String path;
  private Long basicAuthId;
  private String basicAuthName;
  private List<EndpointRequestHeaderRepresentation> requestHeaders;
  
  public EndpointConfigurationRepresentation() {}
  
  public EndpointConfigurationRepresentation(EndpointConfiguration endpointConfiguration)
  {
    this(endpointConfiguration, null);
  }
  
  public EndpointConfigurationRepresentation(EndpointConfiguration endpointConfiguration, List<EndpointRequestHeaderRepresentation> requestHeaders)
  {
    this.id = endpointConfiguration.getId();
    this.tenantId = endpointConfiguration.getTenantId();
    this.name = endpointConfiguration.getName();
    this.protocol = endpointConfiguration.getProtocol().toString();
    this.host = endpointConfiguration.getHost();
    this.port = endpointConfiguration.getPort();
    this.path = endpointConfiguration.getPath();
    if (endpointConfiguration.getBasicAuth() != null)
    {
      this.basicAuthId = endpointConfiguration.getBasicAuth().getId();
      this.basicAuthName = endpointConfiguration.getBasicAuth().getName();
    }
    this.requestHeaders = requestHeaders;
  }
  
  public EndpointConfigurationRepresentation(Long tenantId, String name, String protocol, String host, String port, String path, Long basicAuth, String basicAuthName)
  {
    this.tenantId = tenantId;
    this.name = name;
    this.protocol = protocol;
    this.host = host;
    this.path = path;
    this.basicAuthId = basicAuth;
    this.basicAuthName = basicAuthName;
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public void setId(Long id)
  {
    this.id = id;
  }
  
  public Long getTenantId()
  {
    return this.tenantId;
  }
  
  public void setTenantId(Long tenantId)
  {
    this.tenantId = tenantId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getProtocol()
  {
    return this.protocol;
  }
  
  public void setProtocol(String protocol)
  {
    this.protocol = protocol;
  }
  
  public String getHost()
  {
    return this.host;
  }
  
  public void setHost(String host)
  {
    this.host = host;
  }
  
  public String getPort()
  {
    return this.port;
  }
  
  public void setPort(String port)
  {
    this.port = port;
  }
  
  public String getPath()
  {
    return this.path;
  }
  
  public void setPath(String path)
  {
    this.path = path;
  }
  
  public Long getBasicAuthId()
  {
    return this.basicAuthId;
  }
  
  public void setBasicAuthId(Long basicAuthId)
  {
    this.basicAuthId = basicAuthId;
  }
  
  public String getBasicAuthName()
  {
    return this.basicAuthName;
  }
  
  public void setBasicAuthName(String basicAuthName)
  {
    this.basicAuthName = basicAuthName;
  }
  
  public List<EndpointRequestHeaderRepresentation> getRequestHeaders()
  {
    return this.requestHeaders;
  }
  
  public void setRequestHeaders(List<EndpointRequestHeaderRepresentation> requestHeaders)
  {
    this.requestHeaders = requestHeaders;
  }
}
