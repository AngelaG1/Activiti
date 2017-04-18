package org.activiti.app.service.api;


import org.activiti.app.model.common.ResultListDataRepresentation;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

import java.util.Date;
import java.util.List;

public abstract interface GroupService
{
  public abstract ResultListDataRepresentation getFunctionalGroups(String paramString1, String paramString2, String paramString3, Long paramLong1, Long paramLong2);
  
  public abstract ResultListDataRepresentation getUsersForFunctionalGroup(Long paramLong);
  
  public abstract Group getGroup(Long paramLong);
  
  public abstract Group getGroup(Long paramLong, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4);
  
  public abstract Group getGroup(Long paramLong, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5);
  
  public abstract Group getGroupByExternalId(String paramString);
  
  public abstract Group getGroupByExternalIdAndTenantId(String paramString, Long paramLong);
  
  public abstract List<Group> getGroupByNameAndTenantId(String paramString, Long paramLong);
  
  public abstract List<Group> getFunctionalGroups(String paramString1, String paramString2, String paramString3, Long paramLong1, Long paramLong2, boolean paramBoolean, int paramInt1, int paramInt2);
  
  public abstract List<Group> getFunctionalGroups(Long paramLong);
  
  public abstract Group getFunctionalGroup(Long paramLong);
  
  public abstract List<Group> getFunctionalGroupsByNameAndTenantId(String paramString, Long paramLong);
  
  public abstract Group getFunctionalGroupByExternalIdAndTenantId(String paramString, Long paramLong);
  
  public abstract List<Group> getSystemGroups(Long paramLong);
  
  public abstract List<Group> getSystemGroupsWithCapability(String paramString, Long paramLong);
  
  public abstract List<Group> getSystemGroupWithName(String paramString, Long paramLong);
  
  public abstract List<Group> getRelatedGroups(Long paramLong);
  
  public abstract Group createGroup(String paramString, Long paramLong1, int paramInt, Long paramLong2);
  
  public abstract Group createGroupFromExternalStore(String paramString1, Long paramLong1, int paramInt, Long paramLong2, String paramString2, Date paramDate);
  
  public abstract Group updateGroup(Long paramLong, String paramString);
  
  public abstract Group save(Group paramGroup);
  
  public abstract void deleteGroup(Long paramLong);
  
  public abstract void activateGroup(Long paramLong);
  
  public abstract boolean addUserToGroup(Group paramGroup, User paramUser);
  
  public abstract void deleteUserFromGroup(Group paramGroup, User paramUser);
  
  public abstract void deleteAllUsersFromGroup(Group paramGroup);
  
  public abstract void addRelatedGroup(Group paramGroup1, Group paramGroup2, String paramString);
  
  public abstract void deleteRelatedGroup(Group paramGroup1, Group paramGroup2);
  
  public abstract void addCapabilitiesToGroup(Long paramLong, List<String> paramList);
  
  public abstract boolean deleteCapability(Long paramLong1, Long paramLong2);
  
  public abstract boolean isUserInGroup(Group paramGroup, User paramUser);
  
  public abstract boolean isManagerInOneGroup(List<Long> paramList, User paramUser);
  
  public abstract void setAsOnlyUser(Group paramGroup, User paramUser);
  
  public abstract void addGroupManager(Group paramGroup, User paramUser);
  
  public abstract void removeGroupManager(Group paramGroup, User paramUser);
}
