package br.com.caelum.auction.domain;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.auction.builder.AuctionBuilder;
import br.com.caelum.auction.domain.Auction;
import br.com.caelum.auction.domain.Bid;
import br.com.caelum.auction.domain.User;

public class AuctionTest {
	
	User willian, jose, marcos;
	@Before
	public void setup(){
		willian = new User("willian");
		jose = new User("jose");
		marcos = new User("marcos");
		
	}

	@Test
	public void shouldReceiveOneBid() {
		Auction auction = new AuctionBuilder().to("Macbook Pro 15")
				.bid(willian, 2000).build();

		assertThat(auction.getBids(),hasItems(new Bid(willian,2000)));
	}

	@Test
	public void shouldReceiveManyBids() {
		Auction auction = new AuctionBuilder().to("Macbook Pro 15")
				.bid(jose,2000)
				.bid(willian,3000)
				.build();

		assertThat(auction.getBids(),hasItems(new Bid(jose,2000)));
		assertThat(auction.getBids(),hasItems(new Bid(willian,3000)));
	}

	@Test
	public void shouldNotToAllowTwoBidsFollowedTheSameUser() {
		Auction auction = new AuctionBuilder().to("Macbook Pro 15")
				.bid(jose, 2000)
				.bid(jose, 3000)
				.build();

		assertThat(auction.getBids().size(),equalTo(1));
		assertThat(auction.getBids(), hasItems(new Bid(jose,2000)));
	}

	@Test
	public void shouldNotToAllowMoreThanFiveBidsOfSameUser() {
		Auction auction = new AuctionBuilder().to("Macbook Pro 15")
				.bid(jose, 2000)
				.bid(willian, 3000)
				.bid(jose, 4000)
				.bid(willian, 5000)
				.bid(jose, 6000)
				.bid(willian, 7000)
				.bid(jose, 8000)
				.bid(willian, 9000)
				.bid(jose, 10000)
				.bid(willian, 11000)
				.bid(jose, 12000)
				.build();

		assertEquals(10, auction.getBids().size());
		assertThat(auction.getBids(),not(hasItems(new Bid(jose,12000))));
	}
	
	@Test
    public void shouldToAllowDoubleLastBid() {
	    Auction auction = new AuctionBuilder().to("Macbook Pro 15")
	    		.bid(willian, 2000)
	    		.bid(marcos, 3000)
	    		.build();
	    
	    auction.doubleBid(willian);
	
	    assertThat(auction.getBids(), hasItems(new Bid(willian,4000)));
	}
}