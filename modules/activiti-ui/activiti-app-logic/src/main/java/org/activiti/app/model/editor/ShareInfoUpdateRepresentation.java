package org.activiti.app.model.editor;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

public class ShareInfoUpdateRepresentation
{
  protected List<Long> removed;
  protected List<ShareInfoPermissionRepresentation> added;
  protected List<ShareInfoPermissionRepresentation> updated;
  
  @JsonDeserialize(contentAs=Long.class)
  public List<Long> getRemoved()
  {
    return this.removed;
  }
  
  public void setRemoved(List<Long> removed)
  {
    this.removed = removed;
  }
  
  @JsonDeserialize(contentAs=ShareInfoPermissionRepresentation.class)
  public List<ShareInfoPermissionRepresentation> getAdded()
  {
    return this.added;
  }
  
  public void setAdded(List<ShareInfoPermissionRepresentation> added)
  {
    this.added = added;
  }
  
  @JsonDeserialize(contentAs=ShareInfoPermissionRepresentation.class)
  public List<ShareInfoPermissionRepresentation> getUpdated()
  {
    return this.updated;
  }
  
  public void setUpdated(List<ShareInfoPermissionRepresentation> updated)
  {
    this.updated = updated;
  }
}
