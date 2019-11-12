package cat.xarxarepublicana.hashtagsxrep.helper;

import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class InitDbHelper {

  @Autowired
  private DataSource dataSource;
  @Autowired
  private ResourceHelper resourceHelper;
  @Value("classpath:database/DDL.sql")
  private Resource ddlResource;
  @Value("classpath:fixtures/db/initdb.sql")
  private Resource insertsResource;

  public void initDb() {
    try {
      Connection connection = dataSource.getConnection();
      ScriptRunner scriptRunner = new ScriptRunner(connection);
      scriptRunner.setAutoCommit(true);
      scriptRunner.setStopOnError(true);
      scriptRunner.setLogWriter(null);
      scriptRunner.runScript(reader("DROP SCHEMA IF EXISTS hashtagsxrep;"));
      scriptRunner.runScript(reader("CREATE SCHEMA hashtagsxrep;"));
      scriptRunner.runScript(reader("USE hashtagsxrep;"));
      scriptRunner.runScript(reader(ddlResource));
      scriptRunner.runScript(reader(insertsResource));
      connection.commit();
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException("ERROR::initDB", e);
    }
  }

  private Reader reader(Resource resource) {
    return new StringReader(resourceHelper.readResource(resource));
  }

  private Reader reader(String script) {
    return new StringReader(script);
  }
}
