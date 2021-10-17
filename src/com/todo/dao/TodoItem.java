package com.todo.dao;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private String category;
    private String due_date;
    private String current_date;
    private int id;
    private int comp;
    private int importance;

    public TodoItem(String title, String desc, String category, String due_date) {
        this.title=title;
        this.desc=desc;
        this.category = category;
        this.due_date = due_date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
    	this.current_date = format.format(new Date());
    }
    
    public TodoItem(String title, String desc, String category, String due_date, int importance) {
        this.title=title;
        this.desc=desc;
        this.category = category;
        this.due_date = due_date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
    	this.current_date = format.format(new Date());
    	this.importance = importance;
    }
    
    public TodoItem(String title, String desc, String category, String due_date, String current_date) {
    	this.title = title;
    	this.desc = desc;
    	this.category = category;
        this.due_date = due_date;
    	this.current_date = current_date;
    }
    
    public TodoItem(String title, String desc, String category, String due_date, int comp, int importance) {
    	this.title = title;
    	this.desc = desc;
    	this.category = category;
        this.due_date = due_date;
        this.comp = comp;
        this.importance = importance;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getCategory() {
    	return category;
    }

    public void setCategory(String category) {
    	this.category = category;
    }
    
    public String getDueDate() {
    	return due_date;
    }

    public void setDueDate(String due_date) {
    	this.due_date = due_date;
    }
    
    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(Date current_date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
    	this.current_date = format.format(new Date());
    }
    
    public void setCurrent_date(String current_date) {
    	this.current_date = current_date;
    }
    
    public String toSaveString() {
    	return category + "##" + title + "##" + desc + "##" + due_date + "##" + current_date + "\n";
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return id;
    }
    
    public void setComp(int comp) {
    	this.comp = comp;
    }
    
    public int getComp() {
    	return comp;
    }
    
    public void setImportance(int importance) {
    	this.importance = importance;
    }
    
    public int getImportance() {
    	return importance;
    }
    
    public String toString() {
    	if(importance == 3) {
    		
    		if(comp == 1) {
        		return id + " ***[" + category + "] " + title + " [V] - " + desc + " - " + due_date + " - " + current_date + "***";
        	} else {
        		return id + " ***[" + category + "] " + title + " - " + desc + " - " + due_date + " - " + current_date + "***";
        	}
    		
    	} else if(importance == 2) {
    		
    		if(comp == 1) {
        		return id + " **[" + category + "] " + title + " [V] - " + desc + " - " + due_date + " - " + current_date + "**";
        	} else {
        		return id + " **[" + category + "] " + title + " - " + desc + " - " + due_date + " - " + current_date + "**";
        	}
    		
    	} else if(importance == 1) {
    		
    		if(comp == 1) {
        		return id + " *[" + category + "] " + title + " [V] - " + desc + " - " + due_date + " - " + current_date + "*";
        	} else {
        		return id + " *[" + category + "] " + title + " - " + desc + " - " + due_date + " - " + current_date + "*";
        	}
    		
    	} else {
    		
    		if(comp == 1) {
        		return id + " [" + category + "] " + title + " [V] - " + desc + " - " + due_date + " - " + current_date;
        	} else {
        		return id + " [" + category + "] " + title + " - " + desc + " - " + due_date + " - " + current_date;
        	}
    		
    	}
    }
}
