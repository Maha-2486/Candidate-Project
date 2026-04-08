package com.wipro.candidate.service;

import java.util.ArrayList;


import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.dao.CandidateDAO;
import com.wipro.candidate.util.WrongDataException;


public class CandidateMain {

	/**
	 * @param args
	 */
	public String addCandidate(CandidateBean studBean)
	{
		try {
            if (studBean == null) throw new WrongDataException();
            if (studBean.getName() == null || studBean.getName().trim().isEmpty()) throw new WrongDataException();
            if (studBean.getName().length() < 2) throw new WrongDataException();
            if (studBean.getM1() < 0 || studBean.getM1() > 100 ||
            		studBean.getM2() < 0 || studBean.getM2() > 100 ||
            		studBean.getM3() < 0 || studBean.getM3() > 100)
                throw new WrongDataException();

            CandidateDAO dao = new CandidateDAO();
            String id = dao.generateCandidateId(studBean.getName());
            studBean.setId(id);

            int total = studBean.getM1() + studBean.getM2() + studBean.getM3();
            if (total >= 240) { studBean.setResult("PASS"); studBean.setGrade("Distinction"); }
            else if (total >= 180) { studBean.setResult("PASS"); studBean.setGrade("First Class"); }
            else if (total >= 150) { studBean.setResult("PASS"); studBean.setGrade("Second Class"); }
            else if (total >= 105) { studBean.setResult("PASS"); studBean.setGrade("Third Class"); }
            else { studBean.setResult("FAIL"); studBean.setGrade("No Grade"); }

            String status = dao.addCandidate(studBean);
            if (status.equals("SUCCESS"))
                return studBean.getId() + ":" + studBean.getResult();
            else
                return "Error";

        } catch (WrongDataException e) {
            return e.toString();
        }
		
	}
	public ArrayList<CandidateBean> displayAll(String criteria)
	{
		try {
            if (!(criteria.equalsIgnoreCase("PASS") || criteria.equalsIgnoreCase("FAIL") || criteria.equalsIgnoreCase("ALL")))
                throw new WrongDataException();

            CandidateDAO dao = new CandidateDAO();
            return dao.getByResult(criteria);

        } catch (WrongDataException e) {
            return null;
        }
		
	}
	public static void main(String[] args) {
		//write code here
		
		//Test Case 1
		/*CandidateMain candidateMain = new CandidateMain();  
        String result = candidateMain.addCandidate(null);
        System.out.println(result);*/
		
		//Test Case 2
		/*CandidateMain candidateMain = new CandidateMain();
	    // Create CandidateBean with empty name
	    CandidateBean cand = new CandidateBean();
	    cand.setName("");   // empty name (invalid)
	    cand.setM1(50);
	    cand.setM2(60);
	    cand.setM3(70);

	    String result = candidateMain.addCandidate(cand);
	    System.out.println(result);   // Expected Output: Data Incorrect*/
		
		
		//Test Case 3
		/*CandidateMain candidateMain = new CandidateMain();
	    // Create CandidateBean with name length < 2 (invalid case)
	    CandidateBean cand = new CandidateBean();
	    cand.setName("A");   // only 1 character
	    cand.setM1(80);
	    cand.setM2(70);
	    cand.setM3(60);

	    String result = candidateMain.addCandidate(cand);
	    System.out.println(result);   // Expected Output: Data Incorrect*/
		
		//Test Case 4
		/*CandidateMain candidateMain = new CandidateMain();
	    // Create CandidateBean with invalid marks
	    CandidateBean cand = new CandidateBean();
	    cand.setName("John");  // valid name
	    cand.setM1(120);       // Invalid (greater than 100)
	    cand.setM2(85);        // valid
	    cand.setM3(70);        // valid

	    String result = candidateMain.addCandidate(cand);
	    System.out.println(result);   // Expected Output: Data Incorrect*/
		
		//Test Case 5
		/*
		CandidateMain candidateMain = new CandidateMain();
	    // Create CandidateBean with valid details
	    CandidateBean cand = new CandidateBean();
	    cand.setName("Jacob");
	    cand.setM1(85);
	    cand.setM2(90);
	    cand.setM3(80);

	    String result = candidateMain.addCandidate(cand);
	    System.out.println(result); */ 
		
		//Test Case 6
		/*
		
		 CandidateMain candidateMain = new CandidateMain();

		    // Candidate 1: Distinction
		    CandidateBean c1 = new CandidateBean();
		    c1.setName("Ramesh");
		    c1.setM1(90);
		    c1.setM2(80);
		    c1.setM3(80);   // Total = 250
		    System.out.println(candidateMain.addCandidate(c1));
		    // Expected: RA####:PASS (Distinction)

		    // Candidate 2: First Class
		    CandidateBean c2 = new CandidateBean();
		    c2.setName("Suresh");
		    c2.setM1(70);
		    c2.setM2(60);
		    c2.setM3(60);   // Total = 190
		    System.out.println(candidateMain.addCandidate(c2));
		    // Expected: SU####:PASS (First Class)

		    // Candidate 3: Second Class
		    CandidateBean c3 = new CandidateBean();
		    c3.setName("John");
		    c3.setM1(60);
		    c3.setM2(50);
		    c3.setM3(50);   // Total = 160
		    System.out.println(candidateMain.addCandidate(c3));
		    // Expected: JO####:PASS (Second Class)

		    // Candidate 4: Third Class
		    CandidateBean c4 = new CandidateBean();
		    c4.setName("David");
		    c4.setM1(40);
		    c4.setM2(35);
		    c4.setM3(35);   // Total = 110
		    System.out.println(candidateMain.addCandidate(c4));
		    // Expected: DA####:PASS (Third Class)

		    // Candidate 5: Fail
		    CandidateBean c5 = new CandidateBean();
		    c5.setName("Amit");
		    c5.setM1(30);
		    c5.setM2(20);
		    c5.setM3(40);   // Total = 90
		    System.out.println(candidateMain.addCandidate(c5));
		    // Expected: AM####:FAIL (No Grade)*/
		
		//Test Case 7 Scenario 1
		/*CandidateMain candidateMain = new CandidateMain();
	    // --- Display all PASS candidates ---
	    System.out.println("=== PASS Candidates ===");
	    ArrayList<CandidateBean> passList = candidateMain.displayAll("PASS");
	    if (passList != null) {
	        for (CandidateBean c : passList) {
	            System.out.println(c.getId() + " | " + c.getName() + " | " + c.getResult() + " | " + c.getGrade());
	        }
	    } else {
	        System.out.println("Invalid Criteria");
	    }

	    // --- Display all FAIL candidates ---
	    System.out.println("\n=== FAIL Candidates ===");
	    ArrayList<CandidateBean> failList = candidateMain.displayAll("FAIL");
	    if (failList != null) {
	        for (CandidateBean c : failList) {
	            System.out.println(c.getId() + " | " + c.getName() + " | " + c.getResult() + " | " + c.getGrade());
	        }
	    } else {
	        System.out.println("Invalid Criteria");
	    }

	    // --- Display ALL candidates ---
	    System.out.println("\n=== ALL Candidates ===");
	    ArrayList<CandidateBean> allList = candidateMain.displayAll("ALL");
	    if (allList != null) {
	        for (CandidateBean c : allList) {
	            System.out.println(c.getId() + " | " + c.getName() + " | " + c.getResult() + " | " + c.getGrade());
	        }
	    } else {
	        System.out.println("Invalid Criteria");
	    }*/
		
		//Test Case 7 Scenario 2
		
		/* CandidateMain candidateMain = new CandidateMain();

		    // Display only PASS candidates
		    System.out.println("=== PASS Candidates ===");
		    ArrayList<CandidateBean> passList = candidateMain.displayAll("PASS");
		    if (passList != null) {
		        for (CandidateBean c : passList) {
		            System.out.println(c.getId() + " " + c.getName() + " " + c.getResult() + " " + c.getGrade());
		        }
		    }

		    // Display only FAIL candidates
		    System.out.println("\n=== FAIL Candidates ===");
		    ArrayList<CandidateBean> failList = candidateMain.displayAll("FAIL");
		    if (failList != null) {
		        for (CandidateBean c : failList) {
		            System.out.println(c.getId() + " " + c.getName() + " " + c.getResult() + " " + c.getGrade());
		        }
		    }

		    // Display ALL candidates
		    System.out.println("\n=== ALL Candidates ===");
		    ArrayList<CandidateBean> allList = candidateMain.displayAll("ALL");
		    if (allList != null) {
		        for (CandidateBean c : allList) {
		            System.out.println(c.getId() + " " + c.getName() + " " + c.getResult() + " " + c.getGrade());
		        }
		    }*/
		
		//Test case 8
		/*CandidateMain candidateMain = new CandidateMain();
	    // Test invalid criteria
	    System.out.println("=== Invalid Criteria Test ===");
	    ArrayList<CandidateBean> list = candidateMain.displayAll("XYZ"); // Invalid

	    if (list == null) {
	        System.out.println("Data Incorrect");  // Expected output
	    } else {
	        for (CandidateBean c : list) {
	            System.out.println(c.getId() + " " + c.getName());
	        }
	    }*/
		
		// All Test Case at once
		CandidateMain candidateMain = new CandidateMain();

        // ---------- TEST CASE 1 ----------
        System.out.println("Test Case 1: Candidate is null");
        String result1 = candidateMain.addCandidate(null);
        System.out.println("Output: " + result1 + "\n");

        // ---------- TEST CASE 2 ----------
        System.out.println("Test Case 2: Candidate name empty");
        CandidateBean c2 = new CandidateBean();
        c2.setName("");
        c2.setM1(50); c2.setM2(60); c2.setM3(70);
        String result2 = candidateMain.addCandidate(c2);
        System.out.println("Output: " + result2 + "\n");

        // ---------- TEST CASE 3 ----------
        System.out.println("Test Case 3: Candidate name < 2 chars");
        CandidateBean c3 = new CandidateBean();
        c3.setName("A");
        c3.setM1(80); c3.setM2(70); c3.setM3(60);
        String result3 = candidateMain.addCandidate(c3);
        System.out.println("Output: " + result3 + "\n");

        // ---------- TEST CASE 4 ----------
        System.out.println("Test Case 4: Invalid Marks");
        CandidateBean c4 = new CandidateBean();
        c4.setName("John");
        c4.setM1(120); c4.setM2(85); c4.setM3(70);
        String result4 = candidateMain.addCandidate(c4);
        System.out.println("Output: " + result4 + "\n");

        // ---------- TEST CASE 5 ----------
        System.out.println("Test Case 5: Correct CandidateId generation");
        CandidateBean c5 = new CandidateBean();
        c5.setName("Jacob");
        c5.setM1(85); c5.setM2(90); c5.setM3(80);
        String result5 = candidateMain.addCandidate(c5);
        System.out.println("Output: " + result5 + " (PASS expected)\n");

        // ---------- TEST CASE 6 ----------
        System.out.println("Test Case 6: PASS/FAIL and Grade Calculation");
        CandidateBean c6a = new CandidateBean();
        c6a.setName("Ramesh"); c6a.setM1(90); c6a.setM2(80); c6a.setM3(80); // 250 Distinction
        System.out.println(candidateMain.addCandidate(c6a));

        CandidateBean c6b = new CandidateBean();
        c6b.setName("Suresh"); c6b.setM1(70); c6b.setM2(60); c6b.setM3(60); // 190 First Class
        System.out.println(candidateMain.addCandidate(c6b));

        CandidateBean c6c = new CandidateBean();
        c6c.setName("John"); c6c.setM1(60); c6c.setM2(50); c6c.setM3(50);   // 160 Second Class
        System.out.println(candidateMain.addCandidate(c6c));

        CandidateBean c6d = new CandidateBean();
        c6d.setName("David"); c6d.setM1(40); c6d.setM2(35); c6d.setM3(35); // 110 Third Class
        System.out.println(candidateMain.addCandidate(c6d));

        CandidateBean c6e = new CandidateBean();
        c6e.setName("Amit"); c6e.setM1(30); c6e.setM2(20); c6e.setM3(40);  // 90 FAIL
        System.out.println(candidateMain.addCandidate(c6e) + "\n");

        // ---------- TEST CASE 7 ----------
        System.out.println("Test Case 7: Display by Criteria");
        System.out.println("=== PASS Candidates ===");
        ArrayList<CandidateBean> passList = candidateMain.displayAll("PASS");
        if (passList != null) {
            for (CandidateBean c : passList)
                System.out.println(c.getId() + " " + c.getName() + " " + c.getResult() + " " + c.getGrade());
        }

        System.out.println("\n=== FAIL Candidates ===");
        ArrayList<CandidateBean> failList = candidateMain.displayAll("FAIL");
        if (failList != null) {
            for (CandidateBean c : failList)
                System.out.println(c.getId() + " " + c.getName() + " " + c.getResult() + " " + c.getGrade());
        }

        System.out.println("\n=== ALL Candidates ===");
        ArrayList<CandidateBean> allList = candidateMain.displayAll("ALL");
        if (allList != null) {
            for (CandidateBean c : allList)
                System.out.println(c.getId() + " " + c.getName() + " " + c.getResult() + " " + c.getGrade());
        }
        System.out.println();

        // ---------- TEST CASE 8 ----------
        System.out.println("Test Case 8: Invalid criteria in displayAll()");
        ArrayList<CandidateBean> invalidList = candidateMain.displayAll("XYZ");
        if (invalidList == null) {
            System.out.println("Output: Data Incorrect");
        }
	}

}
