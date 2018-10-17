package com.example.jdbcdemo.domain;

public class Drug{
	
	private long id;
	
	private String name;
	private String producer;
	private int prize;
	private int yoe;
	
	public Drug(String name, String producer, int prize, int yoe) {
		super();
		this.name = name;
		this.producer = producer;
		this.prize = prize;
		this.yoe = yoe;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProducer() {
                return producer;
        }
        public void setYoe(String producer) {
                this.producer = producer;
        }
	public int getPrize() {
                return prize;
        }
        public void setPrize(int prize) {
                this.prize = prize;
        }
	public int getYoe() {
		return yoe;
	}
	public void setYoe(int yoe) {
		this.yoe = yoe;
	}

	
}
