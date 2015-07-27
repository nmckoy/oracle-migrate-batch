package gov.fcc.s3;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

public class FCCAmazonS3 {

    private AmazonS3 connection;
    private final String bucketName = "fcc-opif-dev";

    public FCCAmazonS3(String accessKey, String secretKey) {
        this.connection = new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey));
    }

    public AmazonS3 getConnection() {
        return connection;
    }

    public String getBucketName() {
        return bucketName;
    }
}
