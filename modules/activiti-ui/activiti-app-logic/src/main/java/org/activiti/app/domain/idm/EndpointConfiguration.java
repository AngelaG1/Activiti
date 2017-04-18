package org.activiti.app.domain.idm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.activiti.app.domain.idm.EndpointProtocol;
import org.activiti.engine.identity.IdBlockSize;
import org.activiti.engine.identity.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ENDPOINT_CONFIGURATION")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EndpointConfiguration
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  //@GeneratedValue(strategy=GenerationType.TABLE, generator="endpointConfigurationGenerator")
  //@GenericGenerator(name="endpointConfigurationGenerator", strategy="com.activiti.database.IdGenerator")
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "userIdGenerator")
  @TableGenerator(name = "userIdGenerator", allocationSize = IdBlockSize.DEFAULT_ALLOCATION_SIZE)
  @Column(name="id")
  private Long id;
  @Column(name="tenant_id", nullable=false)
  private Long tenantId;
  @Column(name="name", nullable=false)
  private String name;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="created")
  private Date created;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="last_updated")
  private Date lastUpdated;
  //@ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="created_by")
  @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private User createdBy;
  //@ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="last_updated_by")
  @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private User lastUpdatedBy;
  @Column(name="protocol")
  @Enumerated(EnumType.ORDINAL)
  private EndpointProtocol protocol = EndpointProtocol.HTTPS;
  @Column(name="host")
  private String host;
  @Column(name="port")
  private String port;
  @Column(name="pathuri")
  private String path;
  @Column(name="request_headers")
  private String requestHeaders;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="deleted")
  private Date deleted;
  //@ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="deleted_by")
  @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private User deletedBy;
  //@ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="basic_auth")
  private EndpointBasicAuth basicAuth;
  
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
  
  public Date getCreated()
  {
    return this.created;
  }
  
  public void setCreated(Date created)
  {
    this.created = created;
  }
  
  public Date getLastUpdated()
  {
    return this.lastUpdated;
  }
  
  public void setLastUpdated(Date lastUpdated)
  {
    this.lastUpdated = lastUpdated;
  }
  
  public User getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(User createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public User getLastUpdatedBy()
  {
    return this.lastUpdatedBy;
  }
  
  public void setLastUpdatedBy(User lastUpdatedBy)
  {
    this.lastUpdatedBy = lastUpdatedBy;
  }
  
  public EndpointProtocol getProtocol()
  {
    return this.protocol;
  }
  
  public void setProtocol(EndpointProtocol protocol)
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
  
  public String getRequestHeaders()
  {
    return this.requestHeaders;
  }
  
  public void setRequestHeaders(String requestHeaders)
  {
    this.requestHeaders = requestHeaders;
  }
  
  public Date getDeleted()
  {
    return this.deleted;
  }
  
  public void setDeleted(Date deleted)
  {
    this.deleted = deleted;
  }
  
  public User getDeletedBy()
  {
    return this.deletedBy;
  }
  
  public void setDeletedBy(User deletedBy)
  {
    this.deletedBy = deletedBy;
  }
  
  public EndpointBasicAuth getBasicAuth()
  {
    return this.basicAuth;
  }
  
  public void setBasicAuth(EndpointBasicAuth basicAuth)
  {
    this.basicAuth = basicAuth;
  }
  
  public String getUrl()
  {
    StringBuilder sb = new StringBuilder(this.protocol.toString().toLowerCase() + "://" + this.host);
    if (!StringUtils.isEmpty(this.port)) {
      sb.append(":" + this.port);
    }
    if (!StringUtils.isEmpty(this.path)) {
      sb.append("/" + this.path);
    }
    return sb.toString();
  }
  
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }
    EndpointConfiguration endpointConfiguration = (EndpointConfiguration)o;
    if (!this.id.equals(endpointConfiguration.getId())) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    if (this.id == null) {
      return super.hashCode();
    }
    return this.id.hashCode();
  }
}
