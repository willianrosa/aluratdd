package br.com.caelum.auction.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.auction.builder.AuctionBuilder;
import br.com.caelum.auction.domain.Auction;
import br.com.caelum.auction.domain.Bid;
import br.com.caelum.auction.domain.User;
import br.com.caelum.auction.service.Rater;

public class RaterTest {

	private Auction auction;
	Rater actioneer;
	User willian, marcos, jose;
	
	@Before
	public void setup() {

		willian = new User("Willian");
		jose = new User("Jose");
		marcos = new User("Marcos");
		auction = new Auction("playstation");
		actioneer = new Rater();
	}

	@Test
	public void shouldReturnTheLowestAmount() {
		Auction auction = new AuctionBuilder().to("playstation")
				.bid(willian, 2000)
				.bid(jose, 7500)
				.build();
		
		actioneer.rating(auction);

		assertThat(actioneer.getLowestBid(),equalTo(2000D));
	}
	
	@Test
	public void shouldReturnTheHighestAmount() {
		Auction auction = new AuctionBuilder().to("playstation")
				.bid(willian, 5000)
				.bid(jose, 7500)
				.build();
		
		actioneer.rating(auction);
		
		assertThat(actioneer.getHighestBid(),equalTo(7500D));
	}	
	
	@Test
	public void shouldAllowAuctionThatHasOnlyOneBid(){
		Auction auction = new AuctionBuilder().to("Playstation")
				.bid(willian, 400)
				.build();
		
		actioneer.rating(auction);
		
		assertThat(actioneer.getHighestBid(),equalTo(400D));
		assertThat(actioneer.getLowestBid(),equalTo(400D));
	}
	
	@Test
	public void shouldReturnTheThreeHighestAmount(){
		Auction auction = new AuctionBuilder().to("playstation")
				.bid(willian,1000)
				.bid(willian,2000)
				.bid(jose,5000)
				.bid(marcos,7500)
				.bid(willian,10000)
				.build();

		actioneer.rating(auction);
		
		assertThat(actioneer.getThreeLargest(),hasItems(
				new Bid(jose,5000), 
				new Bid(marcos,7500), 
				new Bid(willian,10000)));
	}
	
	@Test
	public void shouldReturnTheTwoHighestAmount(){
		auction = new AuctionBuilder().to("carangos")
				.bid(marcos, 7500)
				.bid(willian, 10000)
				.build();

		actioneer.rating(auction);
		
		assertThat(actioneer.getThreeLargest(),hasItems(
				new Bid(marcos,7500),
				new Bid(willian,10000)));
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldNotAllowAuctionWithoutBid(){
		auction = new AuctionBuilder().to("carangos")
				.build();
		actioneer.rating(auction);
	}
	
	
	
}
