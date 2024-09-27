package com.sist.vo;

import lombok.Data;

@Data
public class FoodVO {
  private int fno,jjimcount,likecount,hit;
  private String name,type,address,theme,poster,images,time,parking,
          content,rdays,phone;
  private double score;
}