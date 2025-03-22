package kr.co.cyberline.cmm.service;

import kr.co.cyberline.cmm.config.*;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

@Service
public class DatabaseService {

    @Value("${jdbc.type}")
    private String dbType1;

    @Value("${jdbc.type2}")
    private String dbType2;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    // 데이터베이스 타입을 전환하는 메소드
    public void switchDatabase(String dbType) {
        // dbType이 유효한지 확인
        if (dbType != null && dbType.equals("2")) {
            DatabaseContextHolder.setDatabaseType(dbType2);
            reloadMappers(dbType2);
        } else {
            // 기본값으로 설정
            DatabaseContextHolder.setDatabaseType(dbType1);
            reloadMappers(dbType1);
        }
    }

    // 매퍼 파일을 다시 로드하는 메소드
    private void reloadMappers(String dbType) {
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            String mapperLocation = "classpath:kr/co/cyberline/mapper/*/" + dbType + "/**/*.xml";
            Resource[] resources = resolver.getResources(mapperLocation);

            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
            configuration.getTypeAliasRegistry().registerAlias("paramMap", HashMap.class); // 별칭 추가

            for (Resource resource : resources) {
                try {
                    XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(
                            resource.getInputStream(),
                            configuration,
                            resource.toString(),
                            configuration.getSqlFragments()
                    );
                    xmlMapperBuilder.parse();
                } catch (Exception e) {
                    throw new RuntimeException("매퍼 파일 파싱 중 오류 발생: " + resource, e);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("매퍼 파일 로딩 중 오류 발생", e);
        }
    }

    // 실제 사용 예시 메소드
    public void processWithDatabase(String dbType, Runnable operation) {
        try {
            // DB 타입 설정
            switchDatabase(dbType);

            // 작업 수행
            operation.run();
        } finally {
            // ThreadLocal 정리
            DatabaseContextHolder.clear();
        }
    }
}