package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.ProposalFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Ranking;
import cat.xarxarepublicana.hashtagsxrep.domain.report.Report;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.cache.CachedMonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.cache.CachedRankingRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.cache.CachedReportRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.cache.CachedUserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.*;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.*;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.twitter.TwitterApi;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.twitter.TwitterRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.List;

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
    public JdbcUserRepository jdbcUserRepository(UserMapper userMapper) {
        return new JdbcUserRepository(userMapper);
    }

    @Bean
    @Primary
    public CachedUserRepository cachedJdbcUserRepository(@Qualifier("userCache") LoadingCache<String, User> userCache, JdbcUserRepository jdbcUserRepository) {
        return new CachedUserRepository(userCache, jdbcUserRepository);
    }

    @Bean
    public UserFactory defaultUserFactory() {
        return new UserFactory();
    }

    @Bean
    public JdbcMonitorRepository jdbcMonitorRepository(MonitorMapper monitorMapper) {
        return new JdbcMonitorRepository(monitorMapper);
    }

    @Bean
    @Primary
    public CachedMonitorRepository cachedMonitorRepository(JdbcMonitorRepository jdbcMonitorRepository, @Qualifier("monitorCache") LoadingCache<String, Monitor> monitorCache, @Qualifier("monitorListCache") LoadingCache<String, List<Monitor>> monitorListCache, @Qualifier("reportCache") LoadingCache<String, Report> reportCache, @Qualifier("rankingCache") LoadingCache<String, Ranking> rankingCache) {
        return new CachedMonitorRepository(jdbcMonitorRepository, monitorCache, monitorListCache, reportCache, rankingCache);
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
    public JdbcReportRepository jdbcReportRepository(ReportMapper reportMapper) {
        return new JdbcReportRepository(reportMapper);
    }

    @Bean
    @Primary
    public CachedReportRepository cachedReportRepository(JdbcReportRepository jdbcReportRepository, @Qualifier("reportCache") LoadingCache<String, Report> reportCache) {
        return new CachedReportRepository(jdbcReportRepository, reportCache);
    }

    @Bean
    public JdbcRankingRepository jdbcRankingRepository(RankingMapper rankingMapper) {
        return new JdbcRankingRepository(rankingMapper);
    }

    @Bean
    @Primary
    public CachedRankingRepository cachedRankingRepository(JdbcRankingRepository jdbcRankingRepository, @Qualifier("rankingCache")LoadingCache<String, Ranking> rankingCache) {
        return new CachedRankingRepository(jdbcRankingRepository, rankingCache);
    }

    @Bean
    public PollFactory pollFactory() {
        return new PollFactory();
    }

    @Bean
    public ProposalFactory proposalFactory() {
        return new ProposalFactory();
    }

    @Bean
    public PollRepository jdbcPollRepository(PollMapper pollMapper, InviteMapper inviteMapper) {
        return new JdbcPollRepository(pollMapper, inviteMapper);
    }

    @Bean
    public JdbcInviteRepository jdbcInviteRepository(InviteMapper inviteMapper) {
        return new JdbcInviteRepository(inviteMapper);
    }

    @Bean
    public DataSource dataSource(
            @Value("${app.db.user}") String user,
            @Value("${app.db.password}") String password,
            @Value("${app.db.url}") String url) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(5);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");
        HikariDataSource dataSource = new HikariDataSource(config);
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
