package cat.xarxarepublicana.hashtagsxrep.infrastructure.cache;

import cat.xarxarepublicana.hashtagsxrep.application.report.GetTwitterQueryReportUseCase;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.report.ReportRepository;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class CachedGetTwitterQueryReportUseCase extends GetTwitterQueryReportUseCase {

    private final LoadingCache<String, GetTwitterQueryReport> cache;
    public CachedGetTwitterQueryReportUseCase(ReportRepository reportRepository, MonitorRepository monitorRepository) {
        super(reportRepository, monitorRepository);
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .build(hashtag -> super.getTwitterQueryReport(hashtag));
    }

    @Override
    public GetTwitterQueryReport getTwitterQueryReport(String hashtag) {
        return cache.get(hashtag);
    }
}
