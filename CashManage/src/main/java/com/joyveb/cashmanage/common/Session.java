package com.joyveb.cashmanage.common;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import lombok.Data;

public @Data class Session implements Delayed {

	//构造器
	public Session(String id) {
		this.id = id;
		this.idleTimestampNano = System.nanoTime();
	}

	//获取序列号
	public int getSequenceNumber() {
		sequenceNumber = ++sequenceNumber > 7 ? 1 : sequenceNumber;
		return sequenceNumber;
	}

	//设置延迟时间
	public void takeRest(long delayNano) {
		this.idleTimestampNano = System.nanoTime() + delayNano;
	}

	@Override
	public int compareTo(Delayed o) {
		Session other = (Session)o;
		return idleTimestampNano > other.idleTimestampNano ? 1 : (idleTimestampNano < other.idleTimestampNano ? -1 : 0);
	}

	//获取延迟时间
	@Override
	public long getDelay(TimeUnit unit) {
		return this.idleTimestampNano - System.nanoTime();
	}

	private final String id;
	private volatile boolean idle = true;
	private volatile int sequenceNumber = 1;
	private volatile long idleTimestampNano;

}
