package gov.fcc.batch.processors;


import gov.fcc.model.Document;
import gov.fcc.run.Logger;
import org.springframework.batch.item.ItemProcessor;

public class PostgresProcessor implements ItemProcessor<Document, Document> {

    private String threadName;
    private Logger logger;

    @Override
    public Document process(Document document) throws Exception {
        logger.logWithDate(threadName + " working: " + document.getDocumentId());

        return document;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
