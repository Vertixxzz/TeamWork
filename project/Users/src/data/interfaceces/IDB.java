package data.interfaceces;

import java.sql.Connection;

public interface IDB {
    //interface with method Connection
    Connection getConnection();
    void close();
}
