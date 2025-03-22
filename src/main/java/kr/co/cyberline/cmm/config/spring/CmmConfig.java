package kr.co.cyberline.cmm.config.spring;

import egovframework.rte.fdl.cmmn.trace.LeaveaTrace;
import egovframework.rte.fdl.cmmn.trace.handler.DefaultTraceHandler;
import egovframework.rte.fdl.cmmn.trace.handler.TraceHandler;
import egovframework.rte.fdl.cmmn.trace.manager.DefaultTraceHandleManager;
import egovframework.rte.fdl.cmmn.trace.manager.TraceHandlerService;
import kr.co.cyberline.cmm.web.dao.CylMyBatisBase2DAO;
import kr.co.cyberline.cmm.web.dao.CylMyBatisBase3DAO;
import kr.co.cyberline.cmm.web.dao.CylMyBatisBaseDAO;
import kr.co.cyberline.cmm.web.handler.impl.CylJsTreeJsonHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CmmConfig {

    @Bean
    public CylMyBatisBaseDAO baseDAO() {
        return new CylMyBatisBaseDAO();
    }

    @Bean
    public CylMyBatisBase2DAO baseDAO2() {
        return new CylMyBatisBase2DAO();
    }

    @Bean
    public CylMyBatisBase3DAO baseDAO3() {
        return new CylMyBatisBase3DAO();
    }

    @Bean
    public CylJsTreeJsonHandlerImpl defaultTreeHandler() {
        return new CylJsTreeJsonHandlerImpl();
    }

    @Bean
    public LeaveaTrace leaveaTrace() {
        LeaveaTrace leaveaTrace = new LeaveaTrace();
        TraceHandlerService[] traceHandlerServices = new TraceHandlerService[1];
        traceHandlerServices[0] = (traceHandlerService());
        leaveaTrace.setTraceHandlerServices(traceHandlerServices);
        return leaveaTrace;
    }

    @Bean
    public DefaultTraceHandleManager traceHandlerService() {
        DefaultTraceHandleManager manager = new DefaultTraceHandleManager();
        String[] patterns = new String[1];
        patterns[0] = "*";
        manager.setPatterns(patterns);

        TraceHandler[] handlers = new TraceHandler[1];
        handlers[0] = defaultTraceHandler();
        manager.setHandlers(handlers);
        return manager;
    }

    @Bean
    public DefaultTraceHandler defaultTraceHandler() {
        return new DefaultTraceHandler();
    }

    @Bean
    public AntPathMatcher antPathMater() {
        return new AntPathMatcher();
    }
}
