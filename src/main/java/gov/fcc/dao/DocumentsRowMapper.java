package gov.fcc.dao;


import gov.fcc.model.Document;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentsRowMapper implements RowMapper<Document> {

    @Override
    public Document mapRow(ResultSet resultSet, int i) throws SQLException {
        Document doc = new Document();
        // split the source type and application id from bpif application id.
        // should look like this from document table: KidVid_10001.
        // 'KidVid' would be source type
        // '10001' would be applicationid

        doc.setSourceSystem(resultSet.getString("account_id").split("_")[0]);
        doc.setDocumentId(resultSet.getString("account_id").split("_")[1]);
        doc.setExtension(doc.getSourceSystem());
        doc.setFullPath(doc.getS3Path() + resultSet.getString("account_id") + doc.getExtension());
        doc.setPayload(resultSet.getBlob("payload"));

        return doc;
    }
}
