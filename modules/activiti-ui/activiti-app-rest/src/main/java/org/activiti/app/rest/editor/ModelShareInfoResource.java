package org.activiti.app.rest.editor;

import org.activiti.app.model.common.ResultListDataRepresentation;
import org.activiti.app.model.editor.ShareInfoUpdateRepresentation;
import org.activiti.app.service.api.ModelService;
//import org.codahale.metrics.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelShareInfoResource
{
  @Autowired
  protected ModelService modelService;
  
  @RequestMapping(value={"/rest/models/{modelId}/share-info"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  //@Timed
  public ResultListDataRepresentation getModelShareInfo(@PathVariable Long modelId)
  {
    return this.modelService.getModelShareInfo(modelId);
  }
  
  @RequestMapping(value={"/rest/models/{modelId}/share-info"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, produces={"application/json"})
  @ResponseStatus(HttpStatus.OK)
  public void updateShareInfoForModel(@PathVariable Long modelId, @RequestBody ShareInfoUpdateRepresentation update)
  {
    this.modelService.updateShareInfoForModel(modelId, update);
  }
}