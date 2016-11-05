package br.com.caelum.auction.builder;

import br.com.caelum.auction.domain.Auction;
import br.com.caelum.auction.domain.Bid;
import br.com.caelum.auction.domain.User;

public class AuctionBuilder {

    private Auction auction;

    public AuctionBuilder() { }

    public AuctionBuilder to(String description) {
        this.auction = new Auction(description);
        return this;
    }

    public AuctionBuilder bid(User user, double amount) {
        auction.proposes(new Bid(user, amount));
        return this;
    }

    public Auction build() { 
        return auction;
    }
}