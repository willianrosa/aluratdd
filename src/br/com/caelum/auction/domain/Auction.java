package br.com.caelum.auction.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Auction {

	private String description;
	private List<Bid> bids;
	
	public Auction(String description) {
		this.description = description;
		this.bids = new ArrayList<Bid>();
	}
	
	public void proposes(Bid bid) {	
		if(allowsBid(bid.getUser())) bids.add(bid);
	}
	
	public void doubleBid(Bid lastBid){			
			proposes(new Bid(lastBid.getUser(), lastBid.getAmount()*2));
	}	

	private boolean allowsBid(User user) {
		return !getUserBid(user, getLastUser()) 
				&& bids.stream()
					.filter(l->l.getUser().equals(user)).count()<5;
	}

	private boolean getUserBid(User actual, Optional<User> lastUser) {
		return lastUser.isPresent() && lastUser.get().equals(actual);
	}

	private Optional<User> getLastUser() {
		return getLastBid()
		.map(l->l.getUser());
	}

	private Optional<Bid> getLastBid() {
		return bids.stream()
		.reduce((first,last)->last);
	}

	public String getDescription() {
		return description;
	}

	public List<Bid> getBids() {
		return Collections.unmodifiableList(bids);
	}

	public void doubleBid(User user) {
		getLastBidOf(user).ifPresent(this::doubleBid);
		
	}

	private Optional<Bid> getLastBidOf(User user) {
		return bids.stream()
				.filter(l->l.getUser().equals(user))
				.reduce((first,last)->last);
		
	}
	
}
