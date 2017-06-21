package com.lmdestiny.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lmdestiny.model.Ticket;
import com.lmdestiny.service.TicketService;
import com.lmdestiny.util.TransResult;

@Controller
@RequestMapping("/ticket")
public class TicketController{
	@Autowired
	private TicketService ticketService;
	@RequestMapping("/toIndexPage")
	public String toIndexPage() {
		return "index";
	}
	@RequestMapping("/list")
	public String list(Model model) {
		List<Ticket> findAll = ticketService.findAll();
		model.addAttribute("list",findAll);
		return "list";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "add";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public TransResult add(Ticket ticket) {
		System.out.println(ticket.toString());
		TransResult addTicket = ticketService.addTicket(ticket);
		return addTicket;
	}
	
	@RequestMapping("/pay/{plate}")
	public String topay(@PathVariable String plate,Model model) {
		model.addAttribute("t",ticketService.find(plate));
		return "pay";
	}
	
	@RequestMapping("/topay")
	@ResponseBody
	public TransResult pay(String plate,String type,Long count,String startC,String endC) {
		System.out.println( plate +""+type+""+ count+""+ startC+""+ endC);
		TransResult pay = ticketService.pay(plate, type, count, startC, endC);
		return pay;
	}
	//根据车次检索
	@RequestMapping("/findBycc")
	public String find1(String plate,String startT, String endT,
			String startC,String endC,Model model) throws ParseException {
		if("".equals(startT)) startT ="1999-01-01T01:01";
		if("".equals(endT)) endT ="3000-01-01T01:01";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date startT1 = simpleDateFormat.parse(startT);
		Date endT1 = simpleDateFormat.parse(endT);
		System.out.println(startT1);
		System.out.println(endT1);
		List<Ticket> findTicket = ticketService.findTicket1(plate, startT1, endT1, startC, endC);
		model.addAttribute("list",findTicket);
		return "list";
	} 
	
	//根据站点检索
	@RequestMapping("/findByzd")
	public String find2(String startC,String endC, String startT, String endT,Model model) throws ParseException {
		if("".equals(startT)) startT ="1999-01-01T01:01";
		if("".equals(endT)) endT ="3000-01-01T01:01";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date startT1 = simpleDateFormat.parse(startT);
		Date endT1 = simpleDateFormat.parse(endT);
		
		List<Ticket> findTicket = ticketService.findTicket2(startC, endC, startT1, endT1);
		model.addAttribute("list", findTicket);
		return "list";
	}
}
