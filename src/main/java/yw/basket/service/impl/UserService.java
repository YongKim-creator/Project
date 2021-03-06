package yw.basket.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yw.basket.dto.UserDTO;
import yw.basket.mapper.IUserMapper;
import yw.basket.service.IUserService;

@Slf4j
@Service("UserService")
public class UserService implements IUserService {

    private final IUserMapper userMapper;  // 무조건 이거랑 autowired가 있어야함 (service에서 mapper쓰려면) // 변수선언

    @Autowired
    public UserService(IUserMapper userMapper) {
        this.userMapper = userMapper;
    } // 매퍼클래스를 위에 private final 변수에 넣어준다(위에껀 null과 같은상태인데 이 값을 넣어줌으로써 mapper사용)

    @Override
    public int loginCheck(UserDTO userDTO) throws Exception {
        return userMapper.loginCheck(userDTO);
    }
// 1. userdto로 부터 받아온 정보를 DB에 저장한다
    //회원가입
    @Override
    public int signUp(UserDTO userDTO) throws Exception {
        return userMapper.insertUserInfo(userDTO);
    }

    @Override
    public int main(UserDTO userDTO) throws Exception {
        return userMapper.getUserLoginCheck(userDTO);
    }


    @Override
    public UserDTO loginProc(UserDTO userDTO) throws Exception {
        return userMapper.loginProc(userDTO);
    }

    //테스트
    @Override
    public String test() throws Exception {
        return userMapper.test();
    }
}
