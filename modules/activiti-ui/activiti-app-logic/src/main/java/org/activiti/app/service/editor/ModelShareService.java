package org.activiti.app.service.editor;

import org.activiti.app.domain.editor.Model;
import org.activiti.app.domain.editor.ModelShareInfo;
import org.activiti.app.domain.editor.SharePermission;
import org.activiti.editor.language.json.converter.util.JsonConverterUtil;
//import org.activiti.app.model.editor.kickstart.ChoiceContainer;
//import org.activiti.app.model.editor.kickstart.ChoiceStepDefinition;
//import org.activiti.app.model.editor.kickstart.DecisionStepDefinition;
//import org.activiti.app.model.editor.kickstart.HumanStepDefinition;
//import org.activiti.app.model.editor.kickstart.KickstartModelDefinition;
//import org.activiti.app.model.editor.kickstart.StepDefinition;
//import org.activiti.app.model.editor.kickstart.SubProcessDefinition;
//import org.activiti.app.model.editor.kickstart.SubProcessStepDefinition;
import org.activiti.app.repository.editor.ModelRepository;
import org.activiti.app.repository.editor.ModelShareInfoRepository;
import org.activiti.app.service.exception.InternalServerErrorException;








import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("activitiTransactionManager")
public class ModelShareService
{
  private final Logger logger = LoggerFactory.getLogger(ModelShareService.class);

  @Autowired
  protected ModelRepository modelRepository;
   @Autowired(required=false)
  protected ModelShareInfoRepository shareInfoRepository;
  //@Autowired
  //protected UserService userService;
  @Autowired
  protected ObjectMapper objectMapper;
  //@Autowired
  //protected KickstartModelStorageHandler storageHandler;
  
 /* public ModelShareInfo shareModelWithGroup(Model model, String groupToShareWith, SharePermission permission)
  {
    User currentUser = SecurityUtils.getCurrentUserObject();
    
    ModelShareInfo shareInfo = this.shareInfoRepository.findByModelIdAndGroupId(model.getId(), groupToShareWith);
    if (shareInfo == null)
    {
      shareInfo = new ModelShareInfo();
      shareInfo.setSharedBy(currentUser.getId());
      shareInfo.setShareDate(Calendar.getInstance().getTime());
      shareInfo.setGroup(groupToShareWith);
      shareInfo.setModel(model);
      
      //this.emailService.queueModelSharedEmailForGroupOfUsers(model.getId().toString(), model.getModelType().intValue(), model.getName(), model.getDescription(), Long.valueOf(groupToShareWith.getId()), currentUser);
    }
    if (shareInfo != null)
    {
      shareInfo.setPermission(permission);
      this.shareInfoRepository.save(shareInfo);
    }
    if ((model.getModelType() == null) || (model.getModelType().intValue() == 0) || (model.getModelType().intValue() == 1))
    {
      Map<Long, Model> modelMap = new HashMap<Long, Model>();
      if (model.getModelType().intValue() == 1) {
        parseReferenceModels(model, modelMap);
      } else {
        parseReferenceModels(model, modelMap);
      }
      for (Model referenceModel : modelMap.values())
      {
        ModelShareInfo referenceShareInfo = this.shareInfoRepository.findByModelIdAndGroupId(referenceModel.getId(), groupToShareWith);
        if (referenceShareInfo == null)
        {
          referenceShareInfo = new ModelShareInfo();
          referenceShareInfo.setSharedBy(currentUser.getId());
          referenceShareInfo.setShareDate(Calendar.getInstance().getTime());
          referenceShareInfo.setGroup(groupToShareWith);
          referenceShareInfo.setModel(referenceModel);
          referenceShareInfo.setPermission(permission);
          this.shareInfoRepository.save(referenceShareInfo);
        }
      }
    }
    return shareInfo;
  }*/
  
