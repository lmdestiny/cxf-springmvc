package com.lmdestiny.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class TicketEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(cascade=CascadeType.ALL)
	private Ticket ticket;
	//车票级别和对应的总数
	@ElementCollection(fetch=FetchType.EAGER)
	private Map<String,Long> countT = new HashMap<>();
	//所经过的城市
	private String startC;
	private String endC;
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Date startT;
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Date endT;
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	@XmlTransient
	public Ticket getTicket(){
		return ticket;
	}
	public void setTicket(Ticket ticket){
		this.ticket = ticket;
	}
	
	public String getStartC(){
		return startC;
	}
	public void setStartC(String startC){
		this.startC = startC;
	}
	public String getEndC(){
		return endC;
	}
	public void setEndC(String endC){
		this.endC = endC;
	}
	public Map<String, Long> getCountT(){
		return countT;
	}
	public void setCountT(Map<String, Long> countT){
		this.countT = countT;
	}
	public Date getStartT(){
		return startT;
	}
	public void setStartT(Date startT){
		this.startT = startT;
	}
	public Date getEndT(){
		return endT;
	}
	public void setEndT(Date endT){
		this.endT = endT;
	}


}
