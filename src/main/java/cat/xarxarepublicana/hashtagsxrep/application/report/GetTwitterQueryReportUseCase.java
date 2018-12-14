package cat.xarxarepublicana.hashtagsxrep.application.report;

import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorNotFoundException;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.report.Report;
import cat.xarxarepublicana.hashtagsxrep.domain.report.ReportRepository;

public class GetTwitterQueryReportUseCase {

    private final MonitorRepository monitorRepository;
    private final ReportRepository reportRepository;

    public GetTwitterQueryReportUseCase(ReportRepository reportRepository, MonitorRepository monitorRepository) {
        this.reportRepository = reportRepository;
        this.monitorRepository = monitorRepository;
    }

    public GetTwitterQueryReport getTwitterQueryReport(String twitterQuery) {
        Monitor monitor = monitorRepository.findByTwitterQuery(twitterQuery);
        if (monitor == null) {
            throw new MonitorNotFoundException("No hi ha cap monitor per a: " + twitterQuery);
        }
        Report report = reportRepository.loadReport(monitor.getId());

        GetTwitterQueryReport getTwitterQueryReport = new GetTwitterQueryReport(report,monitor);
        return getTwitterQueryReport;
    }

    public static class GetTwitterQueryReport {
        private final Report report;
        private final Monitor monitor;

        public GetTwitterQueryReport(Report report, Monitor monitor) {
            this.report = report;
            this.monitor = monitor;
        }

        public Report getReport() {
            return report;
        }

        public Monitor getMonitor() {
            return monitor;
        }
    }
}
