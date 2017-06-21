package com.lmdestiny.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmdestiny.dao.TicketDaoImpl;
import com.lmdestiny.model.Ticket;
import com.lmdestiny.model.TicketEntity;
import com.lmdestiny.service.TicketService;
import com.lmdestiny.util.TransResult;

@Service("ticketService")
@Transactional
@WebService(serviceName="ticketService",targetNamespace="http://lmdestiny")
public class TicketServiceImpl implements TicketService{
	@Autowired
	private TicketDaoImpl ticketDao;
	//车票添加服务
	@WebMethod
	@Override
	public TransResult addTicket(Ticket ticket) {
		try {
			Ticket ticket2 = new Ticket();
			ticket2.setPlate(ticket.getPlate());
			List<TicketEntity> entitys = ticket.getEntitys();
			Iterator<TicketEntity> iterator = entitys.iterator();
			while(iterator.hasNext()) {
				TicketEntity e = iterator.next();
				e.setTicket(ticket2);
				ticket2.getEntitys().add(e);
			}
			ticketDao.merge(ticket2);
			return TransResult.ok();
		}catch(Exception e) {
			e.printStackTrace();
			return TransResult.build(400,"添加失败");
		}
	}
	//查找车票(根据车次查询)
	@WebMethod
	@Override
	public List<Ticket> findTicket1(
			String plate,Date startT,Date endT,String startC,String endC) {
		Ticket ticket = null;
		List<Ticket> tickets = new ArrayList<>();
		if(!plate.equals("")) {
			ticket = ticketDao.find(plate);
			if(ticket==null) return tickets;
			TicketEntity t1 = ticket.getEntitys().get(0);
			TicketEntity t2 = ticket.getEntitys().get(ticket.getEntitys().size()-1);
			if(t1.getStartT().compareTo(startT)>0 &&t2.getEndT().compareTo(endT)<0
					&&t1.getStartC().equals(startC)&&t2.getEndC().equals(endC)) {
				tickets.add(ticket);
			}
			return tickets;
		}else {
			List<Ticket> all = ticketDao.find();
			if(all ==null || all.size()==0) return tickets;
			List<Ticket> collect = all.stream().filter((x)->{
				TicketEntity t1 = x.getEntitys().get(0);
				TicketEntity t2 = x.getEntitys().get(x.getEntitys().size()-1);
				if(t1.getStartT().compareTo(startT)>0 &&t2.getEndT().compareTo(endT)<0
						&&t1.getStartC().equals(startC)&&t2.getEndC().equals(endC)) {
					return true;
				}
				return false;
			}).collect(Collectors.toList());
			return collect;
		}
	}
	
	//查找车票(根据站点)
	@WebMethod
	@Override
	public List<Ticket> findTicket2(String startC,String endC,
			Date startT,Date endT){
		List<Ticket> all = ticketDao.find();
		List<Ticket> ts = new ArrayList<>();
		for(Ticket t:all) {
			List<TicketEntity> collect = t.getEntitys().stream().filter((x)->{
				if(x.getStartT().compareTo(startT)>=0) {
					return true;
				}
				return false;
			}).filter((x)->{
				if(x.getEndT().compareTo(endT)<=0)
					return true;
				return false;
			}).collect(Collectors.toList());
			//是否包含城市
			boolean stc = collect.stream().map((x)->x.getStartC()).collect(Collectors.toList()).contains(startC);
			//是否包含结束城市
			boolean edc = collect.stream().map((x)->x.getEndC()).collect(Collectors.toList()).contains(endC);
			if(collect !=null &&stc&&edc) {
				ts.add(t);
			}
		}
		return ts;
	}

	//购买车票
	@WebMethod
	@Override
	public TransResult pay(String plate,String type,Long count,String startC,String endC) {
		Ticket ticket = ticketDao.find(plate);
		List<TicketEntity> entity = ticket.getEntitys();
		List<TicketEntity> entitys = new ArrayList<TicketEntity>();
		int sc =0;
		int ec = 0;
		for(int i=0;i<entitys.size();i++) {
			if(entitys.get(i).getStartC().equals(startC)) {
				sc = i;
				break;
			}
		}
		
		for(int i=0;i<entitys.size();i++) {
			if(entitys.get(i).getEndC().equals(endC)) {
					ec =i;
					break;
				}
		}
		for(int i=sc;i<=ec ;i++) {
			Long long1 = entitys.get(i).getCountT().get(type);
			if(long1-count<0) {
				return TransResult.build(400, "票数不足");
			}
			Map<String,Long> countT = entitys.get(i).getCountT();
			countT.put(type, long1-count);
			ticket.getEntitys().get(i).setCountT(countT);
		}
		ticketDao.merge(ticket);
		return TransResult.ok();
	}

	//查找所有车票
	@WebMethod
	@Override
	public List<Ticket> findAll(){
		return ticketDao.find();
	}
	//查看车票信息
	@WebMethod
	@Override
	public Ticket find(String plate) {
		return ticketDao.find(plate);
	}
}
