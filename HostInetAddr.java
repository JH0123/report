package network_Programming;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostInetAddr {
	public static void main(String[]args) {
		String hostName = "www.naver.com";
		
		try {
			InetAddress address = InetAddress.getByName(hostName);
			System.out.println("IP¡÷º“: "+address.getHostAddress());
		}catch(UnknownHostException e){
			System.out.println(e.getMessage());
		}
	}
}
