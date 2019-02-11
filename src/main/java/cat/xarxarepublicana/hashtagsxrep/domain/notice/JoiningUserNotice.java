package cat.xarxarepublicana.hashtagsxrep.domain.notice;

public class JoiningUserNotice implements Notice {

    private final String userNickname;
    private final String group;

    public JoiningUserNotice(String userNickname, String group) {
        this.userNickname = userNickname;
        this.group = group;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public String getGroup() {
        return group;
    }
}
