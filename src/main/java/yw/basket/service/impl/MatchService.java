package yw.basket.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yw.basket.dto.BoardDTO;
import yw.basket.dto.MatchDTO;
import yw.basket.dto.RequestDTO;
import yw.basket.mapper.IMatchMapper;
import yw.basket.mapper.IUserMapper;
import yw.basket.service.IMatchService;

import java.util.List;

@Slf4j
@Service("MatchService")

public class MatchService implements IMatchService {

    private final IMatchMapper matchMapper;

    @Autowired
    public MatchService(IMatchMapper matchMapper) {
        this.matchMapper = matchMapper;
    }

    @Override
    public int matchRegSave(MatchDTO matchDTO) throws Exception {
        return matchMapper.matchRegSave(matchDTO);
    }

    @Override
    public List<MatchDTO> matchDTOList(MatchDTO matchDTO) throws Exception {
        return matchMapper.matchDTOList(matchDTO);
    }

    //매치 상세
    @Override
    public MatchDTO matchDetail(MatchDTO matchDTO) throws Exception {
        return matchMapper.matchDetail(matchDTO);
    }

    @Override
    public int matchReqSave(MatchDTO matchDTO) throws Exception {
        return matchMapper.matchReqSave(matchDTO);
    }


    @Override
    public List<MatchDTO> reqList(MatchDTO matchDTO) throws Exception {
        return matchMapper.reqList(matchDTO);
    }

    //나의 활동
    @Override
    public List<MatchDTO> my(MatchDTO matchDTO) throws Exception {
        return matchMapper.my(matchDTO);
    }


}
