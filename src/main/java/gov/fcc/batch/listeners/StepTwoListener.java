package gov.fcc.batch.listeners;


import gov.fcc.run.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepTwoListener implements StepExecutionListener {

    private Logger logger;
    private String threadName;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.logWithDate("starting write to postgres");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.log("all updates to postgres are done using " + threadName);
        logger.log(logger.getRowsUpdated() + " rows have been updated");
        logger.log(logger.getRowsFailed() > 0 ? logger.getRowsFailed() + " rows have failed to updated" : "there were no update failures");
        return stepExecution.getExitStatus();
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
