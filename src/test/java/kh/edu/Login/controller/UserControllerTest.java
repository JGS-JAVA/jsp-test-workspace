package kh.edu.Login.controller;

// 파일명이나 폴더명에 test 를 직접 작성 X
// 다른 코딩 언어에서는 test 란 명칭이 파일이나 펄더에 작성되어 있으면
// test 코드로 코딩 읽음
// example exam tutorial 같은 명칭 사용하기

import kh.edu.Login.LoginApplication;
import kh.edu.Login.service.UserService;
import kh.edu.Login.vo.UserVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

// 웹 기반 테스트 진행하기 위한 상태
// 많은 파일 중에서 UserController 만 테스트 진행예정
@WebMvcTest(controllers = UserController.class)

// 메인 실행 클래스 위치설정
// 메인 클래스로 접근해서 테스트 진행
@ContextConfiguration(classes = LoginApplication.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() throws Exception {
        // 추가적 옵션 설정시 사용
    }

    // 로그인 성공 테스트
    // 디비에 실질적 접근 못할때 사용하는 테스트 기법
    // Controller 에 작성한 메서드 명칭과 테스트 메서드 기능명칭이 달라도 된다
    @Test
    public void loginSuccessTest() throws Exception {
        UserVO dummyUser = new UserVO();
        // 디비에 연결 안돼서 연결된다는 가정하에 아이디 비번 설정
        dummyUser.setUserId("user_lee");
        dummyUser.setUserPw("1234");

        when(userService.login(any(UserVO.class))).thenReturn(dummyUser);
        // 아이디와 비번 일치시
        mvc.perform(
                // controller post 에 작성한 api endpoint 주소로 전송
                post("login.do")
                        // 아이디 비번 설정해서 전송
                        // .param 은 html 에서 input 태그에 값을 넣은 상태
                        .param("userId","user_lee")
                        // id 나 name 명칭이 userPw 인 인풋 태그에 1234 를 입력한 상태
                        .param("userPw","1234"))
                // http 응답 코드가 isOk()=200 인 상태일때
                .andExpect(status().isOk())
                // view() = html 파일에서 파일명이 loginSuccess 인 파일로 이동하기
                .andExpect(view().name("loginSuccess"));
    }
}
