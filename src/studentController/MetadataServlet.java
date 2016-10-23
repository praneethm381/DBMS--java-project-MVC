package studentController;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MetadataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Connection con = DBConnection.getConnection();
			String table_name = request.getParameter("table-name");

			DatabaseMetaData metadata = con.getMetaData();
			out.print("<head><link rel='stylesheet' type='text/css' href='style.css'></head>");
			out.print("<body><div id='header'><h1>Database Application</h1></div>");
			out.print("<div id='content'>");
			out.print("<div id='nav'><br> <br> <br><center><form action='FetchPage.html'><table align='center'><tr><td><input type='submit' value='Fetch students'><br></td></tr></table></form><form action='Metadata.html'><table align='center'><tr><td><input type='submit' value='Get metadata'><br></td></tr></table></form></center></div>");
			out.print("<div id='section'>");
			out.print("<table align='center' height='100%'>");
			out.print("<tr>");
			out.print("<td>");
			out.print("<table align='center' border=1>");
			out.print("<center><h1>General Database Metadata</h1></center>");

			out.print("<tr>");
			out.print("<th>Product Name</th>");
			out.print("<th>Product Version</th>");
			out.print("<th>Logged User</th>");
			out.print("<th>JDBC Driver</th>");
			out.print("<th>Driver Version</th>");

			out.print("</tr>");

			out.print("<tr>");
			out.print("<td>" + metadata.getDatabaseProductName() + "</td>");
			out.print("<td>" + metadata.getDatabaseProductVersion() + "</td>");
			out.print("<td>" + metadata.getUserName() + "</td>");
			out.print("<td>" + metadata.getDriverName() + "</td>");
			out.print("<td>" + metadata.getDriverVersion() + "</td></tr>");

			out.print("</table>");
			

			columnsMetadata(tablesMetadata(metadata), metadata, out,table_name);

		} catch (Exception e2) {
			e2.printStackTrace();
		}

		finally {
			out.close();
		}
	}

	public static ArrayList<String> tablesMetadata(DatabaseMetaData metadata)
			throws SQLException {
		String table[] = { "TABLE" };
		ResultSet rs = null;
		ArrayList<String> tables = null;
		// receive the Type of the object in a String array.
		rs = metadata.getTables(null, null, null, table);
		tables = new ArrayList<String>();
		while (rs.next()) {
			tables.add(rs.getString("TABLE_NAME"));
		}
		return tables;
	}

	/**
	 * Prints in the console the columns metadata, based in the Arraylist of
	 * tables passed as parameter.
	 * 
	 * @param tables
	 * @throws SQLException
	 */
	public static void columnsMetadata(ArrayList<String> tables,
			DatabaseMetaData metadata, PrintWriter out,String table_name) throws SQLException {
		ResultSet rs = null;
		// Print the columns properties of the actual table
		
		if(table_name.equalsIgnoreCase("all")){
			//do nothing
		}else if(table_name.equalsIgnoreCase("student"))
		{
			tables.clear();
			tables.add(table_name);
			
		}else if(table_name.equalsIgnoreCase("department"))
		{
			tables.clear();
			tables.add(table_name);
		}else if(table_name.equalsIgnoreCase("instructor"))
		{
			tables.clear();
			tables.add(table_name);
		}
		
		
		for (String actualTable : tables) {
			rs = metadata.getColumns(null, null, actualTable, null);
			out.print("<table align='center' border=1>");
			out.print("<center><h1>Metadata for "+actualTable +" table</h1></center>");

			out.print("<tr>");
			out.print("<th>COLUMN_NAME</th>");
			out.print("<th>TYPE_NAME</th>");
			out.print("<th>COLUMN_SIZE</th>");
			out.print("</tr>");
			while (rs.next()) {
				out.print("<tr>");
				out.print("<td>" + rs.getString("COLUMN_NAME") + "</td>");
				out.print("<td>" + rs.getString("TYPE_NAME") + "</td>");
				out.print("<td>" + rs.getString("COLUMN_SIZE") + "</td></tr>");
			}
			out.print("</table>");
			out.print("<table align='center' border=1>");
			out.print("<center><h3>Keys</h3></center>");
			ResultSet primaryKeys = null;
			primaryKeys = metadata.getPrimaryKeys(null, null, actualTable);
			while (primaryKeys.next()) {
				String primaryKey = primaryKeys.getString("COLUMN_NAME");
				out.print("<tr>");
				out.print("<td> PRIMARY KEY </td><td>" + primaryKey + "</td></tr>");
			}

			ResultSet foreignKeys = null;
			foreignKeys = metadata.getExportedKeys(null, null, actualTable);
			while (foreignKeys.next()) {
				String foreignKey = foreignKeys.getString("FKCOLUMN_NAME");
				String fktablename = foreignKeys.getString("FKTABLE_NAME");
				out.print("<tr>");
				out.print("<td>FOREIGN KEY</td><td>" + foreignKey
						+ " as foreign key for " + fktablename+" table"
						+ "</td></tr>");
			}

			ResultSet foreignKeysimported = null;
			foreignKeysimported = metadata.getImportedKeys(null, null,
					actualTable);
			while (foreignKeysimported.next()) {
				String foreignKey = foreignKeysimported
						.getString("PKCOLUMN_NAME");
				String pktablename = foreignKeysimported
						.getString("PKTABLE_NAME");
				out.print("<tr>");
				out.print("<td>FOREIGN KEYS IMPORTED</td><td>" + foreignKey
						+ " is foreign key from " + pktablename+" table"
						+ "</td></tr>");
			}
			out.print("</table>");
			out.print("</td>");
			out.print("</tr>");
			out.print("</table>");
			
			
			
		}
		out.print("</div><br><br><br>");
		out.print("</div><br><br><br>");
		out.print("</body>");
		out.print("<div id='footer'><h3>Copyright © praneeth</h3></div>");
	}

}