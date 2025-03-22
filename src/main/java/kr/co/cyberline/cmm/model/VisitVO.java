package kr.co.cyberline.cmm.model;

import org.apache.ibatis.type.Alias;

/*[통신분쟁]2021-05-11 / Kait-6 / by ihpark / 홈페이지 방문자 통계*/
@Alias("visitVO")
public class VisitVO {

    private String visit_no;
    private String visit_ip; //접속자 ip
    private String visit_time; //접속자 접속시각
    private String visit_refer; //접속자 경로
    private String visit_agent; //접속사 브라우저 정보

    public String getVisit_no() {
        return visit_no;
    }

    public void setVisit_no(String visit_no) {
        this.visit_no = visit_no;
    }

    public String getVisit_ip() {
        return visit_ip;
    }

    public void setVisit_ip(String visit_ip) {
        this.visit_ip = visit_ip;
    }

    public String getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(String visit_time) {
        this.visit_time = visit_time;
    }

    public String getVisit_refer() {
        return visit_refer;
    }

    public void setVisit_refer(String visit_refer) {
        this.visit_refer = visit_refer;
    }

    public String getVisit_agent() {
        return visit_agent;
    }

    public void setVisit_agent(String visit_agent) {
        this.visit_agent = visit_agent;
    }
}
