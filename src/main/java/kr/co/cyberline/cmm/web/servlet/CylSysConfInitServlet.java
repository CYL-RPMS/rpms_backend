
package kr.co.cyberline.cmm.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.cyberline.cmm.util.file.CylFileUtil;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.web.code.CylCmmCodeDetailVO;
import kr.co.cyberline.cmm.web.code.CylCmmCodeMap;
import kr.co.cyberline.cmm.web.code.CylCmmCodeVO;
import kr.co.cyberline.cmm.web.code.CylICmmCodeService;
import kr.co.cyberline.cmm.web.hcode.CylHierCodeMap;
import kr.co.cyberline.cmm.web.hcode.CylHierCodeVO;
import kr.co.cyberline.cmm.web.hcode.CylIHierCodeService;
import kr.co.cyberline.cmm.web.hcode.CylIHierDetailCodeService;
import kr.co.cyberline.cmm.web.model.CylWebDefaultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * properties 설정정보 초기화
 * 
 * <pre>
 * kr.co.cyberline.cmm.web.servlet
 * CylSysConfInitServlet.java
 * </pre>
 * 
 * @Author : Chang Ho Seok
 * @Date : 2013. 11. 4.
 * @Version : 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-04		석창호					최초등록
 * 2015-07-30		송치석					propertiesLoad 항목 수정
 * ================================================================
 * </pre>
 */
public class CylSysConfInitServlet extends HttpServlet {
	private WebApplicationContext wac;
	
	;private static Logger logger = LoggerFactory.getLogger(CylSysConfInitServlet.class);
	
	private HashMap<Object, Object> tempMap = null;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			init();

			// alert 메시지 표시할때 백그라운드 한글깨짐 문제로 삽입
			resp.setHeader("Content-Type", "text/html; charset=UTF-8");

			PrintWriter out = resp.getWriter();
			out.println("<script>alert('storage 설정이 초기화되었습니다.');history.back();</script>");
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}

	@Override
	public void init() throws ServletException {
		this.wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		// 공통코드
		initSysCode();
		// 계층 코드
		initHierCode();
	}

	private void initSysCode() {

		logger.info("########################################");
		logger.info("## 시스템 공통코드 목록을 초기화 합니다.");

		Map<Object, CylCmmCodeVO> tempMap = new HashMap<Object, CylCmmCodeVO>();
		CylCmmCodeMap commonCodeMap = CylCmmCodeMap.getInstance();
		CylICmmCodeService cmmCodeService = (CylICmmCodeService) wac.getBean("cylCmmCodeService");

		try {

			CylCmmCodeVO temp = new CylCmmCodeVO();
			CylCmmCodeDetailVO tempCode = new CylCmmCodeDetailVO();
			temp.setUse_at("Y");
			tempCode.setUse_at("Y");
			// 코드 목록 조회
			List<CylCmmCodeVO> codeIdList = cmmCodeService.retrieveCmmCodeTreeList(temp);
			for ( CylCmmCodeVO vo : codeIdList ){
				// 상세 코드
				tempCode.setCode_id(vo.getCode_id());
				tempCode.setPagingEnable(CylWebDefaultVO.PAGING_ENABLE_OFF);
				vo.setCodeDetailList(cmmCodeService.retrieveCmmCodeDetailList(tempCode));
				vo.setCodeDetailMap(cmmCodeService.retrieveCmmCodeDetailMap(tempCode));
				tempMap.put(vo.getCode_id(), vo);
			}

			commonCodeMap.setCommonCode(tempMap);
		} catch (Exception ex) {
			logger.error(ex.toString());
		}

		logger.info("## 시스템 공통코드 목록을 초기화 하였습니다.");
		logger.info("########################################");
	}

	private void initHierCode() {
		String hierCodeImplClass = CylStringUtil.isNotBlank(getInitParameter("hierCodeServiceImplId")) ? getInitParameter("hierCodeServiceImplId") : "cylHierCodeService";
		String hierDetailCodeImplClass = CylStringUtil.isNotBlank(getInitParameter("hierDetailCodeServiceImplId")) ? getInitParameter("hierDetailCodeServiceImplId") : "cylHierDetailCodeService";
		logger.info("########################################");
		logger.info("## 계층 코드 설정값을 초기화 합니다.");

		try {
			logger.info("## init-param name : herCodeServiceImplId = " + hierCodeImplClass);
			logger.info("## init-param name : hierDetailCodeServiceImplId = " + hierDetailCodeImplClass);

			HashMap<Object, List<CylHierCodeVO>> tempMap = null;
			CylHierCodeMap hierCodeMap = CylHierCodeMap.getInstance();

			CylIHierCodeService hierCodeService = (CylIHierCodeService) wac.getBean(hierCodeImplClass);
			CylIHierDetailCodeService hierDetailCodeService = (CylIHierDetailCodeService) wac.getBean(hierDetailCodeImplClass);

			hierCodeMap.setHierCodeImplClass(hierCodeImplClass);
			hierCodeMap.setHierDetailCodeImplClass(hierDetailCodeImplClass);

			if (hierCodeService != null && hierDetailCodeService != null) {
				try {
					String groupId = "";
					List<CylHierCodeVO> detailCodeList = null;
					CylHierCodeVO condCylHierCodeVO = new CylHierCodeVO();

					CylHierCodeVO tempCodeVO = new CylHierCodeVO();
					tempMap = new HashMap<Object, List<CylHierCodeVO>>();
					List<CylHierCodeVO> codeIdList = hierCodeService.selectHierGroupIdList(tempCodeVO);

					Iterator<CylHierCodeVO> codeIdIter = codeIdList.iterator();

					while (codeIdIter.hasNext()) {
						CylHierCodeVO cylHierCodeVO = codeIdIter.next();

						groupId = cylHierCodeVO.getGrp_id();
						condCylHierCodeVO.setGrp_id(groupId);

						detailCodeList = hierDetailCodeService.selectHiercodeDetailList(condCylHierCodeVO);
						tempMap.put(groupId, detailCodeList);

						logger.info("## " + cylHierCodeVO.getGrp_id() + "(" + cylHierCodeVO.getGrp_id_nm() + ") - 상세코드 로딩 갯수 : " + detailCodeList.size() + "개 loaded");
					}

					hierCodeMap.setHierCode(tempMap);
				} catch (Exception e) {
					logger.error(e.toString());
				}
			} else {
				logger.warn("## 계층 코드 구현체 클래스를 로딩할 수 없습니다.");
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
	}
}
