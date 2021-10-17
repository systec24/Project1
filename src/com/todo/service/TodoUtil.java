package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		
		String title, desc, category, due_date;
		int importance;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n[항목 추가]");
		System.out.print("제목 > ");
		title = sc.nextLine().trim();
		
		System.out.print("카테고리 > ");
		category = sc.nextLine();
		
		System.out.print("내용 > ");
		desc = sc.nextLine();
		
		if (l.isDuplicate(title, desc)) {
			System.out.print("중복되는 항목이 존재합니다!\n\n");
			return;
		}
		
		System.out.print("마감일자 > ");
		due_date = sc.nextLine();
		
		System.out.print("중요도 (0 - 3) > ");
		importance = sc.nextInt();
		
		TodoItem t = new TodoItem(title, desc, category, due_date, importance);
		if(l.addItem(t) > 0)
			System.out.println("항목 추가 완료!\n");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n[항목 삭제]");
		System.out.print("삭제할 항목의 번호를 입력하시오 > ");
		
		String str = sc.nextLine();
		String[] array = str.split(" ");
		int[] index = new int[array.length];

		for(int i = 0; i < array.length; i++) {
			index[i] = Integer.parseInt(array[i]);
		}		
		
		for(int i = 0; i < index.length; i++) {
			l.deleteItem(index[i]);
		}
		
		System.out.println("삭제되었습니다.");
	}
	
	public static void deleteAll(TodoList l) {
		
		for(int i = 1; i <= l.getBiggestIndex(); i++) {
			TodoItem t = l.getItem(i);
			l.deleteItem(i);
		}
		
		System.out.println("모두 삭제되었습니다.");
	}

	public static void deleteComp(TodoList l) {
		int[] count = l.getComp();
		for(int i = 0; i < count.length; i++) {
			l.deleteItem(count[i]);
		}
		
		System.out.println("완료항목 삭제되었습니다.");
	}

	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n[항목 수정]");
		System.out.print("수정할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		
		sc.nextLine();
		
		System.out.print("새 제목 > ");
		String new_title = sc.nextLine().trim();

		System.out.print("새 카테고리 > ");
		String new_category = sc.nextLine().trim();
		
		System.out.print("새 내용 > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("새 마감일자 > ");
		String new_due_date = sc.nextLine().trim();
		
		System.out.print("중요도 (0 - 3) > ");
		int importance = sc.nextInt();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date, importance);
		t.setId(index);
		if(l.updateItem(t) > 0)
			System.out.println("항목 수정 완료!\n");
		
	}
	
	public static void completeItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n[항목 완료]");
		System.out.print("완료할 항목의 번호를 입력하시오 > ");
		
		String str = sc.nextLine();
		String[] array = str.split(" ");
		int[] index = new int[array.length];

		for(int i = 0; i < array.length; i++) {
			index[i] = Integer.parseInt(array[i]);
		}		
		
		for(int i = 0; i < index.length; i++) {
			TodoItem t = l.getItem(index[i]);
			l.completeItem(t);
		}
		
		System.out.println("완료 체크하였습니다!\n");
	}
	
	public static void find(TodoList l, String search) {
		int count = 0;
		System.out.println();
		for(TodoItem item : l.getList(search)) {
			if(item.getCategory().contains(search) || item.getTitle().contains(search) || item.getDesc().contains(search) || item.getDueDate().contains(search) || item.getCurrent_date().contains(search)) {
				System.out.println(item.toString());
				count++;
			}
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.\n");
	}
	
	public static void find_cate(TodoList l, String search) {
		int count = 0;
		System.out.println();
		for(TodoItem item : l.getListCategory(search)) {
			System.out.println(item.toString());
			count++;
		}
		
		System.out.println("총 " + count + "개의 항목을 찾았습니다.\n");
	}
	
	public static void listCate(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.println("\n총 " + count + "개의 카테고리가 등록되어 있습니다.\n");
	}

	public static void listAll(TodoList l) {
		System.out.println("\n[전체 목록, 총 " + l.size() + "개]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
		System.out.println();
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		ArrayList<TodoItem> list = l.getOrderedList(orderby, ordering);
		System.out.println("\n[전체 목록, 총 " + list.size() + "개]");
		for (TodoItem item : list) {
			System.out.println(item.toString());
		}
		System.out.println();
	}
	
	public static void listAll(TodoList l, int comp) {
		ArrayList<TodoItem> list = l.getList(comp);
		System.out.println("\n[전체 목록, 총 " + list.size() + "개]");
		for (TodoItem item : list) {
			System.out.println(item.toString());
		}
		System.out.println();
	}
	
	public static void listOutdated(TodoList l) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    	String current_date = format.format(new Date());
    	Date date1 = null;
		try {
			date1 = format.parse(current_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		for (TodoItem item : l.getList(0)) {
			Date date2 = null;
			try {
				date2 = format.parse(item.getDueDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(date1.compareTo(date2) > 0) {
				System.out.println(item.toString());
			}
		}
		System.out.println();
	}
	
	public static void listImportance(TodoList l, int ordering) {
		ArrayList<TodoItem> list = l.getImpList(ordering);
		System.out.println("\n[전체 목록, 총 " + list.size() + "개]");
    	
		for (TodoItem item : list) {
			System.out.println(item.toString());
		}
		System.out.println();
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			
			for(TodoItem item : l.getList()) {
				bw.write(item.toSaveString());
			}
			
			bw.flush();
			bw.close();
			System.out.print("모든 데이터가 저장되었습니다.");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
