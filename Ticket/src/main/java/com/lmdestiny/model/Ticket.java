package com.lmdestiny.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 *车票预订信息
 * @author wangbin
 *
 */
@Entity
public class Ticket{
	@Id
	//车次号
	private String plate;
	//每个区间,列车经过的区间
	@OneToMany(mappedBy="ticket",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
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

}
