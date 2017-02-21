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

package org.activiti.engine.impl.cmd;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

import org.activiti.bpmn.model.TimerEventDefinition;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class RescheduleTimerJobCmd implements Command<TimerJobEntity>, Serializable {

  private static final long serialVersionUID = 1L;

  private final String timerJobId;
  private String timeDate;
  private String timeDuration; 
  private String timeCycle;
  private String endDate;
  private String calendarName;

  public RescheduleTimerJobCmd(String timerJobId, String timeDate, String timeDuration, String timeCycle, String endDate, String calendarName) {
    if (timerJobId == null) {
      throw new ActivitiIllegalArgumentException("The timer job id is mandatory, but 'null' has been provided.");
    }
    
    int timeValues = Collections.frequency(Arrays.asList(timeDate, timeDuration, timeCycle), null);
    if(timeValues == 0) {
      throw new ActivitiIllegalArgumentException("A non-null value is required for one of timeDate, timeDuration, or timeCycle");
    }
    else if(timeValues != 2) {
      throw new ActivitiIllegalArgumentException("At most one non-null value can be provided for timeDate, timeDuration, or timeCycle");
    }
    
    if(endDate != null && timeCycle == null) {
      throw new ActivitiIllegalArgumentException("An end date can only be provided when rescheduling a timer using timeDuration.");
    }
    
    this.timerJobId = timerJobId;
    this.timeDate = timeDate;
    this.timeDuration = timeDuration;
    this.timeCycle = timeCycle;
    this.endDate = endDate;
    this.calendarName = calendarName;
  }

  public TimerJobEntity execute(CommandContext commandContext) {
    TimerEventDefinition ted = new TimerEventDefinition();
    ted.setTimeDate(timeDate);
    ted.setTimeDuration(timeDuration);
    ted.setTimeCycle(timeCycle);
    ted.setEndDate(endDate);
    ted.setCalendarName(calendarName);
    TimerJobEntity timerJob = commandContext.getJobManager().rescheduleTimerJob(timerJobId, ted);
    return timerJob;
  }

}
