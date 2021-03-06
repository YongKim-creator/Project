package yw.basket.service;

import yw.basket.dto.BoardDTO;
import yw.basket.dto.MatchDTO;
import yw.basket.dto.RequestDTO;
import yw.basket.dto.UserDTO;

import java.util.List;

public interface IMatchService {


    //매치 저장
    int matchRegSave(MatchDTO matchDTO) throws Exception;

    //매치 불러오기
    List<MatchDTO> matchDTOList(MatchDTO matchDTO) throws Exception;

    //매치 상세
    MatchDTO matchDetail(MatchDTO matchDTO) throws Exception;

    int matchReqSave(MatchDTO matchDTO) throws Exception;

    //신청 리스트
    List<MatchDTO>  reqList(MatchDTO matchDTO) throws Exception;

    //나의 활동
    List<MatchDTO> my(MatchDTO matchDTO) throws Exception;

}
