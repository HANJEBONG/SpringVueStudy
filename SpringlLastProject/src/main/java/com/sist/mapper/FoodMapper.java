package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface FoodMapper {
  @Select("SELECT fno,name,poster,address,rownum "
		 +"FROM (SELECT fno,name,poster,address "
		 +"FROM food_house ORDER BY hit DESC) "
		 +"WHERE rownum<=5")
  public List<FoodVO> foodHitTop5();
  
  @Select("SELECT fno,name,phone,poster,address,num "
  		+ "FROM (SELECT fno,name,phone,poster,address,rownum as num "
  		+ "FROM (SELECT /*+ INDEX_ASC(food_house fh_fno_pk)*/fno,name,phone,poster,address "
  		+ "FROM food_house)) "
  		+ "WHERE num BETWEEN #{start} AND #{end}")
  public List<FoodVO> foodListData(@Param("start")int start,@Param("end")int end);
  
  @Select("SELECT CEIL(COUNT(*)/12.0) FROM food_house")
  public int foodTotalData();
  
  @Update("UPDATE food_house SET "
  		+ "hit=hit+1 "
  		+ "WHERE fno=#{fno}")
  public void hitIncrement(int fno);
  
  @Select("SELECT * FROM food_house "
  		+ "WHERE fno=#{fno}")
  public FoodVO foodDetailData(int fno);
  
}