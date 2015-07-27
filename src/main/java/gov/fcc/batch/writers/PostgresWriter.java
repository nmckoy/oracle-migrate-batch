package gov.fcc.batch.writers;

import gov.fcc.dao.DocumentsDAO;
import gov.fcc.model.Document;
import gov.fcc.run.Logger;
import org.springframework.batch.item.ItemWriter;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class PostgresWriter implements ItemWriter<Document> {

    private DocumentsDAO docDAO;
    private Logger logger;

    public DocumentsDAO getDocDAO() {
        return docDAO;
    }

    public void setDocDAO(DocumentsDAO docDAO) {
        this.docDAO = docDAO;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void write(List<? extends Document> documents) throws Exception {
        Map<String, String> docMap = new HashMap<String, String>();

        for (Document doc : documents) {
            docMap.put("DOCUMENT_TYPE", doc.getExtension());
            docMap.put("DOCUMENT_URL", doc.getFullPath());
            docMap.put("DOCUMENT_ID", doc.getDocumentId());
            docMap.put("SOURCE_SYSTEM", doc.getSourceSystem());

//            if (docDAO.ETLUpdate(docMap)){
            if (docDAO.ETLInsert(docMap)){
                logger.addRowsUpdated(1);
            } else {
                logger.addRowsFailed(1);
            }

        }

    }
}
