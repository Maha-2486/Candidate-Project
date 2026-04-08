package com.wipro.candidate.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.util.DBUtil;


public class CandidateDAO {
	public String addCandidate(CandidateBean studentBean)
	{
			String status="FAIL";
			//write code here
			 Connection con = null;
		        PreparedStatement ps = null;

		        try {
		            con = DBUtil.getDBConn();
		            ps = con.prepareStatement("INSERT INTO candidate VALUES(?,?,?,?,?,?,?)");
		            ps.setString(1, studentBean.getId());
		            ps.setString(2, studentBean.getName());
		            ps.setInt(3, studentBean.getM1());
		            ps.setInt(4, studentBean.getM2());
		            ps.setInt(5, studentBean.getM3());
		            ps.setString(6, studentBean.getResult());
		            ps.setString(7, studentBean.getGrade());

		            int rows = ps.executeUpdate();
		            if (rows > 0) status = "SUCCESS";

		        } catch (Exception e) {
		            status = "FAIL";
		            e.printStackTrace();
		        } finally {
		            try { if (ps != null) ps.close(); if (con != null) con.close(); } catch (Exception e) {}
		        }
			return status;
	}
	public ArrayList<CandidateBean> getByResult(String criteria)
	{
		ArrayList<CandidateBean> list=new ArrayList<CandidateBean>();
		//write code here
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getDBConn();
            String query = "SELECT * FROM candidate";
            if (criteria.equalsIgnoreCase("PASS"))
                query += " WHERE RESULT='PASS'";
            else if (criteria.equalsIgnoreCase("FAIL"))
                query += " WHERE RESULT='FAIL'";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                CandidateBean c = new CandidateBean();
                c.setId(rs.getString(1));
                c.setName(rs.getString(2));
                c.setM1(rs.getInt(3));
                c.setM2(rs.getInt(4));
                c.setM3(rs.getInt(5));
                c.setResult(rs.getString(6));
                c.setGrade(rs.getString(7));
                list.add(c);
            }

        } catch (Exception e) {
            return null;
        } finally {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); if (con != null) con.close(); } catch (Exception e) {}
        }
		return list;
	}
	public String generateCandidateId (String name)
	{
		String id=null;
		//write code here
		 Connection con = null;
	        Statement st = null;
	        ResultSet rs = null;

	        try {
	            con = DBUtil.getDBConn();
	            st = con.createStatement();
	            rs = st.executeQuery("SELECT CANDID_SEQ.NEXTVAL FROM DUAL");
	            if (rs.next()) {
	                int num = rs.getInt(1);
	                id = name.substring(0, 2).toUpperCase() + num;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try { if (rs != null) rs.close(); if (st != null) st.close(); if (con != null) con.close(); } catch (Exception e) {}
	        }
		
		return id;
	}
}
