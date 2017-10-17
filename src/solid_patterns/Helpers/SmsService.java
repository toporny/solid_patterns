package solid_patterns.Helpers;

import solid_patterns.Interfaces.IMsgProvider;

public class SmsService implements IMsgProvider {

	public void SendMsg(String senderDetails, String recipientDetails, String msgContent) {
		System.out.println("SMS message from "+senderDetails+" to "+recipientDetails+ ". Contant: "+msgContent);
	}
	
}

