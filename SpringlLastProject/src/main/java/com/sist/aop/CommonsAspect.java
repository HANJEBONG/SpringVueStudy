package com.sist.aop;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sist.vo.*;
import com.sist.service.*;
import java.util.*;
@Aspect
@Component
public class CommonsAspect {
	@Autowired
	private FoodService fService;
	
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void footerCookieSend() {
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		Cookie[] cookies=request.getCookies();
		List<FoodVO> list=new ArrayList<FoodVO>();
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("food_")) {
					String fno=cookies[i].getValue();
					FoodVO vo=fService.foodInfoData(Integer.parseInt(fno));
					list.add(vo);
				}
			}
		}
	}
}