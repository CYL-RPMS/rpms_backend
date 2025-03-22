package kr.co.cyberline.cmm.service.impl;

import kr.co.cyberline.cmm.service.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(value = "CommService")
public class CommServiceImpl implements CommService{

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private SqlSession sqlSession;

    @Override
    public Object commonApi(Map paramMap) {

        final Object[] result = new Object[1];

        databaseService.processWithDatabase((String)paramMap.get("dbType"), () -> {

            String nameSpace = (String)paramMap.get("namespace");
            String queryId = (String)paramMap.get("queryid");
            String command = (String)paramMap.get("command");

            if (nameSpace == null || queryId == null) {
                throw new IllegalArgumentException("NAME_SPACE와 METHOD_ID는 필수 파라미터입니다.");
            }

            String mapperId = nameSpace + "." + queryId;

            switch (command) {
                case "select":
                    result[0] = sqlSession.selectList(mapperId, paramMap);
                    break;
                case "selectOne":
                    result[0] = sqlSession.selectOne(mapperId, paramMap);
                    break;
                case "update":
                    result[0] = sqlSession.update(mapperId, paramMap);
                    break;
                case "insert":
                    result[0] = sqlSession.insert(mapperId, paramMap);
                    break;
                case "delete":
                    result[0] = sqlSession.delete(mapperId, paramMap);
                    break;
                default:
                    throw new IllegalArgumentException("지원하지 않는 command 값입니다: " + command);
            }
        });

        // 작업 결과 반환
        return result[0];
    }
}