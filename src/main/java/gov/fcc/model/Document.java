package gov.fcc.model;


import java.sql.Blob;

public class Document {

    private String sourceSystem;
    private String documentId;
    private Blob payload;
    private String fullPath;
    private String extension;
    private static final String s3Path = "opif/auth/tv/";

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Blob getPayload() {
        return payload;
    }

    public void setPayload(Blob payload) {
        this.payload = payload;
    }

    public String getS3Path() {
        return s3Path;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String source) {
        if ("KidVid".equalsIgnoreCase(source) || "App".equalsIgnoreCase(source)) { // for cdbs
            this.extension = ".html";
        } else if (documentId.matches("[a-z]+")){ // for lm apps
            this.extension = ".pdf";
        } else { // for authorizations with Auth_ prefix
            this.extension = ".pdf";
        }
    }
}
