package gov.fcc.batch.listeners;


import gov.fcc.run.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepOneListener implements StepExecutionListener {

    private Logger logger;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.logWithDate("starting write to s3");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.log("all writes to s3 are done");
        logger.log(logger.getWrittenFiles() + " files were written");
        logger.log(logger.getFailedWrites() > 0 ? logger.getFailedWrites() + " files failed to write" : "there were no write failures");
        return stepExecution.getExitStatus();
    }
}
