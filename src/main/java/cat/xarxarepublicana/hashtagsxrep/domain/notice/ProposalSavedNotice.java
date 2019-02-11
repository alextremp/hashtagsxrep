package cat.xarxarepublicana.hashtagsxrep.domain.notice;

public class ProposalSavedNotice implements Notice {

    private final String authorNickname;
    private final String hashtag;
    private final String lastModeratorNickname;

    public ProposalSavedNotice(String authorNickname, String hashtag, String lastModeratorNickname) {
        this.authorNickname = authorNickname;
        this.hashtag = hashtag;
        this.lastModeratorNickname = lastModeratorNickname;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }

    public String getHashtag() {
        return hashtag;
    }

    public String getLastModeratorNickname() {
        return lastModeratorNickname;
    }
}
