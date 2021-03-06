package com.smallbell.server.util;

import com.alibaba.fastjson.JSON;
import com.smallbell.server.model.Event;
import com.smallbell.server.model.RecvieMessage;
import com.smallbell.server.model.RequestFile;
import com.smallbell.server.model.ResponseFile;
import com.smallbell.server.model.SecureModel;
/**
 * 传输数据转化包
 * 把传输的文本信息，文件信息转化成对象和json字符串
 */
public class ObjectConvertUtil {
	
	public static String convertModle(SecureModel secureModel){
		RecvieMessage recevie = new RecvieMessage();
		recevie.setData(JSON.toJSONString(secureModel));
		recevie.setMsgType(Event.MESSAGE_TYPE_SECURE_MODEL);
		return JSON.toJSONString(recevie);
	}
	
	public static String convertModle(ResponseFile response){
		RecvieMessage recevie = new RecvieMessage();
		recevie.setData(JSON.toJSONString(response));
		recevie.setMsgType(Event.MESSAGE_TYPE_RESPONSE_FILE);
		return JSON.toJSONString(recevie);
	}
	
	public static String convertModle(RequestFile requst){
		RecvieMessage recevie = new RecvieMessage();
		recevie.setData(JSON.toJSONString(requst));
		recevie.setMsgType(Event.MESSAGE_TYPE_REQUEST_FILE);
		return JSON.toJSONString(recevie);
	}
	
	public static Object convertModle(String recviejson){
		RecvieMessage recvie = (RecvieMessage) JSON.parseObject(recviejson,RecvieMessage.class);
		Object obj = null ;
		switch(recvie.getMsgType()){
			case Event.MESSAGE_TYPE_SECURE_MODEL:
				obj = (SecureModel) JSON.parseObject(recvie.getData().toString(),SecureModel.class);
				break;
			case Event.MESSAGE_TYPE_REQUEST_FILE:
				obj = (RequestFile) JSON.parseObject(recvie.getData().toString(),RequestFile.class);
				break;
			case Event.MESSAGE_TYPE_RESPONSE_FILE:
				obj = (ResponseFile) JSON.parseObject(recvie.getData().toString(),ResponseFile.class);
				break;
		}
		return obj ;
	}
	
	public static String request(Object obj){
		if( obj instanceof SecureModel ) {
			SecureModel secureModel = (SecureModel)obj;
			return convertModle(secureModel);
		} else if ( obj instanceof RequestFile) {
			RequestFile requestFile = (RequestFile)obj;
			return convertModle(requestFile);
		} else if ( obj instanceof ResponseFile) {
			ResponseFile responseFile = (ResponseFile)obj;
			return convertModle(responseFile);
		} else {
			return null ;
		}
		
	}
	
	
}
