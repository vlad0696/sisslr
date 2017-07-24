package org.ipps.sisslr.contrlollers;


import org.ipps.sisslr.models.Address;
import org.springframework.stereotype.Controller;

import java.sql.*;
import java.util.ArrayList;


@Controller
public class ExampleController {

    public static Connection getPostgresConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Test","postgres", "qwerty123");
            return connection;
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Address> getHello() throws SQLException {
        Connection connection = getPostgresConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs =stmt.executeQuery("select id, city from public.address;");
        ArrayList<Address> list = new ArrayList<Address>();

        while ( rs.next() ) {
            int id = rs.getInt("id");
            String  city = rs.getString("city");
            Address address= new Address();
            address.setId(id);
            address.setCity(city);
            list.add(address);
        }

        return list;
    }





}
