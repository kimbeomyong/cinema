package com.mire.movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.mire.cinema.Application;
import com.mire.cinema.domain.itemgiftcard.ItemGiftCardDTO;
import com.mire.cinema.domain.member.MemberDTO;
import com.mire.cinema.domain.member.MemberDTO.search;
import com.mire.cinema.domain.order.OrderDTO;
import com.mire.cinema.repository.ItemGiftCardMapper;
import com.mire.cinema.repository.MemberMapper;
import com.mire.cinema.repository.MovieMapper;
import com.mire.cinema.repository.OrderMapper;
import com.mire.cinema.service.ItemGiftCardService;
import com.mire.cinema.service.ItemGiftCardServiceImpl;
import com.mire.cinema.service.MemberService;
import com.mire.cinema.service.MovieService;
import com.mire.cinema.service.MovieServiceImpl;
import com.mire.cinema.domain.reserve.ReserveDTO;
import com.mire.cinema.domain.reserve.ReserveDTO.Count;
import com.mire.cinema.repository.ReserveMapper;

import lombok.extern.java.Log;

@Log
@SpringBootTest
@ContextConfiguration(classes = Application.class)
class ApplicationTests {
	@Autowired
	MovieService Service;
	
	@Autowired
	MovieServiceImpl ServiceImpl;
	
	@Autowired
    MovieMapper mapper;

	@Autowired
	ReserveMapper mapper2;
	 @Test
	    void testSelectItemGiftCard() {
	       System.out.println(Service.findSearchMovie("무서운"));
	    
	       ReserveDTO.Count count = new Count();
	       count.setStart(1);
	       count.setEnd(3);
	       count.setMemberId("wltn3223");
	       System.out.println(mapper2.getPartMyList(count).size());
	        
	    }
}