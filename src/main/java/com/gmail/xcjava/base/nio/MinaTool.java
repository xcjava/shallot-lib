package com.gmail.xcjava.base.nio;

import java.net.InetSocketAddress;
  
import org.apache.mina.core.future.ConnectFuture;  
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;  
import org.apache.mina.transport.socket.nio.NioSocketConnector;  

/**
 * mina简易nio客户端
 * @author xcjava@gmail.com
 *
 */
public class MinaTool {
	
	private NioSocketConnector connector;
	private ConnectFuture connectFuture;
	private IoHandler handler;
	private ProtocolCodecFilter codec;
	private String ip;
	private int port;

	/**
	 * 创建客户端
	 * @param handler	业务处理器
	 * @param codec		编译器
	 * @param ip		ip地址
	 * @param port		端口
	 */
	public MinaTool(IoHandler handler, ProtocolCodecFilter codec, String ip, int port){
		
		this.handler = handler;
		this.codec = codec;
		this.ip = ip;
		this.port = port;
		init();
	}
	
	private void init(){
		
		connector = new NioSocketConnector();   
	    connector.getFilterChain().addLast( "logger", new LoggingFilter() );   
	    connector.getFilterChain().addLast( "codec", this.codec);   
	    connector.setHandler(this.handler);
	    
	    connector.getSessionConfig().setReadBufferSize(2048);
	    connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 1000);
		
	}
	
	/**
	 * 创建链接
	 */
	public void open(){
		if(connector.isDisposed() || connector.isDisposing()){
			init();
		}else{
			if(connectFuture == null || !connectFuture.isConnected()){
				connectFuture = connector.connect(new InetSocketAddress(this.ip, this.port));
				connectFuture.awaitUninterruptibly();
			}
		}
	}
	
	public boolean isOpen(){
		if(connectFuture == null)
			return false;
		return connectFuture.isConnected();
	}
	
	/**
	 * 发送数据
	 * @param obj
	 */
	public void write(Object obj){
		connectFuture.getSession().write(obj);
	}
	
	/**
	 * 设置会话属性
	 * @param key
	 * @param value
	 */
	public void setAttribute(Object key, Object value){
		connectFuture.getSession().setAttribute(key, value);
	}
	
	/**
	 * 获取会话属性
	 * @param key
	 * @return
	 */
	public Object getAttribute(Object key){
		if(!connectFuture.getSession().containsAttribute(key))
			return null;
		return connectFuture.getSession().getAttribute(key);
	}
	
	/**
	 * 关闭
	 * 使用完毕后一定要执行该操作
	 */
	public void close(){
		if(isOpen()){
			connectFuture.getSession().close(true);  
			connectFuture.getSession().getCloseFuture().awaitUninterruptibly();
		    connector.dispose();
		}
	}
	
}
