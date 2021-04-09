package myStoredFunction.controller;

import myStoredFunction.dto.SoilDto;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public interface ConnectInterface {

    public static List<SoilDto> myConnection() throws IOException {
        // ОБРАЩЕНИЕ К БАЗЕ ddoc, функция f_sel_eio_table_302_ex(null)
        String query = "select id, uuid_t, p_name, p_type, p_type_fk from f_sel_eio_table_302_ex(null)";
        List<SoilDto> soilDtoList = new ArrayList<>();
        SoilDto soilDto = new SoilDto();
        Properties property = new Properties();
        try {
            // creating stream from application.properties
            FileInputStream fis = new FileInputStream("src/main/resources/application.properties");
            property.load(fis);

            // JDBC URL, username and password of MySQL server
            String url = property.getProperty("spring.datasource.url");
            String user = property.getProperty("spring.datasource.username");
            String password = property.getProperty("spring.datasource.password");

            // opening database connection to MySQL server
            Connection connection = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            Statement statement = connection.createStatement();

            // executing SELECT query
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                //soilDtoList.add((SoilDto) rs);
                int id = rs.getInt("id");
                String uuid_t = rs.getString("uuid_t");
                String p_name = rs.getString("p_name");
                String p_type = rs.getString("p_type");
                int p_type_fk = rs.getInt("p_type_fk");

                System.out.printf("id: %d, uuid_t: %s, p_name: %s, p_type: %s, p_type_fk: %d %n", id, uuid_t, p_name, p_type, p_type_fk);

                soilDto.setId(rs.getInt("id"));
                soilDto.setUuid_t(rs.getString("uuid_t"));
                soilDto.setP_name(rs.getString("p_name"));
                soilDto.setP_type(rs.getString("p_type"));
                soilDto.setP_type_fk(rs.getInt("p_type_fk"));
                soilDtoList.add(soilDto);
            }
            //System.out.println("\n" + Arrays.asList(soilDtoList));

            connection.close();
            statement.close();
            rs.close();

            // closing processes
            try { statement.close(); } catch(SQLException se) { /*can't do anything */ }
            try { connection.close(); } catch(SQLException se) { /*can't do anything */ }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return soilDtoList;
    }
}
