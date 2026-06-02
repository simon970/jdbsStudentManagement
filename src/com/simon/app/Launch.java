package com.simon.app;

import java.util.Scanner;

import com.simon.dao.StudentDAO;
import com.simon.model.Student;

public class Launch {

	public static void main(String[] args) {
		int value=0;
		Scanner sc = new Scanner(System.in);
		
		do {
			
			System.out.println("These are the following operations :");
			System.out.println("1. Add Student");
			System.out.println("2. Get Student By Id");
			System.out.println("3. Get All Students");
			System.out.println("4. Update all Students");
			System.out.println("5. Delete Student");
			System.out.println("6. Exit");
			System.out.println();
			
			System.out.println("Enter the operation you wanna perform");
			value = sc.nextInt();
			StudentDAO op = new StudentDAO();
			
			switch (value) {
			case  1: {
			 System.out.println("Enter the id");
			 int id = sc.nextInt();
			 
			 System.out.println("Enter the name");
			 String sname = sc.next();
			 
			 System.out.println("Enter the email");
			 String email = sc.next();
			 
			 System.out.println("Enter the marks");
			 int marks = sc.nextInt();
		 
			 Student student = new Student(id, sname, email, marks);
			 boolean result =op.addStudent(student);
			 
			 if(result) {
				 System.out.println("Studnet has been added");
			 }else {
				 System.out.println("There was some issue ");
			 } 
			 break;
			}
			case 6:
				System.out.println("Exit");
				break;
			}
		} while (value !=6);
		sc.close();
	}

}