  public ModelShareInfo shareModelWithUser(Model model, String userToShareWith, SharePermission permission, String sharedBy)
  {
    if (userToShareWith == null) {
      return null;
    }
    ModelShareInfo shareInfo = this.shareInfoRepository.findByModelIdAndUserId(model.getId(), userToShareWith);
    if (shareInfo == null)
    {
      shareInfo = new ModelShareInfo();
      shareInfo.setSharedBy(sharedBy);
      shareInfo.setShareDate(Calendar.getInstance().getTime());
      shareInfo.setUser(userToShareWith);
      shareInfo.setModel(model);
      
      //this.emailService.queueModelSharedEmailForSingleUser(model.getId(), model.getModelType().intValue(), model.getName(), model.getDescription(), userToShareWith, sharedBy);
    }
    if (shareInfo != null)
    {
      shareInfo.setPermission(permission);
      this.shareInfoRepository.save(shareInfo);
    }
    if ((model.getModelType() == null) || (model.getModelType().intValue() == 0) || (model.getModelType().intValue() == 1))
    {
      Map<Long, Model> modelMap = new HashMap<Long, Model>();
      if (model.getModelType().intValue() == 1) {
        parseReferenceModels(model, modelMap);
      } else {
        parseReferenceModels(model, modelMap);
      }
      for (Model referenceModel : modelMap.values()) {
        if (!referenceModel.getId().equals(userToShareWith))
        {
          ModelShareInfo referenceShareInfo = this.shareInfoRepository.findByModelIdAndUserId(referenceModel.getId(), userToShareWith);
          if (referenceShareInfo == null)
          {
            referenceShareInfo = new ModelShareInfo();
            referenceShareInfo.setSharedBy(sharedBy);
            referenceShareInfo.setShareDate(Calendar.getInstance().getTime());
            referenceShareInfo.setUser(userToShareWith);
            referenceShareInfo.setModel(referenceModel);
            referenceShareInfo.setPermission(permission);
            this.shareInfoRepository.save(referenceShareInfo);
          }
        }
      }
    }
    return shareInfo;
  }
  
  public boolean updateShareInfo(Model model, String shareInfoId, SharePermission permission)
  {
    boolean updated = false;
    
    String sharedBy = null;
    String sharedWithUser = null;
    String sharedWithGroup = null;
    
    ModelShareInfo shareInfo = this.shareInfoRepository.findByModelIdAndId(model.getId(), Long.valueOf(shareInfoId));
    if (shareInfo != null)
    {
      sharedBy = shareInfo.getSharedBy();
      sharedWithUser = shareInfo.getUser();
      sharedWithGroup = shareInfo.getGroup();
      shareInfo.setPermission(permission);
      this.shareInfoRepository.save(shareInfo);
      updated = true;
    }
    if ((sharedBy != null) && ((model.getModelType() == null) || (model.getModelType().intValue() == 0) || (model.getModelType().intValue() == 1)))
    {
      Map<Long, Model> modelMap = new HashMap<Long, Model>();
      if (model.getModelType().intValue() == 1) {
        parseReferenceModels(model, modelMap);
      } else {
        parseReferenceModels(model, modelMap);
      }
      for (Model referenceModel : modelMap.values())
      {
        ModelShareInfo referenceShareInfo = null;
        if (sharedWithUser != null) {
          referenceShareInfo = this.shareInfoRepository.findByModelIdAndSharedByAndUser(referenceModel.getId(), sharedBy, sharedWithUser);
        } else if (sharedWithGroup != null) {
        //  referenceShareInfo = this.shareInfoRepository.findByModelIdAndSharedByAndGroup(referenceModel.getId(), sharedBy.getId(), sharedWithGroup.getId());
        }
        if (referenceShareInfo != null)
        {
          referenceShareInfo.setPermission(permission);
          this.shareInfoRepository.save(referenceShareInfo);
        }
      }
    }
    return updated;
  }
  
