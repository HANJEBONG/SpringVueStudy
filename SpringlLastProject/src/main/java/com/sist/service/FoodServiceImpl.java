package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Service
public class FoodServiceImpl implements FoodService{
    @Autowired
    private FoodDAO fDao;

	@Override
	public List<FoodVO> foodHitTop5() {
		// TODO Auto-generated method stub
		return fDao.foodHitTop5();
	}

	@Override
	public List<FoodVO> foodListData(int start, int end) {
		// TODO Auto-generated method stub
		return fDao.foodListData(start, end);
	}

	@Override
	public int foodTotalData() {
		// TODO Auto-generated method stub
		return fDao.foodTotalData();
	}

	@Override
	public FoodVO foodDetailData(int fno) {
		// TODO Auto-generated method stub
		return fDao.foodDetailData(fno);
	}

	@Override
	public FoodVO foodInfoData(int fno) {
		// TODO Auto-generated method stub
		return fDao.foodInfoData(fno);
	}
  
  
}