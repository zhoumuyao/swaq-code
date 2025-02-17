package com.example.service.Impl;

import com.example.entity.BiologicalCase;
import org.apache.commons.lang3.StringUtils;
import com.example.mapper.CaseMapper;
import com.example.service.AuthorizeService;
import com.example.service.CaseService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class CaseServiceImpl implements CaseService {

    @Resource
    CaseMapper caseMapper;

    @Override
    public int createCase(BiologicalCase biologicalCase) {
        if(biologicalCase == null){
            return -1;
        }
        caseMapper.createCase(biologicalCase);
        return biologicalCase.getId();
    }

    @Override
    public Boolean deleteCase(int id) {
        return caseMapper.deleteCase(id);
    }

    @Override
    public BiologicalCase selectCase(int id) {
        if(id < 0){
            return null;
        }
        return caseMapper.selectCase(id);
    }

    @Override
    public List<BiologicalCase> searchAllCase() {
        return caseMapper.searchAllCase();
    }


}
