package dao;

import java.sql.Connection;

public interface databaseDAO {
    public abstract void connectBD(String ip, String username, String password, String databaseName);
    public abstract void disconnect();
    public abstract boolean isConnected();
    public abstract String getJdbcUrl();
    public abstract Connection getConnection();
}
