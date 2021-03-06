package yw.basket.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import yw.basket.dto.UserDTO;
import yw.basket.service.IUserService;
import yw.basket.util.CmmUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class UserController {

    //서비스 객체 가져오기
   @Resource(name = "UserService")
    private IUserService userService;

    /**
     * @param : N/A
     * @return : N/A
     * @title : 메인 시작 URL
     */
    /*@GetMapping(value = "/")
    public String start(HttpSession session) {
        session.setAttribute("userInfo", "Test");
        return "redirect:/login";
    }*/

    @GetMapping(value = "/")
    public String title() {
        return "/login";
    }

    /**
     * @param : N/A
     * @return : login
     * @title : 로그인 페이지 이동
     */
    @GetMapping(value = "/login")
    public String login() {
        return "/user/login";
    }


    /**
     * @param : UserDTO
     * @return : login
     * @title : 로그인 체크
     */
    @PostMapping(value = "/loginCheck")
    @ResponseBody
    public int loginCheck(UserDTO userDTO, HttpSession session) throws Exception {

        log.info("userId :: " + userDTO.getUserId());
        log.info("userPw :: " + userDTO.getUserPw());
        session.setAttribute("userInfo", "test");

        return userService.loginCheck(userDTO);
    }

    /**
     * @param : UserDTO
     * @return : login
     * @title : 로그인
     */
    @PostMapping(value = "/loginProc")
    @ResponseBody
    public UserDTO loginProc(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        UserDTO userDTO = new UserDTO();
        log.info("Service Start!");

        userDTO.setUserId(CmmUtil.nvl((String) request.getParameter("userId")));
        userDTO.setUserPw(CmmUtil.nvl((String) request.getParameter("userPw")));

        log.info("userId :: " + userDTO.getUserId());
        log.info("userPw :: " + userDTO.getUserPw());


        log.info("Service : ");
        UserDTO user = userService.loginProc(userDTO);
        log.info("Service End : ");

        // 로그인 성공 시
        if (user != null) {
            session.setAttribute("user", user);  // 로그인 성공시에만 세션을 담는다
        }

        return user;
    }

    /**
     * @param : UserDTO
     * @return :
     * @title : 회원가입 페이지
     */
    @RequestMapping(value = "/userReg")
    public String userReg() throws Exception {
        return "user/reg";
    }

    /**
     * @param : UserDTO
     * @return :
     * @title : 회원가입
     */
    @PostMapping(value = "/signUp")
    @ResponseBody // ajax사용하기 위한 어노테이션
    public int signUp(UserDTO userDTO, Model model) throws Exception {
        log.info("userId :: " + userDTO.getUserId());
        log.info("userPw :: " + userDTO.getUserPw());

        return userService.signUp(userDTO);
    }

    //비밀번호 변경 화면
    @RequestMapping(value = "/updatePw")
    public String updatePw(Model model, HttpSession session) throws Exception {
        UserDTO user = (UserDTO) session.getAttribute("user");
        UserDTO userinfo = userService.getUserInfo(user);

        model.addAttribute("userinfo", userinfo);
        return "/user/updatePw";
    }

    //비밀번호 수정
    @PostMapping(value = "/updatePwSave")
    @ResponseBody
    public int updatePwSave(UserDTO userDTO, HttpSession session) throws Exception {

        UserDTO user = (UserDTO) session.getAttribute("user");

        userDTO.setUserSeq(user.getUserSeq());
        return userService.updatePwSave(userDTO);
    }


    //회원 정보 수정
    @GetMapping(value = "/memberModify")
    public String memberModify(Model model, HttpSession session) throws Exception {
        UserDTO user = (UserDTO) session.getAttribute("user");
        UserDTO userinfo = userService.getUserInfo(user);

        model.addAttribute("userinfo", userinfo);

        return "/user/memberModify";
    }

    //회원 정보 수정
    @PostMapping(value = "/memberModifySave")
    @ResponseBody
    public int memberModifySave(HttpSession session, Model model, HttpServletRequest request, @RequestParam Map<String, String> map) throws Exception {

        UserDTO user = (UserDTO) session.getAttribute("user");

        //session을 통해서 user에 값을 받고  여기 user에는 세션에서 받은 id와 seq가 있따
        //getUserInfo 메소드에 user를 파라미터로 보내고 받아온 결과값을 userDTO에 담는다
        String userName = CmmUtil.nvl(map.get("userName"));
        String userEmail = CmmUtil.nvl(map.get("userEmail"));
        String userNickname = CmmUtil.nvl(map.get("userNickname"));
        String userGender = CmmUtil.nvl(map.get("userGender"));
        String userLevel = CmmUtil.nvl(map.get("userLevel"));
        String userPlayer = CmmUtil.nvl(map.get("userPlayer"));

        UserDTO userDTO = new UserDTO();

        userDTO.setUserName(userName);
        userDTO.setUserEmail(userEmail);
        userDTO.setUserNickname(userNickname);
        userDTO.setUserGender(userGender);
        userDTO.setUserLevel(userLevel);
        userDTO.setUserPlayer(userPlayer);

        userDTO.setUserSeq(user.getUserSeq());

        return userService.memberModifySave(userDTO);

    }
    //마이페이지 이동
    @RequestMapping(value = "/mypage")
    public String usermypage(UserDTO userDTO, Model model, HttpSession session) throws Exception {

        UserDTO user = (UserDTO) session.getAttribute("user");
        UserDTO userinfo = userService.getUserInfo(user);

        //이미지 가져오기
        String word = ApiUtil.search(userinfo.getUserPlayer());
        model.addAttribute("image_link", word);

        model.addAttribute("userinfo", userinfo);
        return "/user/mypage";
    }

    //아이디 중복체크
    @PostMapping("/idCheck")
    @ResponseBody
    public String idCheck(String userId) throws Exception {

      log.info("memberIdChk() 진입");
        int result = userService.idCheck(userId);
        log.info("결과값 = " + result);

        if(result != 0) {
            return "fail";	// 중복 아이디가 존재

        } else {
            return "success";	// 중복 아이디 x
        }

    }

    //회원 탈퇴
    @PostMapping(value = "/memberOut")
    @ResponseBody
    public int memberOut(UserDTO userDTO, HttpSession session) throws Exception {

        UserDTO user = (UserDTO) session.getAttribute("user");
        userDTO.setUserSeq(user.getUserSeq());

        return userService.memberOut(userDTO);
    }

    //이메일 발송
    @GetMapping(value = "/emailCheck")
    @ResponseBody
    public int emailCheck(HttpServletRequest request) throws Exception {
        log.info("controller.emailCheck start");

        String userId = request.getParameter("userId");
        String userEmail = request.getParameter("userEmail");
        log.info("userId : " + userId);
        log.info("userEmail : " + userEmail);

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        userDTO.setUserEmail(userEmail);

        int res = userService.emailCheck(userDTO);
        log.info("이메일체크결과 : " + res);
        if(res > 0) {
            String newPw = UUID.randomUUID().toString();
            userDTO.setUserPwNew(EncryptUtil.encHashSHA256(newPw));
            int uptRes = userService.updateOncePw(userDTO);
            log.info("임시비밀번호 변경결과 : " + uptRes);
            if(uptRes > 0) {
                MailUtil.sendAuthEmail(userEmail, "Basket Life 임시 비밀번호 발급", newPw);
            } else {
                res = 0;
            }
        }

        return res;
    }



}



