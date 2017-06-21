package com.lmdestiny.service;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;	

import com.lmdestiny.model.Ticket;
import com.lmdestiny.util.TransResult;
@WebService(serviceName="ticketService",targetNamespace="http://lmdestiny",name="ticketService")
public interface TicketService{

	// 查找车票(根据车次查询)
	@WebMethod
	/*@WebResult(name="return",targetNamespace="http://lmdestiny")*/
	public List<Ticket> findTicket1(String plate, Date startT,
			Date endT, String startC, String endC);

	// 查找车票(根据站点)
	@WebMethod
	public List<Ticket> findTicket2(String startC, String endC,
			Date startT, Date endT);

	// 购买车票
	@WebMethod
	public TransResult pay(String plate, String type, Long count, String startC,
			String endC);

	// 查找所有车票
	@WebMethod
	public List<Ticket> findAll();
	// 查看车票信息
	@WebMethod
	public Ticket find(String plate);
	@WebMethod
	public TransResult addTicket(Ticket ticket);
}
