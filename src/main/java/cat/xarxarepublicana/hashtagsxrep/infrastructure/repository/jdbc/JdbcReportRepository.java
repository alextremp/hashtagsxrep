package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.report.*;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.ReportMapper;

public class JdbcReportRepository implements ReportRepository {

    private final ReportMapper reportMapper;

    public JdbcReportRepository(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    @Override
    public Report loadReport(String monitorId) {
        TweetedContentResume tweetedContentResume = reportMapper.selectTweetedContentResume(monitorId);
        InteractorResume interactorResume = reportMapper.selectInteractorResume(monitorId);
        Integer taggersCount = reportMapper.selectTaggersCount(monitorId);
        UserContentResume userContentResume = reportMapper.selectUserContentResume(monitorId);

        Report report = new Report(tweetedContentResume, interactorResume, taggersCount, userContentResume);
        return report;
    }
}
