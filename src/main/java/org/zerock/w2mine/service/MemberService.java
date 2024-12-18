package org.zerock.w2mine.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.w2mine.dao.MemberDAO;
import org.zerock.w2mine.domain.MemberVO;
import org.zerock.w2mine.dto.MemberDTO;
import org.zerock.w2mine.util.MapperUtil;

@Log4j2
public enum MemberService {
    INSTANCE;

    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService() {

        dao = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();

    }


    public MemberDTO login(String mid, String mpw)throws Exception {

        MemberVO vo = dao.getWithPassword(mid, mpw);

        MemberDTO memberDTO = modelMapper.map(vo, MemberDTO.class);

        return memberDTO;
    }
}
