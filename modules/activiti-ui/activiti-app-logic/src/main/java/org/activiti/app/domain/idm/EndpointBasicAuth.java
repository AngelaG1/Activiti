package org.activiti.app.domain.idm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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



import org.activiti.engine.identity.IdBlockSize;
import org.activiti.engine.identity.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ENDPOINT_BASIC_AUTH")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EndpointBasicAuth
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  //@GeneratedValue(strategy=GenerationType.TABLE, generator="endpointBasicAuthGenerator")
  //@GenericGenerator(name="endpointBasicAuthGenerator", strategy="com.activiti.database.IdGenerator")
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "endpointBasicAuthGenerator")
  @TableGenerator(name = "endpointBasicAuthGenerator", allocationSize = IdBlockSize.DEFAULT_ALLOCATION_SIZE)
  @Column(name="id")
  protected Long id;
  @Column(name="tenant_id", nullable=false)
  protected Long tenantId;
  @Column(name="name", nullable=false)
  protected String name;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="created")
  protected Date created;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="last_updated")
  protected Date lastUpdated;
  //@ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="created_by")
  @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  protected User createdBy;
  //@ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="last_updated_by")
  @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  protected User lastUpdatedBy;
  @Column(name="username")
  protected String username;
  @Column(name="auth_password")
  protected String password;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="deleted")
  protected Date deleted;
  //@ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="deleted_by")
  @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  protected User deletedBy;
  
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
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
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
  
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }
    EndpointBasicAuth endpointBasicAuth = (EndpointBasicAuth)o;
    if (!this.id.equals(endpointBasicAuth.getId())) {
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
