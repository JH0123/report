package network_Programming;

import java.io.*;
import java.net.*;

public class HttpURLPostTest {
	public static void main(String[]args) throws Exception{
		String site = "http://localhost:9090/todos";
		URL url = new URL(site);
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
		con.setDoInput(true);
		con.setDoOutput(true);
		
//		con.setRequestMethod("POST");
		con.setRequestProperty("content-tyep", "application/x-www-form-urlencoded");
		
		//id=scpark & pw=1111
		
		String data = "id=scpark&pw=1111";
		
		OutputStream ostream = con.getOutputStream();
		
		OutputStreamWriter owriter = new OutputStreamWriter(ostream, "UTF-8");
		PrintWriter writer = new PrintWriter(owriter);
		writer.println(data);
		writer.flush();
		
		InputStream stream = con.getInputStream();
		
		InputStreamReader streamReader = new InputStreamReader(stream);
		
		BufferedReader reader = new BufferedReader(streamReader);
		String line;
		while((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println();
	}
}
