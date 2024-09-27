package com.sist.web;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;

@RestController
public class FoodRestController {
	private FoodService fService;
	
	@Autowired
	public FoodRestController(FoodService service) {
		this.fService=service;
	}
	
	@GetMapping(value="food/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_list(int page) throws Exception{
		int curpage=page;
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		
		List<FoodVO> list=fService.foodListData(start, end);
		int totalpage=fService.foodTotalData();
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;
		Map map=new HashMap();
		map.put("list", list);
		map.put("curpage", curpage);
		map.put("totalpage", totalpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		
		return json;
	}
}
