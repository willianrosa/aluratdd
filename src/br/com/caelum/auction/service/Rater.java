package br.com.caelum.auction.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.caelum.auction.domain.Auction;
import br.com.caelum.auction.domain.Bid;

public class Rater {
	Double lowestBid = Double.NEGATIVE_INFINITY;
	Double highestBid= Double.POSITIVE_INFINITY;
	private Auction auction;
	private Double averageBid;
	private List<Bid> threeLargest = new ArrayList<Bid>();

	public Rater(Auction auction) {
		this.auction = auction;
	}

	public Rater() {}

	public void evaluate() {
		
		Optional.ofNullable(auction.getBids()).orElseThrow(() -> new RuntimeException("You must have at least one bid"));
		
		this.lowestBid = auction.getBids().stream()
				.mapToDouble(Bid::getAmount)
				.min()
				.getAsDouble();

		this.highestBid = auction.getBids().stream()
				.mapToDouble(Bid::getAmount)
				.max()
				.getAsDouble();

		this.averageBid = auction.getBids().stream()
				.mapToDouble(Bid::getAmount)
				.average()
				.getAsDouble();
		
		this.threeLargest = auction.getBids().stream()
				.sorted(Comparator.comparingDouble(Bid::getAmount).reversed())
				.limit(3)
				.collect(Collectors.toList());
			

	}

	public Double getLowestBid() {
		return this.lowestBid;
	}
	
	public Double getHighestBid() {
		return this.highestBid;
	}
	
	public Double getAverageBid(){
		return this.averageBid;
	}
	
	public List<Bid> getThreeLargest(){
		return this.threeLargest;
	}

	public void rating(Auction auction) {
		this.auction = auction;
		this.evaluate();
	}

}
