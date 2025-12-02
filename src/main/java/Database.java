import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class Database {
    private static BasicDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/northwind");
            dataSource.setUsername("root");
            dataSource.setPassword("yearup");

            dataSource.setMinIdle(2);
            dataSource.setMaxIdle(5);
            dataSource.setMaxTotal(10);
        }
        return dataSource;
    }
}

