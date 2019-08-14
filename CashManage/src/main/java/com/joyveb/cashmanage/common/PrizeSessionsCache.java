package com.joyveb.cashmanage.common;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.entity.Terminal;
import com.joyveb.cashmanage.entity.TerminalExample;
import com.joyveb.cashmanage.service.TerminalService;
import com.joyveb.cashmanage.web.InitParm;

/**
 * 兑奖终端缓存，持有所有兑奖终端
 *
 */
@Slf4j
@Component("prizeSessionsCache")
public class PrizeSessionsCache implements Cacheable{

	@Autowired
	public PrizeSessionsCache(@Qualifier("terminalService")TerminalService terminalService) {
		this.terminalService = terminalService;
		this.lidles = new DelayQueue<Session>();
		this.sessions = new ConcurrentHashMap<String, Session>();
		this.reload();
	}

	//刷新缓存
	public void reload() {
		String ssid = "";
		rwl.writeLock().lock();
		try {
			this.lidles.clear();
			TerminalExample te = new TerminalExample();
			te.createCriteria().andIsusedEqualTo("0");
			List<Terminal> ls = terminalService.selectByExample(te);
			for(Terminal l : ls) {
				String id = l.getSessionid();
				ssid+=id+",";
				Session s = new Session(id);
				this.lidles.offer(s);
				this.sessions.put(l.getSessionid(), s);
			}
			if(!ssid.equals("")){
				log.debug("重新加载sessionID：("+ssid.substring(0,ssid.length()-1)+")");
			}
		} finally {
			rwl.writeLock().unlock();
		}
	}
	//从缓存中获取兑奖终端
	public Session getPrizeSession(long millis) throws InterruptedException {
		rwl.readLock().lock();
		try {
			return this.lidles.poll(millis, TimeUnit.MILLISECONDS);
		} finally {
			rwl.readLock().unlock();
		}
	}

	//释放指定终端
	public void release(final Session s) {
		rwl.readLock().lock();
		try {
			long delay = TimeUnit.NANOSECONDS.convert(initParm.getLongDbp("ips.session.delay.millis", 20), TimeUnit.MILLISECONDS);
			s.takeRest(delay);
			if(sessions.containsKey(s.getId())) {
				lidles.offer(s);
			}
			log.debug("释放"+s.getId()+"终端");
		} finally {
			rwl.readLock().unlock();
		}
	}

	//添加终端
	public void add(Session s) {
		rwl.writeLock().lock();
		try {
			lidles.offer(s);
			this.sessions.put(s.getId(), s);
		} finally {
			rwl.writeLock().unlock();
		}
	}
public static void main(String[] args) throws InterruptedException {
	DelayQueue<Session> lidles = new DelayQueue<Session>();
	for(int i=0;i<5;i++){
		Session s = new Session(i+"");
		lidles.add(s);
	}
	for(int j=0;j<10;j++){
		Session poll = lidles.poll(20, TimeUnit.MILLISECONDS);
		System.out.println(poll);
	}
	
}
	//删除终端
	public void remove(Session s) {
		rwl.writeLock().lock();
		try {
			this.lidles.remove(s);
			this.sessions.remove(s.getId());
		} finally {
			rwl.writeLock().unlock();
		}
	}

	//获取可用小奖兑奖终端集合
	public Collection<Session> getUseableLittleSessions() {
		return Collections.unmodifiableCollection(this.lidles);
	}

	public Session getPrizeSession() {
		Session session = null;
		for(int i = 0; i <= initParm.getIntDbp("ips.prizersession.maxfail.times", 3); i ++){
			try {
				session = getPrizeSession(initParm.getLongDbp("ips.prizersession.timeout.millis",20));
				if(session != null) {
					break;
				}
			} catch (InterruptedException e) {
				log.warn("Redeem the Serial number[" + session + "]获取兑奖终端异常:", e);
				Thread.currentThread().interrupt();
			}
			log.info("Redeem the prize Serial number[" + session + "]获取兑奖终端失败[" + i + "]次");
		}
		return session;
	}
	private @Resource(name="initParm") InitParm initParm;
	private final TerminalService terminalService;
	private final DelayQueue<Session> lidles;
	private final ConcurrentHashMap<String, Session> sessions;
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

}
