package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.domain.invite.InviteGroup;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Ranking;
import cat.xarxarepublicana.hashtagsxrep.domain.report.Report;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.*;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfiguration {

    @Qualifier("monitorCache")
    @Bean
    public LoadingCache<String, Monitor> monitorCache(JdbcMonitorRepository jdbcMonitorRepository) {
        return Caffeine.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(monitorId -> jdbcMonitorRepository.findById(monitorId));
    }

    @Qualifier("monitorListCache")
    @Bean
    public LoadingCache<String, List<Monitor>> monitorListCache(JdbcMonitorRepository jdbcMonitorRepository) {
        return Caffeine.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(key -> jdbcMonitorRepository.getLastMonitors());
    }

    @Qualifier("reportCache")
    @Bean
    public LoadingCache<String, Report> reportCache(JdbcReportRepository jdbcReportRepository) {
        return Caffeine.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(monitorId -> jdbcReportRepository.loadReport(monitorId));
    }

    @Qualifier("userCache")
    @Bean
    public LoadingCache<String, User> userCache(JdbcUserRepository jdbcUserRepository) {
        return Caffeine.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .maximumSize(100)
                .build(userId -> jdbcUserRepository.findById(userId));
    }

    @Qualifier("rankingCache")
    @Bean
    public LoadingCache<String, Ranking> rankingCache(JdbcRankingRepository jdbcRankingRepository) {
        return Caffeine.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(key -> jdbcRankingRepository.loadRanking());
    }

    @Qualifier("inviteGroupCache")
    @Bean
    public LoadingCache<String, InviteGroup> inviteGroupCache(JdbcInviteRepository jdbcInviteRepository) {
        return Caffeine.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(pollId -> jdbcInviteRepository.loadInvitesForPoll(pollId));
    }
}
