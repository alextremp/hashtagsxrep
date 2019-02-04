package cat.xarxarepublicana.hashtagsxrep.infrastructure.cache;

import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Ranking;
import cat.xarxarepublicana.hashtagsxrep.domain.report.Report;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.List;

import static cat.xarxarepublicana.hashtagsxrep.infrastructure.cache.CacheConstants.SINGLE_ENTRY_KEY;

public class CachedMonitorRepository implements MonitorRepository {

    private final MonitorRepository monitorRepository;
    private final LoadingCache<String, Monitor> monitorCache;
    private final LoadingCache<String, List<Monitor>> monitorListCache;
    private final LoadingCache<String, Report> reportCache;
    private final LoadingCache<String, Ranking> rankingCache;

    public CachedMonitorRepository(MonitorRepository monitorRepository, LoadingCache<String, Monitor> monitorCache, LoadingCache<String, List<Monitor>> monitorListCache, LoadingCache<String, Report> reportCache, LoadingCache<String, Ranking> rankingCache) {
        this.monitorRepository = monitorRepository;
        this.monitorCache = monitorCache;
        this.monitorListCache = monitorListCache;
        this.reportCache = reportCache;
        this.rankingCache = rankingCache;
    }

    @Override
    public void save(Monitor monitor) {
        monitorRepository.save(monitor);
        monitorCache.invalidate(monitor.getId());
        reportCache.invalidate(monitor.getId());
        monitorListCache.invalidate(SINGLE_ENTRY_KEY);
        rankingCache.invalidate(SINGLE_ENTRY_KEY);
    }

    @Override
    public void updateCursor(Monitor monitor, String nextQueryString) {
        monitorRepository.updateCursor(monitor, nextQueryString);
        monitorCache.invalidate(monitor.getId());
        reportCache.invalidate(monitor.getId());
        rankingCache.invalidate(SINGLE_ENTRY_KEY);
    }

    @Override
    public Monitor findById(String id) {
        return monitorCache.get(id);
    }

    @Override
    public List<Monitor> getActiveMonitors() {
        return monitorRepository.getActiveMonitors();
    }

    @Override
    public String getMaxTweetId(String monitorId) {
        return monitorRepository.getMaxTweetId(monitorId);
    }

    @Override
    public List<Monitor> getLastMonitors() {
        return monitorListCache.get(SINGLE_ENTRY_KEY);
    }

    @Override
    public Monitor findByTwitterQuery(String twitterQuery) {
        return monitorRepository.findByTwitterQuery(twitterQuery);
    }

    @Override
    public void disable(String id) {
        monitorRepository.disable(id);
        monitorCache.invalidate(id);
        monitorListCache.invalidate(SINGLE_ENTRY_KEY);
    }

    @Override
    public void delete(Monitor monitor) {
        monitorRepository.delete(monitor);
        monitorCache.invalidate(monitor.getId());
        monitorListCache.invalidate(SINGLE_ENTRY_KEY);
    }
}
