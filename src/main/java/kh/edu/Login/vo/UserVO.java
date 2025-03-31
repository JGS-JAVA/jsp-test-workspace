package kh.edu.Login.vo;

// dto  -> 디비와 연결된 변수명
// vo  -> 디비와 관련없는 변수명

public class UserVO {
    private String userId;
    private String userPw;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

}
