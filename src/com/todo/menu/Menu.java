package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("< ToDoList 관리 명령어 사용법 >");
        System.out.println("add - 항목 추가");
        System.out.println("del - 항목 삭제");
        System.out.println("del_all - 항목 모두 삭제");
        System.out.println("del_comp - 완료 항목 삭제");
        System.out.println("edit - 항목 삭제");
        System.out.println("comp - 항목 완료처");
        System.out.println("ls - 전체 목록");
        System.out.println("ls_cate - 카테고리 목록");
        System.out.println("ls_comp - 완료된 목록");
        System.out.println("ls_not_comp - 미완료된 목록");
        System.out.println("ls_overdue - 기한 지난 항목 정렬");
        System.out.println("find <검색어> - 항목 검색");
        System.out.println("find_cate <검색어> - 카테고리 검색");
        System.out.println("ls_name - 제목순 정렬");
        System.out.println("ls_name_desc - 제목역순 정렬");
        System.out.println("ls_date - 날짜순 정렬");
        System.out.println("ls_date_desc - 최신순 정렬");
        System.out.println("ls_imp - 중요도순 정렬");
        System.out.println("ls_imp_desc - 중요도순 역정렬");
        System.out.println("exit - 정렬");
    }
    
    public static void prompt() {
    	System.out.print("Command > ");
    }
}
