package com.lmdestiny.service;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import com.lmdestiny.model.Ticket;
import com.lmdestiny.util.TransResult;
@WebService(targetNamespace="http://lmdestiny")
public interface TicketService{

	// 查找车票(根据车次查询)
	public List<Ticket> findTicket1(String plate, Date startT1,
			Date endT1, String startC, String endC);

	// 查找车票(根据站点)
	public List<Ticket> findTicket2(String startC, String endC,
			Date startT1, Date endT1);

	// 购买车票
	public TransResult pay(String plate, String type, Long count, String startC,
			String endC);

	// 查找所有车票
	public List<Ticket> findAll();
	// 查看车票信息
	public Ticket find(String plate);

	public TransResult addTicket(Ticket ticket);
}
