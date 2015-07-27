package gov.fcc.batch.writers;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.IOUtils;
import gov.fcc.model.Document;
import gov.fcc.run.Logger;
import gov.fcc.s3.FCCAmazonS3;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.batch.item.ItemWriter;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public class S3Writer implements ItemWriter<Document> {
    private Logger logger;
    private FCCAmazonS3 s3;

    @Override
    public void write(List<? extends Document> documents) throws Exception {

        for (Document doc : documents) {
            Blob blob = doc.getPayload();
            String filename = doc.getFullPath();

            try {
//                OutputStream output = new FileOutputStream(filename);
                ObjectMetadata meta = new ObjectMetadata();
                byte[] buff = blob.getBytes(1, (int) blob.length());
                ByteArrayInputStream input = new ByteArrayInputStream(blob.getBytes(1, (int) blob.length()));

                // setting the content length for s3 headers so as to be sure there are no OOM errors
                meta.setContentLength(Long.valueOf(buff.length));

                System.out.println("filename" + filename);
                s3.getConnection().putObject(s3.getBucketName(), filename, input, meta);

//                output.write(buff);
//                output.close();
                logger.addWrittenFiles(1);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (AmazonClientException e) {
                e.printStackTrace();
                logger.addFailedWrites(1);
            }
        }
    }

    public void setS3(FCCAmazonS3 s3) {
        this.s3 = s3;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
