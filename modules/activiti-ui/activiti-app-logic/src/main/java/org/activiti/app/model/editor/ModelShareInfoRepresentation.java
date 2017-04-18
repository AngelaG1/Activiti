package org.activiti.app.model.editor;

import org.activiti.app.domain.editor.ModelShareInfo;
import org.activiti.app.domain.editor.SharePermission;
import org.activiti.app.model.common.AbstractRepresentation;


import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class ModelShareInfoRepresentation
  extends AbstractRepresentation
{
  protected Long id;
  protected String permission;
  protected Date shareDate;
  protected String sharedBy;
  protected UserEntity person;
  protected GroupEntity group;
  protected Long processId;
  protected String processName;
  
  public ModelShareInfoRepresentation(ModelShareInfo shareInfo, boolean includeProcessModel)
  {
    this.id = shareInfo.getId();
    this.shareDate = shareInfo.getShareDate();
    this.permission = shareInfo.getPermission().toString().toLowerCase();
    if (shareInfo.getSharedBy() != null) {
         this.sharedBy = shareInfo.getSharedBy();
    }
    if (shareInfo.getUser() != null)
    {


      this.person.setId(shareInfo.getUser());

    }
    if (includeProcessModel)
    {
      this.processId = shareInfo.getModel().getId();
      this.processName = shareInfo.getModel().getName();
    }
  }
  
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public Long getProcessId()
  {
    return this.processId;
  }
  
  public void setProcessId(Long processId)
  {
    this.processId = processId;
  }
  
  public String getPermission()
  {
    return this.permission;
  }
  
  public void setPermission(String permission)
  {
    this.permission = permission;
  }
  
  public Date getShareDate()
  {
    return this.shareDate;
  }
  
  public void setShareDate(Date shareDate)
  {
    this.shareDate = shareDate;
  }
  
  public void setProcessName(String processName)
  {
    this.processName = processName;
  }
  
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public String getProcessName()
  {
    return this.processName;
  }
  
  public UserEntity getPerson()
  {
    return this.person;
  }
  
  public void setPerson(UserEntity person)
  {
    this.person = person;
  }
  
  public GroupEntity getGroup()
  {
    return this.group;
  }
  
  public void setGroup(GroupEntity group)
  {
    this.group = group;
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public void setId(Long id)
  {
    this.id = id;
  }
  
  public String getSharedBy()
  {
    return this.sharedBy;
  }
  
  public void setSharedBy(String sharedBy)
  {
    this.sharedBy = sharedBy;
  }
  
  @JsonIgnore
  public SharePermission getSharePermission()
  {
    if (this.permission != null) {
      for (SharePermission p : SharePermission.values()) {
        if (p.name().toLowerCase().equals(this.permission)) {
          return p;
        }
      }
    }
    return null;
  }
}
