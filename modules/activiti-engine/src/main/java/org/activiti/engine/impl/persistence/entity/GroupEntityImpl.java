/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.engine.impl.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.identity.Group;

import org.activiti.engine.identity.User;

/**
 * @author Tom Baeyens
 */
public class GroupEntityImpl extends AbstractEntity implements GroupEntity, Serializable {

	private static final long serialVersionUID = 1L;

	protected String name;
	protected String type;

	private Set<User> users;

	private Long tenantId;



	private Group parent;

	private String parentGroupId;

	private String managerGroupId;

	private Group managerGroup;


	public GroupEntityImpl() {
	}

	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("name", name);
		persistentState.put("type", type);
		persistentState.put("users", users);
		persistentState.put("tenantId", tenantId);

		persistentState.put("parent", parent);
		persistentState.put("parentGroupId", parentGroupId);
		persistentState.put("managerGroupId", managerGroupId);
		persistentState.put("managerGroup", managerGroup);


		return persistentState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Set<User> getUsers()
	{
		return this.users;
	}
	

	public void setUsers(Set<User> users) {
		
		this.users = users;
	}

	public Long getTenantId()
	{
		return this.tenantId;
	}

	public void setTenantId(Long tenantId)
	{
		this.tenantId = tenantId;
	}

	public Group getParent()
	{
		return this.parent;
	}

	public void setParent(Group parent)
	{
		this.parent = parent;
	}

	public String getParentGroupId()
	{
		return this.parentGroupId;
	}

	public void setParentGroupId(String parentGroupId)
	{
		this.parentGroupId = parentGroupId;
	}

	public String getManagerGroupId()
	{
		return this.managerGroupId;
	}

	public void setManagerGroupId(String managerGroupId)
	{
		this.managerGroupId = managerGroupId;
	}

	public Group getManagerGroup()
	{
		return this.managerGroup;
	}

	public void setManagerGroup(Group managerGroup)
	{
		this.managerGroup = managerGroup;
	}




}
