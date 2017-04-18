package org.activiti.app.domain.editor;


import org.activiti.engine.identity.IdBlockSize;
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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="ACT_DE_MODEL_SHARE_INFO")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ModelShareInfo
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  //@GeneratedValue(strategy=GenerationType.TABLE, generator="modelShareInfoIdGenerator")
  //@GenericGenerator(name="modelShareInfoIdGenerator", strategy="com.activiti.database.IdGenerator")
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "modelShareInfoIdGenerator")
  @TableGenerator(name = "modelShareInfoIdGenerator", allocationSize = IdBlockSize.DEFAULT_ALLOCATION_SIZE)
  @Column(name="id")
  private Long id;
  //@ManyToOne(optional=true)
  @JoinColumn(name="user_id")
  @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private String user;
  //@ManyToOne(optional=true)
  @JoinColumn(name="group_id")
  @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private String group;
  @Column(name="email")
  @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private String email;
  //@ManyToOne
  @JoinColumn(name="shared_by")
  @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private String sharedBy;
  //@JoinColumn(name="shared_by")
  //@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  //private String  sharedById;
  //@ManyToOne
  @Column(name = "model_id")
  private Long modelId;

  // Only needed for HQL queries. Not using it!
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "model_id", insertable = false, updatable = false)
  private Model model;
  
  @Column(name="share_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date shareDate;
  @Column(name="permission")
  @Enumerated(EnumType.ORDINAL)
  private SharePermission permission = SharePermission.READ;

  public Long getId()
  {
    return this.id;
  }
  
  public void setId(Long id)
  {
    this.id = id;
  }
  
  public String getUser()
  {
    return this.user;
  }
  
  public void setUser(String user)
  {
    this.user = user;
  }
  
  public String getGroup()
  {
    return this.group;
  }
  
  public void setGroup(String group)
  {
    this.group = group;
  }
  
  public Model getModel()
  {
    return this.model;
  }
  
  public void setModel(Model model)
  {
    this.model = model;
  }
  
  public Date getShareDate()
  {
    return this.shareDate;
  }
  
  public void setShareDate(Date shareDate)
  {
    this.shareDate = shareDate;
  }
  
  public SharePermission getPermission()
  {
    return this.permission;
  }
  
  public void setPermission(SharePermission permission)
  {
    this.permission = permission;
  }
  
  public String getSharedBy()
  {
    return this.sharedBy;
  }
  
  public void setSharedBy(String sharedBy)
  {
    this.sharedBy = sharedBy;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }
    ModelShareInfo shareInfo = (ModelShareInfo)o;
    return new EqualsBuilder().append(this.id.toString(), shareInfo.user).isEquals();
  }
  
  public int hashCode()
  {
    if (this.id != null) {
      return this.id.hashCode();
    }
    return super.hashCode();
  }
}
