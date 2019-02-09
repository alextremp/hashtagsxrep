package cat.xarxarepublicana.hashtagsxrep.domain.notice;

public class UserWantToJoinNotice {

    private final String userNickname;

    public UserWantToJoinNotice(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserNickname() {
        return userNickname;
    }
}