  public boolean deleteShareInfo(Model model, String shareInfoId)
  {
    boolean deleted = false;
    
    String sharedBy = null;
    String sharedWithUser = null;
    String sharedWithGroup = null;
    
    ModelShareInfo shareInfo = this.shareInfoRepository.findByModelIdAndId(model.getId(), Long.valueOf(shareInfoId));
    if (shareInfo != null)
    {
      sharedBy = shareInfo.getSharedBy();
      sharedWithUser = shareInfo.getUser();
      sharedWithGroup = shareInfo.getGroup();
      this.shareInfoRepository.delete(shareInfo);
      deleted = true;
    }
    if ((sharedBy != null) && ((model.getModelType() == null) || (model.getModelType().intValue() == 0) || (model.getModelType().intValue() == 1)))
    {
      Map<Long, Model> modelMap = new HashMap<Long, Model>();
      if (model.getModelType().intValue() == 1) {
        parseReferenceModels(model, modelMap);
      } else {
        parseReferenceModels(model, modelMap);
      }
      for (Model referenceModel : modelMap.values())
      {
        ModelShareInfo referenceShareInfo = null;
        if (sharedWithUser != null) {
          referenceShareInfo = this.shareInfoRepository.findByModelIdAndSharedByAndUser(referenceModel.getId(), sharedBy, sharedWithUser);
        } else if (sharedWithGroup != null) {
         // referenceShareInfo = this.shareInfoRepository.findByModelIdAndSharedByAndGroup(referenceModel.getId(), sharedBy.getId(), sharedWithGroup.getId());
        }
        if (referenceShareInfo != null) {
          this.shareInfoRepository.delete(referenceShareInfo);
        }
      }
    }
    return deleted;
  }
  
  protected void parseReferenceModels(Model model, Map<Long, Model> modelMap)
  {
    if (StringUtils.isNotEmpty(model.getModelEditorJson()))
    {
      JsonNode editorJsonNode = null;
      try
      {
        editorJsonNode = this.objectMapper.readTree(model.getModelEditorJson());
      }
      catch (Exception e)
      {
        this.logger.error("Error reading process model json " + model.getId(), e);
        throw new InternalServerErrorException("Error reading process model json");
      }
      for (JsonConverterUtil.JsonLookupResult jsonLookupResult : JsonConverterUtil.getBpmnProcessModelFormReferences(editorJsonNode))
      {
        JsonNode formReferenceNode = jsonLookupResult.getJsonNode();
        JsonNode formTableIdNode = formReferenceNode.get("id");
        if ((formTableIdNode != null) && (!formTableIdNode.isNull()))
        {
          Long formId = Long.valueOf(formTableIdNode.asLong());
          Model formModel = (Model)this.modelRepository.findOne(formId);
          if (formModel != null) {
            modelMap.put(formId, formModel);
          }
        }
      }
      for (JsonConverterUtil.JsonLookupResult jsonLookupResult : JsonConverterUtil.getBpmnProcessModelDecisionTableReferences(editorJsonNode))
      {
        JsonNode decisionTableReferenceNode = jsonLookupResult.getJsonNode();
        JsonNode decisionTableIdNode = decisionTableReferenceNode.get("id");
        if ((decisionTableIdNode != null) && (!decisionTableIdNode.isNull()))
        {
          Long decisionTableId = Long.valueOf(decisionTableIdNode.asLong());
          Model decisionTableModel = (Model)this.modelRepository.findOne(decisionTableId);
          if (decisionTableModel != null) {
            modelMap.put(decisionTableId, decisionTableModel);
          }
        }
      }
      for (JsonNode jsonLookupResult : JsonConverterUtil.getAppModelReferencedProcessModels(editorJsonNode))
      {
        JsonNode subProcessReferenceNodes = jsonLookupResult;
        JsonNode subProcessIdNode = subProcessReferenceNodes.get("id");
        if ((subProcessIdNode != null) && (!subProcessIdNode.isNull()))
        {
          Long referencedModelId = Long.valueOf(subProcessIdNode.asLong());
          if (!modelMap.containsKey(referencedModelId))
          {
            Model subProcessModel = (Model)this.modelRepository.findOne(referencedModelId);
            if (subProcessModel != null)
            {
              modelMap.put(referencedModelId, subProcessModel);
              parseReferenceModels(subProcessModel, modelMap);
            }
          }
        }
      }
    }
  }
  
