package br.com.caelum.auction.domain;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class BidTest {

	@Test
	public void test() {
		Bid bid = new Bid(new User("willian"), 2000);
		assertThat(bid, equalTo(new Bid(new User("willian"),2000)));
	}

}
