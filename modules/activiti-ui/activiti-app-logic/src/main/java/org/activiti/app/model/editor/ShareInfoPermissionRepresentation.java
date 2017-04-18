package org.activiti.app.model.editor;

import org.activiti.app.domain.editor.SharePermission;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ShareInfoPermissionRepresentation
{
  protected Long id;
  protected String userId;
  protected String groupId;
  protected String email;
  protected String permission;
  
  public void setId(Long id)
  {
    this.id = id;
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public String getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
  
  public String getGroupId()
  {
    return this.groupId;
  }
  
  public void setGroupId(String groupId)
  {
    this.groupId = groupId;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getPermission()
  {
    return this.permission;
  }
  
  public void setPermission(String permission)
  {
    this.permission = permission;
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
