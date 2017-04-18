package org.activiti.app.repository.editor;

import org.activiti.app.domain.editor.ModelShareInfo;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public abstract interface ModelShareInfoRepository
  extends JpaRepository<ModelShareInfo, Long>
{
	  public abstract List<ModelShareInfo> findByUserIdOrderByShareDateAsc(Long paramLong, Pageable paramPageable);
	  
	  public abstract List<ModelShareInfo> findByModelIdOrderByShareDateAsc(Long paramLong, Pageable paramPageable);
	  
	  //@Query("select info from ModelShareInfo info left outer join info.group as group where info.model.id =:modelId and (info.user =:userId or group = :groupId)")
	  //public abstract List<ModelShareInfo> findByModelIdWithUserIdOrGroups(@Param("modelId") Long paramLong1, @Param("userId") String paramLong2, @Param("groupId") String paramList);
	  
	 @Query("select info from ModelShareInfo info where info.modelId =:modelId and info.user =:user")
	  public abstract List<ModelShareInfo> findByModelIdWithUserId(Long modelId, String user);
	  
	  @Query("select info from ModelShareInfo info where info.modelId =:modelId and info.user =:user")
	  public abstract ModelShareInfo findByModelIdAndUserId(Long modelId, String user);

	  @Query("select info from ModelShareInfo info where info.modelId =:modelId and info.group =:group")
	  public abstract ModelShareInfo findByModelIdAndGroupId(Long modelId, String group);
	  
	  public abstract ModelShareInfo findByModelIdAndId(Long paramLong1, Long paramLong2);
	  
	  @Query("select info from ModelShareInfo info where info.modelId =:modelId and info.sharedBy=:sharedBy and info.user =:user")
	  public abstract ModelShareInfo findByModelIdAndSharedByAndUser(Long modelId,String sharedBy, String user);
	  
	 // @Query("select info from ModelShareInfo info where info.model.id =:modelId and info.model.sharedBy=:sharedBy and info.group.id =:groupId")
	 // public abstract ModelShareInfo findByModelIdAndSharedByAndGroup(@Param("modelId") Long paramLong,@Param("sharedBy") String paramUser, @Param("groupId") String paramGroup);
	  

	  @Query("delete from ModelShareInfo info where info.modelId = :modelId")
	  public abstract void deleteInBatchByModelId(@Param("modelId") Long modelId);
	  
	 // @Modifying
	 // @Query("update ModelShareInfo info set info.email = null, info.user = :user where info.email = :email")
	 // public abstract void connectSharedModelsByEmail(@Param("email") String paramString, @Param("user") User paramUser);
	  
	  //public abstract Long deleteBySharedById(String paramLong);
	  
	 // public abstract String deleteByUserId(String paramLong);
}
