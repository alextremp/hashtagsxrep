package cat.xarxarepublicana.hashtagsxrep.domain.notice;

public interface NoticeRepository {

  void publishPollClosedNotice(PollClosedNotice notice);

  void publishProposalSavedNotice(ProposalSavedNotice notice);
}
