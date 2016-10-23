package studentController;

import java.sql.*;
import java.util.ArrayList;

import studentModel.StudentBean;

public class StudentDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;

	public static ArrayList<StudentBean> fetch(StudentBean bean) {

		PreparedStatement stmt = null;
		ArrayList<StudentBean> al = null;

		String deptName = bean.getDept_name();
		String totalCredits = bean.getTotal_credits();

		String searchQuery = "select * from student where dept_name=? and tot_cred=?";

		try {
			// connect to DB
			// using prepared statement
			currentCon = DBConnection.getConnection();
			stmt = currentCon.prepareStatement(searchQuery);
			stmt.setString(1, deptName);
			stmt.setString(2, totalCredits);

			rs = stmt.executeQuery();

			al = new ArrayList<StudentBean>();

			while (rs.next()) {

				bean = new StudentBean();
				bean.setId(rs.getString("ID"));
				bean.setDept_name(rs.getString("dept_name"));
				bean.setName(rs.getString("name"));
				bean.setTotal_credits(rs.getString("tot_cred"));

				al.add(bean);
			}
		}

		catch (Exception ex) {
			System.out
					.println("Fetching the students details failed: An Exception has occurred! "
							+ ex);
		}

		// some exception handling
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				stmt = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				currentCon = null;
			}
		}

		return al;

	}
}