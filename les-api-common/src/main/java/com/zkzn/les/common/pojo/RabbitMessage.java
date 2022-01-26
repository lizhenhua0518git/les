package com.zkzn.les.common.pojo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class RabbitMessage implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8009402906077654690L;
	
	
	private Class<?>[] paramTypes;//参数类型  
    private String exchange;//交换器  
	      
    private Object[] params;  //发消息的参数，第一个代表仓库，第二个及以后代表占位符的内容
	      
    private String routeKey;//路由key  
    
    private String type;//类型：message代表消息通知，business代表业务处理
    
    //消息失效时间
  	private  String expiration;
	      
    public RabbitMessage(){}  
	  
    public RabbitMessage(String exchange,String routeKey,Object...params){  
        this.params=params;       
        this.exchange=exchange;  
        this.routeKey=routeKey;  
    }  
    
    public RabbitMessage(String exchange,String routeKey,String type,Object...params)  {  
        this.params=params;       
        this.exchange=exchange;  
        this.routeKey=routeKey;  
        this.type = type;
        int len=params.length;  
        Class[] clazzArray=new Class[len];  
        for(int i=0;i<len;i++) {
            clazzArray[i]=params[i].getClass();
        }  
        this.paramTypes=clazzArray;  
    }  
      
    public byte[] getSerialBytes()  {  
        byte[] res=new byte[0];
        try {
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);  
            oos.close();  
            res=baos.toByteArray();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }         
        return res;  
    }

	public Class<?>[] getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(Class<?>[] paramTypes) {
		this.paramTypes = paramTypes;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public String getRouteKey() {
		return routeKey;
	}

	public void setRouteKey(String routeKey) {
		this.routeKey = routeKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	
	
}
