package proj2.DTO;

public class MetadataResponseDTO {
    String tableName;
    String columns;

    public MetadataResponseDTO(String tableName, String columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

}
