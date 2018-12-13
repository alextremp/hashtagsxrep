package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.JdbcMonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.JdbcTwitterExtractionRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.JdbcUserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.MonitorMapper;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.TwitterExtractionMapper;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.UserMapper;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.local.InMemoryUserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.twitter.TwitterApi;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.twitter.TwitterRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class RepositoryConfiguration {

    @Value("${twitter.apiKey}")
    private String apiKey;

    @Value("${twitter.apiSecret}")
    private String apiSecret;

    @Value("${twitter.appKey}")
    private String appKey;

    @Value("${twitter.appSecret}")
    private String appSecret;

    @Value("${twitter.oauthCallback}")
    private String oauthCallback;

    @Bean
    public TwitterRepository twitterRepository(UserFactory userFactory, OAuth10aService oAuth10aService, OAuth1AccessToken applicationToken) {
        ObjectMapper objectMapper = new ObjectMapper();
        return new TwitterRepositoryImpl(userFactory, oAuth10aService, applicationToken, objectMapper);
    }

    @Bean
    public OAuth10aService oAuth10aService() {
        return new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .callback(oauthCallback)
                .build(TwitterApi.instance());
    }

    @Bean
    public OAuth1AccessToken applicationToken() {
        return new OAuth1AccessToken(appKey, appSecret);
    }

    @Bean
    @Profile("test")
    public UserRepository inMemoryUserRepository(UserFactory userFactory) {
        return new InMemoryUserRepository(userFactory);
    }

    @Bean
    public UserRepository jdbcUserRepository(UserMapper userMapper) {
        return new JdbcUserRepository(userMapper);
    }

    @Bean
    public UserFactory defaultUserFactory() {
        return new UserFactory();
    }

    @Bean
    public MonitorRepository jdbcMonitorRepository(MonitorMapper monitorMapper) {
        return new JdbcMonitorRepository(monitorMapper);
    }

    @Bean
    public MonitorFactory defaultMonitorFactory() {
        return new MonitorFactory();
    }

    @Bean
    public TwitterExtractionRepository jdbcTwitterExtractionRepository(MonitorRepository monitorRepository, UserRepository userRepository, UserFactory userFactory, TwitterExtractionFactory twitterExtractionFactory, TwitterExtractionMapper twitterExtractionMapper) {
        return new JdbcTwitterExtractionRepository(monitorRepository, userRepository, userFactory, twitterExtractionFactory, twitterExtractionMapper);
    }

    @Bean
    public TwitterExtractionFactory twitterExtractionFactory() {
        return new TwitterExtractionFactory();
    }

    @Bean
    public DataSource dataSource(
            @Value("${app.db.driver}") Class<Driver> driver,
            @Value("${app.db.user}") String user,
            @Value("${app.db.password}") String password,
            @Value("${app.db.url}") String url) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory;
    }

}
