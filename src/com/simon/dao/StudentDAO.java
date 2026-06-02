package com.simon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.simon.model.Student;
import com.simon.util.DBConnection;

public class StudentDAO {
	
	public boolean addStudent(Student student) {
		Connection connect;
		int result=0;
		PreparedStatement statement;
		try {
			connect = DBConnection.getConnection();
			String sql = "Insert into studentsinfo (id, sname, email, marks) values (?,?,?,?)";
		    statement = connect.prepareStatement(sql);
			int id = student.getId();
			String sname = student.getName();
			String email = student.getEmail();
			double marks = student.getMarks();
			
			statement.setInt(1,id);
			statement.setString(2, sname);
			statement.setString(3, email);
			statement.setDouble(4, marks);
			
			result =statement.executeUpdate();
			
			DBConnection.close(connect, statement);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result==0) {
			return false;
		}else {
			return true;
		}
	}
	
	
	public Student getStudentById(int id) {
		Connection connect;
		PreparedStatement statemnet;
		ResultSet rs ;
		try {
			connect = DBConnection.getConnection();
			String sql = "Select * from studentsinfo where id=?";
			statemnet= connect.prepareStatement(sql);
			statemnet.setInt(1, id);
			 rs = statemnet.executeQuery(sql);
			 
			 DBConnection.close(connect, statemnet);
			 
			 while(rs.next()) {
				int id1 = rs.getInt(1);
				String sname = rs.getString(2);
				String email = rs.getString(3);
				double marks = rs.getDouble(4);
				return new Student(id1, sname, email, marks);
				}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<Student> getAllstudentsinfo(){
		Connection connect;
		PreparedStatement statemnet;
		ResultSet rs ;
		List<Student> list = new ArrayList();
		
		try {
			connect = DBConnection.getConnection();
			String sql = "Select * from studentsinfo";
			statemnet = connect.prepareStatement(sql);
			
			 rs =  statemnet.executeQuery();
			 
			 DBConnection.close(connect, statemnet);
			 while(rs.next()) {
				 list.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
			 }
			 return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateStudentMarks(int id, double marks) {
		Connection connect;
		PreparedStatement statemnet;
		int rowsAffected;
		
		try {
			connect = DBConnection.getConnection();
			String query = "update studentsinfo Set marks=? where id=?";
			statemnet = connect.prepareStatement(query);
			statemnet.setDouble(1, marks);
			statemnet.setInt(2, id);
			
			rowsAffected= statemnet.executeUpdate();
			
			if(rowsAffected ==0) {
				return false;
			}else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteStudent(int id) {
		Connection connect;
		PreparedStatement statemnet;
		int rowsAffected =0;
		
		try {
			connect = DBConnection.getConnection();
			String query = "Delect from studentsinfo where id =?";
			statemnet = connect.prepareStatement(query);
			statemnet.setInt(1, id);
			
			rowsAffected = statemnet.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(rowsAffected ==0) {
			return false;
		}else {
			return true;
		}
	}
}
