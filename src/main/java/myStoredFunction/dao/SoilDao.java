package myStoredFunction.dao;

import myStoredFunction.entity.Soil;
import myStoredFunction.mapper.SoilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoilDao {
    //@Autowired
    //private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate; // для параметрич-х запросов

    public SoilDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Soil> findAll() {
        return namedParameterJdbcTemplate.query(
                "select id, uuid_t, p_name, p_type, p_type_fk from f_sel_eio_table_302_ex(null);",
                SoilMapper.INSTANCE
        );
    }

    public Soil findById(Long id) {
        return namedParameterJdbcTemplate.queryForObject(
                "select id, uuid_t, p_name, p_type from eio_table_302 where id = :id;",
                new MapSqlParameterSource("id", id),
                SoilMapper.INSTANCE
        );
    }

    public void insert(Soil soil) {
        namedParameterJdbcTemplate.update(
                "insert into eio_table_302 (p_type, p_name) values (1, :pName);",
                new BeanPropertySqlParameterSource(soil)
        );
    }

    public void update(Soil soil) {
        namedParameterJdbcTemplate.update(
                "update eio_table_302 set p_name = :pName where id = :id;",
                new BeanPropertySqlParameterSource(soil)
        );
    }

    public Integer delete(Long id) {
        return namedParameterJdbcTemplate.queryForObject(
                "select f_del_eio_table_302(:id);",
                new MapSqlParameterSource("id", id),
                Integer.class
        );
    }




}