  /*protected void parseStepReferenceModels(Model model, Map<Long, Model> modelMap)
  {
    if (StringUtils.isNotEmpty(model.getModelEditorJson()))
    {
      KickstartModelDefinition definition = this.storageHandler.getDefinition(model.getModelEditorJson(), false, false);
      fillStepSubProcessMap(model.getId(), definition, modelMap);
    }
  }
  
  public void fillStepSubProcessMap(Long modelId, KickstartModelDefinition subProcessDefinition, Map<Long, Model> modelMap)
  {
    FormRepresentation formRepresentation = subProcessDefinition.getStartForm();
    if (formRepresentation != null) {
      fillModelInMap(formRepresentation.getId(), modelMap);
    }
    List<Model> subProcessModels = this.modelRepository.findModelsByModelTypeAndReferenceId(Integer.valueOf(1), modelId);
    Map<Long, Model> subProcessModelMap = new HashMap();
    for (Model subProcessModel : subProcessModels) {
      subProcessModelMap.put(subProcessModel.getId(), subProcessModel);
    }
    loopThroughStepsForSubProcesses(subProcessDefinition.getSteps(), subProcessModelMap, modelMap);
  }
  
  protected void loopThroughStepsForSubProcesses(List<StepDefinition> steps, Map<Long, Model> subProcessModelMap, Map<Long, Model> modelMap)
  {
    for (StepDefinition step : steps) {
      if ((step instanceof SubProcessStepDefinition))
      {
        SubProcessStepDefinition subProcessStep = (SubProcessStepDefinition)step;
        if ((subProcessStep.getSubProcessDefinition() != null) && (subProcessStep.getSubProcessDefinition().getId() != null) && 
          (subProcessModelMap.containsKey(subProcessStep.getSubProcessDefinition().getId())))
        {
          Model subProcessModel = (Model)subProcessModelMap.get(subProcessStep.getSubProcessDefinition().getId());
          modelMap.put(subProcessModel.getId(), subProcessModel);
          KickstartModelDefinition subProcessDefinition = this.storageHandler.getDefinition(subProcessModel.getModelEditorJson(), false, false);
          fillStepSubProcessMap(subProcessStep.getSubProcessDefinition().getId(), subProcessDefinition, modelMap);
        }
      }
      else if ((step instanceof HumanStepDefinition))
      {
        HumanStepDefinition humanStep = (HumanStepDefinition)step;
        
        FormRepresentation formRepresentation = humanStep.getFormDefinition();
        if (formRepresentation != null) {
          fillModelInMap(formRepresentation.getId(), modelMap);
        }
        if (humanStep.getOverdueSteps() != null) {
          loopThroughStepsForSubProcesses(humanStep.getOverdueSteps(), subProcessModelMap, modelMap);
        }
      }
      else if ((step instanceof ChoiceStepDefinition))
      {
        ChoiceStepDefinition choiceStep = (ChoiceStepDefinition)step;
        if (CollectionUtils.isNotEmpty(choiceStep.getChoices())) {
          for (ChoiceContainer choiceContainer : choiceStep.getChoices()) {
            loopThroughStepsForSubProcesses(choiceContainer.getSteps(), subProcessModelMap, modelMap);
          }
        }
      }
      else if ((step instanceof DecisionStepDefinition))
      {
        DecisionStepDefinition decisionStepDefinition = (DecisionStepDefinition)step;
        if (decisionStepDefinition.getDecisionTableDefinition() != null) {
          fillModelInMap(decisionStepDefinition.getDecisionTableDefinition().getId(), modelMap);
        }
      }
    }
  }*/
  
  protected void fillModelInMap(Long modelId, Map<Long, Model> modelMap)
  {
    Model model = (Model)this.modelRepository.findOne((Long.valueOf(modelId)));
    if (model != null) {
      modelMap.put((Long.valueOf(model.getId())), model);
    }
  }
}
