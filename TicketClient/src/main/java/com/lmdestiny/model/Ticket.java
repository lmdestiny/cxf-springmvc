package com.lmdestiny.model;

import java.util.ArrayList;
import java.util.List;


/**
 *车票预订信息
 * @author wangbin
 *
 */
public class Ticket{
	//车次号
	private String plate;
	//每个区间,列车经过的区间
	private List<TicketEntity> entitys = new ArrayList<>();
	
	public List<TicketEntity> getEntitys(){
		return entitys;
	}
	public void setEntitys(List<TicketEntity> entitys){
		this.entitys = entitys;
	}
	public String getPlate(){
		return plate;
	}
	public void setPlate(String plate){
		this.plate = plate;
	}
	@Override
	public String toString(){
		return "Ticket [plate=" + plate + ", entitys=" + entitys + "]";
	}
}
