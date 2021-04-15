package myStoredFunction.mapper;

import myStoredFunction.entity.Soil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SoilMapper implements RowMapper<Soil> {
    public static final SoilMapper INSTANCE = new SoilMapper();

    @Override
    public Soil mapRow(ResultSet rs, int rowNum) throws SQLException {
        Soil soil = new Soil();
        soil.setId(rs.getLong("id"));
        soil.setUuidT(rs.getString("uuid_t"));
        soil.setPName(rs.getString("p_name"));
        soil.setPType(rs.getString("p_type"));
        soil.setPTypeFk(rs.getInt("p_type_fk"));
        //soil.setPTypeFk(rs.deleteRow());   // void
        //soil.setPTypeFk(rs.rowDeleted()); // boolean
        //soil.setPTypeFk(rs.rowUpdated()); // boolean
        return soil;
    }
}
