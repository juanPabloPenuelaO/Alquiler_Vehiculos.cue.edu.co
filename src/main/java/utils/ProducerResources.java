package utils;

import annotations.MysqlConn;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@ApplicationScoped
public class ProducerResources {
    @Inject
    private Logger log;
    @Resource(name="jdbc/mysqlDB")
    private DataSource ds;
    @Produces
    @RequestScoped
    @MysqlConn
    private Connection beanConnection() throws NamingException, SQLException {
        return ds.getConnection();
    }
    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint){
        return
                Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
    public void close(@Disposes @MysqlConn Connection connection) throws
            SQLException {
        connection.close();
        log.info("cerrando la conexión a la base de datos mysql!");
    }
}
