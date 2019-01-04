package cat.xarxarepublicana.hashtagsxrep.infrastructure.cache;

import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.report.Report;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.List;
import java.util.stream.Collectors;

public class CachedMonitorRepository implements MonitorRepository {

    private static final String MONITOR_LIST_CACHE_KEY = "K";
    private final MonitorRepository monitorRepository;
    private final LoadingCache<String, Monitor> monitorCache;
    private final LoadingCache<String, List<Monitor>> monitorListCache;
    private final LoadingCache<String, Report> reportCache;

    public CachedMonitorRepository(MonitorRepository monitorRepository, LoadingCache<String, Monitor> monitorCache, LoadingCache<String, List<Monitor>> monitorListCache, LoadingCache<String, Report> reportCache) {
        this.monitorRepository = monitorRepository;
        this.monitorCache = monitorCache;
        this.monitorListCache = monitorListCache;
        this.reportCache = reportCache;
    }

    @Override
    public void save(Monitor monitor) {
        monitorRepository.save(monitor);
        monitorCache.invalidate(monitor.getId());
        monitorListCache.invalidate(MONITOR_LIST_CACHE_KEY);
    }

    @Override
    public void updateCursor(Monitor monitor, String nextQueryString) {
        monitorRepository.updateCursor(monitor, nextQueryString);
        monitorCache.invalidate(monitor.getId());
        reportCache.invalidate(monitor.getId());
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
        return monitorListCache.get(MONITOR_LIST_CACHE_KEY);
    }

    @Override
    public Monitor findByTwitterQuery(String twitterQuery) {
        return monitorRepository.findByTwitterQuery(twitterQuery);
    }

    @Override
    public void delete(Monitor monitor) {
        monitorRepository.delete(monitor);
        monitorCache.invalidate(monitor.getId());
        monitorListCache.invalidate(MONITOR_LIST_CACHE_KEY);
    }
}
