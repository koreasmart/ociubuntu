package ksmart.oci.interceptor;

import java.util.Set;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ksmart.oci.mapper.MemberMapper;


@Component
public class CommonInterceptor implements HandlerInterceptor{
	
	private static final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);
	
	private final MemberMapper memberMapper;
	
	public CommonInterceptor (MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// parameter key SET
		Set<String> paramKeySet =  request.getParameterMap().keySet();
		
		//memberId=id001, memberPw=pw001 ...........
		StringJoiner param = new StringJoiner(", ");
		
		for(String key : paramKeySet) {
			//memberId: id001, memberPw: pw001 ...........
			param.add(key + ": " + request.getParameter(key));
		}
		
		// TODO 주소요청시에 어떤 사용자 ip가 어떤 주소를 요청하고 파라미터 무엇인지 로그 작업
		log.info("ACCESS INFO ==================================================");
		log.info("PORT 		::::::::	{}", request.getLocalPort());
		log.info("ServerName		::::::::	{}", request.getServerName());
		log.info("Method 		::::::::	{}", request.getMethod());
		log.info("URI 		::::::::	{}", request.getRequestURI());
		log.info("CLIENT IP		::::::::	{}", request.getRemoteAddr());
		log.info("parameter		::::::::	{}", param);
		log.info("ACCESS INFO ==================================================");
		
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		/*
		Cookie cookie = WebUtils.getCookie(request, "loginKeepId");
		if(cookie != null) {
			String loginId = cookie.getValue();
			loginId = stringEncyption.decrypt(loginId);
			HttpSession session = request.getSession();
			LoginInfo loginInfo = (LoginInfo) session.getAttribute("S_MEMBER_INFO");
			String requestURI = request.getRequestURI();
			if(loginInfo == null && requestURI.indexOf("logout") < 0) {
				loginInfo = memberMapper.getLoginInfo(loginId);
				session.setAttribute("S_MEMBER_INFO", loginInfo);
			}
			log.info("cookie 여부에 따른 common loginInfo : {}", loginInfo);
		}
		*/
	}
}
