package ch15_2;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;
import java.io.*;
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
	private static final String DIC_FILE_NAME = "dict.props";
	
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
		bulidDictionaryFromFile();
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
			
			JOptionPane.showInputDialog(this, "["+value +"] 영어단어가 추가되었습니다",
					key, JOptionPane.INFORMATION_MESSAGE);
			}
//		inputField.setText("");
		
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
