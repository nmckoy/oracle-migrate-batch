package gov.fcc.batch.writers;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import gov.fcc.dao.DocumentsDAO;
import gov.fcc.model.Document;
import gov.fcc.run.Logger;
import gov.fcc.s3.FCCAmazonS3;
import org.springframework.batch.item.ItemWriter;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class PostgresWriter implements ItemWriter<Document> {

    private DocumentsDAO docDAO;
    private FCCAmazonS3 s3;
    private Logger logger;



    @Override
    public void write(List<? extends Document> documents) throws Exception {
        Map<String, String> docMap = new HashMap<String, String>();

        for (Document doc : documents) {
            docMap.put("DOCUMENT_TYPE", doc.getExtension());
            docMap.put("DOCUMENT_URL", doc.getFullPath());
            docMap.put("DOCUMENT_ID", doc.getDocumentId());
            docMap.put("SOURCE_SYSTEM", doc.getSourceSystem());

            try {
                // check if file exists in s3 before we update
                S3Object obj = s3.getConnection().getObject(s3.getBucketName(), doc.getFullPath());

                if (obj != null && docDAO.ETLUpdate(docMap)){
                    logger.addRowsUpdated(1);
                } else {
                    logger.addRowsFailed(1);
                }
            } catch (AmazonClientException e) {
                logger.log("file " + doc.getFullPath() + " doesnt exist on s3");
                logger.addRowsFailed(1);
            }

        }

    }

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

    public void setS3(FCCAmazonS3 s3) {
        this.s3 = s3;
    }
}
