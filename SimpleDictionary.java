package ch15_2;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.awt.*;

public class SimpleDictionary extends JPanel implements ActionListener{
	/*
	 * 단어 입력 받을 수 있는 JTextField
	 * 검색 버튼
	 * 추가 버튼
	 * 단어장 구현을 위한 자료 구조로 Map 객체
	 */
	private JTextField inputField = new JTextField(30);
	private JButton searchBtn = new JButton("검색");
	private JButton addBtn = new JButton("추가");
	
	//Map 객체를 단어장 구현 사용
	//<key, value> 쌍으로 저장. key는 한글단어, value는 대응되는 영어단어
	private Map<String, String> words = new HashMap<>();
	public static final String driverClassName = "oracle.jdbc.driver.OracleDriver";
	private static final String DIC_FILE_NAME = "dict.props";
	//DB마다 서버 URL 포멧이 다르다
	//
	private static final String DB_SERVER_URL ="jdbc:oracle:thin:@localhost:1521:xe\r\n";
	private static final String DB_USER ="java";
	private static final String DB_USER_PW ="java";
	
	public SimpleDictionary() {
		//Panel의 기본 레이아웃은 : FlowLayout
		this.add(inputField);
		this.add(searchBtn);
		this.add(addBtn);
		
		//searchBtn, addBtn에 클릭 이벤트가 발생했을 때 처리할 리스너를 지정하자!
		searchBtn.addActionListener(this);
		addBtn.addActionListener(this);
		
		this.setPreferredSize(new Dimension(600, 50));
		
		//파일에 key = value 형태로 저장돤 엔트리들을 읽어서, words를 구성하자.
		//DB에서 레코드를 읽고, 그 레코드들을 이용해 dict 맵을 구성하자
		bulidDictionaryFromDB();
//		bulidDictionaryFromFile();
	}
	private void bulidDictionaryFromDB() {
		try {
			Class.forName(driverClassName);
			
		}catch(Exception e) {
			System.out.println( e.getMessage());
			return;
		}
		/*
		 * 1. database server 연결
		 * 		a. JDBC Driver를 로딩(적재)
		 * 		b. DriverManager (java,sql 페키지에 정의된 클래스)
		 * 		   Connection con = DriverManager.getConnection();
		 * 		   메서드를 호출해 연결을 establish
		 * 		   이 떄 연결 정보를 getConnection() 메서드에 전달해줘야 함
		 * 	       연결정보: DB Server의 URL
		 * 			-> (ip 주소, port 번호, 데이터베이스 이름)
		 * 				DB 사용자의 아이디와 암호
		 * 2. Connection 객체를 통해 SQL문 실행을 서버에 요청하고
		 *    그 경과를 받아 처리한다
		 *    크게 두 가지 방법이 있다
		 *    첫번째는 con.createStatement() 메소드 호출을 토해서
		 *    변환되는 Statement 객체를 이용하는 방법(장작 SQL문)
		 *    정적SQL문 : 프로그래밍 시점에 실해할 SQL문 결정되고 고장된 경우
		 *    select * from dict;
		 *    두번째는 con.prepateStatemnet() 메서드 호출을 통해서
		 *    반환되는 prepateStatemnet 객체를 이용하는 방법 (동적 SQL문)
		 *    동적 SQL문 : 프로그래밍 시점에 실행할 SQL문 결정되지 않고
		 *    변경되는 SQL문
		 *    select * from dict where han = ?
		 * 이 예에서는 prepateStatemnet 객체를 이용한다.
		 * 	String sql = "select * from dict";
		 * 	prepateStatemnet pstmt = cpm.prepateStatemnet(sql);
		 * 	//실행 즌비가 된 prepateStatemnet를 실행시키는 방법은 크게 2가지
		 * 	첫번째: 실행할 SQL 문이 insert, delete, 또는 update 문인 경우
		 * 		"insert into ..."
		 * 		"delete from dict ..."
		 * 		"update set eng = ..... form ......"
		 * 		
		 * 		pstmt.executeUpdate();
		 * 		
		 * 		"select..."
		 * 	두번째: 실행할 SQL 문이 select 문인 경우
		 * 		ResultSet rs = pstmt.exrcuteQuery();
		 * 3. DB Server와의 연결을 해제(close)한다
		 * 	  con.close();
		 */
		//MySQL JDBC 드라이버를 메모리에 적재
		//드라이버 클래스 이름은 DBMS마다 다르다
	
		//DB 서버에 연결
		try(Connection con =
				DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)) {
			
			//SELECT 문 실행
			String sql = "select * from dict";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				//현재 포인터가 가리키는 칼럼 값을 빼오면 됨
				//각 칼럼의 타입에 따라서, 호출할 메서드가 달라진다
				//예를 들어서 char, varchar 타입의 칼럼은
				//getString("칼럼이름" 또는 "칼럼 위치");
				//int 타입의 칼럼은 getInt(...);
				//DateTime, Date 타입의 칼럼 값은
				//getDate
				String key = rs.getString(1);
				//String key = rs.getString("hword");
				String value = rs.getString(2);
				//String value = rs.getString("eword");
				words.put(key, value);
				words.put(value, key);
				
				System.out.println(key+" : "+value);

			}
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
//			final {
//			try {con.close();
//			}catch(Exception ignore) {
//			}
//		
	}
	private void bulidDictionaryFromFile() {
		//properties는
		//key, value와 타입이 각각 String, String으로
		//고정된 일종에 Map이다
		Properties props = new Properties();
//		props.put("사과","apple");
//		System.out.println(props.get(("사과")));
		
		//파일에서 읽어서 props 객체의 <key, value>
		//쌍을 구성할 수 있다
//		FileReader fReader = new FileReader(DIC_FILE_NAME);
//		props.load(fReader);
		try(FileReader fReader = new FileReader(DIC_FILE_NAME)){
			props.load(fReader);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Set<Object>set = props.keySet();
		for(Object obj : set) {
			words.put((String)obj, (String)props.get(obj));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String key = inputField.getText();
			if(key.trim().length() == 0) //공백만 입력된 경우는 무시.
				return;
			if(e.getSource() == searchBtn) {
			/*
			 * 입력된 단어를 추출
			 * 그 단어를 key 값으로 가지는 대응되는 맵에 엔드리가 있는지 검사 -> dict.get(단어);
			 * 그 단어에 대응되는 값이 있으면 JOptionPane.showMessageDialog() 메서드를
			 * 호출해서 그 대응되는 값을 보여준다.
			 * 없으면(null이면) JOptionPane.showMessageDialog() 메서드를 호출해서
			 * '단어를 찾을 수 없습니다' 라고 출력해준다
			 * inputField를 빈 문자열로 설정.
			 */
			System.out.println("["+ key + "]");
			String value = words.get(key);
			if(value != null) { //대응되는 단어가 있는 경우
				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			}else { //대응되는 단어가 없는 경우
				JOptionPane.showMessageDialog(this, "단어를 찾을 수 없습니다",key,
						JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource() == addBtn) {
			/*
			 * 입력된 단어를 추출
			 * String value = JOptionPane.showInputDialot();
			 * 메서드를 호출해서 추가할 영어 단어를 입력 받는다
			 * dict.put(입력필드에 입력된 단어, inputDialog에 입력된 단어);
			 */
			String value = JOptionPane.showInputDialog(this, key + "에 대응되는 영어단어를 입력하세요");
			if(value.trim().length() == 0) return;
			words.put(key, value);
			addWordToFile(key, value);
			//파일에도 key = value 의 쌍으로 기록해놓자.
			//DB에 <key, value>의 쌍을 하나의 레코드로 지정하자
			addToDB(key,value);
			JOptionPane.showInputDialog(this, "["+value +"] 영어단어가 추가되었습니다",
					key, JOptionPane.INFORMATION_MESSAGE);
			}
//		inputField.setText("");
		
	}
	public void addWordToDB(String key, String value) {
		/*
		 * 1.DB연결
		 * 		(1)JDBC 드라이버 메모리 적재(한 번만 하면 되니까 여기에서는 안해도 된다)
		 * 		(2)DriveManger.getConnection(url, id, pw) 호출
		 * 			Connection 객체를 생성하고
		 * 2.SQL문 실행
		 * 		(1)Connection 객체에게 실행할 SQL 문을 실행 준비 요청하고 con.prepateStatemnet(sql);
		 * 			PrepateStatemnet 객체가 변환된다
		 * 		(2)prepateStatemnet 객체에게 서버에게 실행요청 보내라
		 * 			prepateStatemnet.excuteUpdate(); <-실행할 SQL문의 insert, delete 또는 update 
		 * 			prepateStatemnet.executeQuery()l <-실행할 SQL문의 select 문일 때
		 * 3. DB 연결 해제
		 * 		con.close();
		 */
		try(Connection con =
				DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)) {
			// ? 는 place holder 이고, 실행준비 시킨 후에,
			// 실행 직전에 저 ? 자리에 값을 실핼하고 실행 요청을 보낸다.
			String sql = "insert into dict values(?, ?)";
//			String sql2 = "insert into dict values("장미","rose")";
			
			PreparedStatement pstmt = con.prepareStatement(sql);  //sql문을 DB 서버로 보내서 실행준비  시켜라
			// ? 자리에 들어갈 column 값의 데이터 타입에 따라
			// 적절한 setXXX 메서드를 호출해야 한다
			pstmt.setString(1, "");
			pstmt.setString(2, "");
			
			//실행할 SQL문의 insert, delete, 또는 update문일 떄 executeUpdate 메서드
			//executeUpdate 메서드는 실행 후, 정수 값을 반환한다
			//그 정수 값은 insert, delete, update 문의 수행으로 변경된 레코드의 수
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private void addToDB(String key, String value) {
		/*
		 * 1. 드라이버 클래스는 딱 한 번만 메모리에 적재하면 됨.
		 * 	  근데, 이미 객체가 생성될 때, 생성자에서 적재되었음.
		 * 	  여기에서는 적재할 필요가 없다
		 * 
		 * 2. 데이터베이스에 연결
		 * 3. SQL 문 실행
		 * 4. 데이터베이스 연결 해제
		 */
		
		try(Connection con = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)){
			String sql = "insert into dict values(?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			// ? 자리에 값을 채운 후에, 서버에세
			// 실행준비가 된 SQL문을 실행하라고 요청해야 한다
			pstmt.setString(1, key);
			pstmt.setString(2, value);
			
			pstmt.executeUpdate(); // 실행 요청
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	private void addWordToFile(String key, String value) {
		try(FileWriter fWriter = new FileWriter(DIC_FILE_NAME, true)){
			fWriter.write(key+"="+value+"\n");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		SimpleDictionary dictPanel = new SimpleDictionary();
		frame.add(dictPanel);
		frame.setTitle("나의 한영사전");
		
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
	}
}
