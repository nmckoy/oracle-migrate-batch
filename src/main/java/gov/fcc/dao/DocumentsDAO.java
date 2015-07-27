package gov.fcc.dao;


import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class DocumentsDAO {

    private JdbcTemplate oracleJdbcTemplate;
    private JdbcTemplate postgresJdbcTemplate;

    public JdbcTemplate getOracleJdbcTemplate() {
        return oracleJdbcTemplate;
    }

    public void setOracleJdbcTemplate(JdbcTemplate oracleJdbcTemplate) {
        this.oracleJdbcTemplate = oracleJdbcTemplate;
    }

    public JdbcTemplate getPostgresJdbcTemplate() {
        return postgresJdbcTemplate;
    }

    public void setPostgresJdbcTemplate(JdbcTemplate postgresJdbcTemplate) {
        this.postgresJdbcTemplate = postgresJdbcTemplate;
    }

    public boolean ETLUpdate(Map<String, String> document) {
        String sql      = "UPDATE etl_document " +
                          "SET document_type = ?, document_url = ?, document_status = 'confirmed' " +
                          "WHERE document_id = ? " +
                          "AND source_system = ?";
        Object[] params = {document.get("DOCUMENT_TYPE"),
                           document.get("DOCUMENT_URL"),
                           document.get("DOCUMENT_ID"),
                           document.get("SOURCE_SYSTEM")};
        int[] types     = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

        int rows = postgresJdbcTemplate.update(sql, params, types);

        return rows == 1;
    }

    public boolean ETLInsert(Map<String, String> document) {
        String sql      = "insert into etl_document values (?, ?, ?, 'confirmed', ?)";
        Object[] params = {document.get("DOCUMENT_ID"),document.get("SOURCE_SYSTEM"),document.get("DOCUMENT_TYPE"),document.get("DOCUMENT_URL")};
        int[] types     = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

        int rows = postgresJdbcTemplate.update(sql, params, types);

        return rows == 1;
    }
}
