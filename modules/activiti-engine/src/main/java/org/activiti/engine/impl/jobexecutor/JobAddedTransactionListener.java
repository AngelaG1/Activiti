
package org.activiti.engine.impl.jobexecutor;

import org.activiti.engine.impl.cfg.TransactionPropagation;
import org.activiti.engine.impl.interceptor.CommandConfig;
import org.activiti.engine.impl.asyncexecutor.AsyncExecutor;
import org.activiti.engine.impl.cfg.TransactionListener;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.persistence.entity.JobEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tijs Rademakers
 */
public class JobAddedTransactionListener implements TransactionListener {

    private static Logger log = LoggerFactory.getLogger(JobAddedTransactionListener.class);

    protected JobEntity job;
    protected AsyncExecutor asyncExecutor;

    public JobAddedTransactionListener(JobEntity job, AsyncExecutor asyncExecutor) {
        this.job = job;
        this.asyncExecutor = asyncExecutor;
    }

    @Override
    public void execute(CommandContext commandContext) {
        CommandExecutor commandExecutor = commandContext.getProcessEngineConfiguration().getCommandExecutor();
        CommandConfig commandConfig = new CommandConfig(false, TransactionPropagation.REQUIRES_NEW);
        commandExecutor.execute(commandConfig, new Command<Void>() {
            public Void execute(CommandContext commandContext) {
                if (log.isTraceEnabled()) {
                    log.trace("notifying job executor of new job");
                }
                asyncExecutor.executeAsyncJob(job);
                return null;
            }
        });
    }
}
