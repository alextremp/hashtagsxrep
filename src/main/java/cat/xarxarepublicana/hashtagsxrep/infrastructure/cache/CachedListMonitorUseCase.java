package cat.xarxarepublicana.hashtagsxrep.infrastructure.cache;

import cat.xarxarepublicana.hashtagsxrep.application.monitor.ListMonitorUseCase;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class CachedListMonitorUseCase extends ListMonitorUseCase {

    private final LoadingCache<String, ListMonitorResponse> cache;
    private static final String CACHE_KEY = "K";

    public CachedListMonitorUseCase(MonitorRepository monitorRepository) {
        super(monitorRepository);
        this.cache = Caffeine.newBuilder()
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .build(k -> super.listMonitor());
    }

    @Override
    public ListMonitorResponse listMonitor() {
        return cache.get(CACHE_KEY);
    }

    public void invalidate() {
        cache.invalidate(CACHE_KEY);
    }
}
