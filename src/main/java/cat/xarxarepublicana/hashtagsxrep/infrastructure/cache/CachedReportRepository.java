package cat.xarxarepublicana.hashtagsxrep.infrastructure.cache;

import cat.xarxarepublicana.hashtagsxrep.domain.report.Report;
import cat.xarxarepublicana.hashtagsxrep.domain.report.ReportRepository;
import com.github.benmanes.caffeine.cache.LoadingCache;

public class CachedReportRepository implements ReportRepository {

    private final ReportRepository reportRepository;
    private final LoadingCache<String, Report> reportCache;

    public CachedReportRepository(ReportRepository reportRepository, LoadingCache<String, Report> reportCache) {
        this.reportRepository = reportRepository;
        this.reportCache = reportCache;
    }

    @Override
    public Report loadReport(String monitorId) {
        return reportCache.get(monitorId);
    }
}
