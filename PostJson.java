package network_Programming;

import java.net.*;
import java.sql.*;
import com.google.gson.Gson;

import java.io.*;

public class PostJson {
	public static void  main(String args[]) throws Exception{
		String site = "https://jsonplaceholder.typicode.com/posts";
		URL url = new URL(site);
		
		URLConnection con = url.openConnection();
		
		InputStream stream = con.getInputStream();
		InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
		
		BufferedReader bufReader = new BufferedReader(reader);
		
		String line = null;
		String jsonString = "";
		while((line = bufReader.readLine()) != null) {
//			System.out.println(line);
			jsonString += line;
		}
		
//		System.out.println(jsonString);
		
		Gson gson = new Gson();
//		String json = "[{'userId': 1, 'id':1 ,'title': 'test', 'body':'test body' }, {'userId': 2, 'id':2 ,'title': 'test2', 'body':'test body2' }]";
		Post[] posts = gson.fromJson(jsonString, Post[].class);
		
		/*
		 * Post post = new Post();
		 * post.setYserId(1);
		 * post.setId(l);
		 * post.setTitel("title");
		 * post.setBody("test body");
		 * return post;
		 */
//		System.out.println(post[0].getUserId());
//		System.out.println(post[0].getId());
//		System.out.println(post[0].getTitle());
//		System.out.println(post[0].getBody());
		
		for(Post post : posts) {
			System.out.println(post.getUserId());
			System.out.println(post.getId());
			System.out.println(post.getTitle());
			System.out.println(post.getBody());
			System.out.println("######################");

		}
		
		insertIntoDB(posts);
		
		
		// 연결
		// 결과를 입력스트립으로 받아서 문자열로 저장
		// 저장된 문자열로부터 자바 객체 생성
		
		//자바 객체를 이용해서 여러기지 처리를 할 수 있겠지
		//예를 들면, DB에 저장할 수 있고....
	}
	private static void insertIntoDB(Post[]posts)throws Exception {
		/*
		 * 1.Class.forName(...); // JDBC 드라이브 메모리에 적재
		 * 2.Connection con =
		 * 				DriverManger.getConnection(...); DB서버에 연결
		 * 3.String sql = "insert into posts(userId, id, title, body)
		 * 								values(?,?,?,?);
		 * PreparedStatement pstmt - con.prepareStatemnt(sql);
		 * 
		 * 4.? 자리에 들어갈 값을 설정한다
		 * pstmt.setInt(1, post.getUserId());
		 * pstmt.setInt(2, post.getId());
		 * pstmt.setString(3, post.getTitle());
		 * pstmt.setString(4, post.getBody());
		 * 
		 * 5.서버에 실행 요청
		 * pstmt.executeUpdate
		 * 6.con.close();
		 */
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe\r\n";
		String id = "java";
		String pw = "java";
		Connection con = DriverManager.getConnection(url, id, pw);
		String sql = "insert into posts(userId, id, title, body) values(?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		for(Post post: posts) {
			pstmt.setInt(1, post.getUserId());
			pstmt.setInt(2, post.getId());
			pstmt.setString(3, post.getTitle());
			pstmt.setString(4, post.getBody());
			pstmt.executeUpdate();
		}
		con.close();
	}
}
