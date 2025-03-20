package kr.co.cyberline.service.impl;

import kr.co.cyberline.mapper.CommMapper;
import kr.co.cyberline.service.CommService;
import kr.co.cyberline.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("commService")
public class CommServiceImpl implements CommService {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private CommMapper commMapper;

    @Override
    public Object commonApi(Map params) {
        return databaseService.processWithDatabase((String)params.get("dbType"), () -> {
            try {
                // 이 블록 내부에서는 선택된 데이터베이스와 해당 매퍼가 사용됨
                return commMapper.commonApi(params);
            } catch (Exception e) {
                throw new RuntimeException("commonApi 실행 중 오류 발생", e);
            }
        });
    }
}
