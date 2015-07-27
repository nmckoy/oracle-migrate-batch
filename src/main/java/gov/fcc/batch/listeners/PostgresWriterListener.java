package gov.fcc.batch.listeners;


import gov.fcc.model.Document;
import gov.fcc.run.Logger;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class PostgresWriterListener implements ItemWriteListener<Document> {

    private Logger logger;

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void beforeWrite(List<? extends Document> documents) {
//        logger.log(".");
    }

    @Override
    public void afterWrite(List<? extends Document> documents) {
//        logger.log(".");
    }

    @Override
    public void onWriteError(Exception e, List<? extends Document> documents) {
        logger.log("ERROR -> " + e.getMessage());
        e.printStackTrace();
    }
}
