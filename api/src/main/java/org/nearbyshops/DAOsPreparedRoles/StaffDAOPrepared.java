package org.nearbyshops.DAOsPreparedRoles;

import com.zaxxer.hikari.HikariDataSource;
import org.nearbyshops.Globals.Globals;
import org.nearbyshops.ModelRoles.Staff;

import java.sql.*;
import java.util.ArrayList;


public class StaffDAOPrepared {


	private HikariDataSource dataSource = Globals.getDataSource();


	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}


	public int saveStaff(Staff staff) {

		Connection connection = null;
		PreparedStatement statement = null;
		int rowIdOfInsertedRow = -1;

		String insert = "INSERT INTO "
				+ Staff.TABLE_NAME
				+ "("
				+ Staff.STAFF_NAME + ","
				+ Staff.USER_NAME + ","
				+ Staff.PASSWORD + ","
				+ Staff.IS_ENABLED + ","
				+ Staff.IS_WAITLISTED + ""
				+ ") VALUES(?,?,?,?,?)";

		try {

			connection = dataSource.getConnection();

			statement = connection.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);

			statement.setString(1, staff.getStaffName());
			statement.setString(2, staff.getUsername());
			statement.setString(3, staff.getPassword());
			statement.setObject(4,staff.getEnabled());
			statement.setObject(5,staff.getWaitlisted());

			rowIdOfInsertedRow = statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				rowIdOfInsertedRow = rs.getInt(1);
			}


			System.out.println("Key autogenerated SaveDistributor: " + rowIdOfInsertedRow);


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return rowIdOfInsertedRow;
	}


	public int updateStaff(Staff staff) {
		String updateStatement = "UPDATE " + Staff.TABLE_NAME
				+ " SET "
				+ Staff.STAFF_NAME + " = ?,"
				+ Staff.USER_NAME + " = ?,"
				+ Staff.PASSWORD + " = ?,"
				+ Staff.IS_ENABLED + " = ?,"
				+ Staff.IS_WAITLISTED + " = ?"
				+ " WHERE " + Staff.STAFF_ID + " = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		int updatedRows = -1;

		try {

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(updateStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			statement.setString(1, staff.getStaffName());
			statement.setString(2, staff.getUsername());
			statement.setString(3, staff.getPassword());
			statement.setObject(4,staff.getEnabled());
			statement.setObject(5,staff.getWaitlisted());
			statement.setInt(6, staff.getStaffID());

			updatedRows = statement.executeUpdate();


			System.out.println("Total rows updated: " + updatedRows);

			//conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{

			try {

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return updatedRows;

	}


	public int deleteStaff(int staffID) {

		String deleteStatement = "DELETE FROM " + Staff.TABLE_NAME
				+ " WHERE "
				+ Staff.STAFF_ID + " = ?";


		Connection connection = null;
		PreparedStatement statement = null;
		int rowsCountDeleted = 0;
		try {

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(deleteStatement);

			statement.setInt(1, staffID);

			rowsCountDeleted = statement.executeUpdate();

			System.out.println(" Deleted Count: " + rowsCountDeleted);

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{

			try {

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return rowsCountDeleted;
	}


	public ArrayList<Staff> getStaff() {
		String query = "SELECT * FROM " + Staff.TABLE_NAME;
		ArrayList<Staff> staffList = new ArrayList<Staff>();


		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {

			connection = dataSource.getConnection();
			statement = connection.createStatement();

			rs = statement.executeQuery(query);

			while (rs.next()) {

				Staff staff = new Staff();

				staff.setStaffID(rs.getInt(Staff.STAFF_ID));
				staff.setStaffName(rs.getString(Staff.STAFF_NAME));
				staff.setUsername(rs.getString(Staff.USER_NAME));
				staff.setPassword(rs.getString(Staff.PASSWORD));
				staff.setEnabled(rs.getBoolean(Staff.IS_ENABLED));
				staff.setWaitlisted(rs.getBoolean(Staff.IS_WAITLISTED));
				staffList.add(staff);

			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return staffList;
	}


	public Staff getStaff(int staffID) {

		String query = "SELECT * FROM " + Staff.TABLE_NAME
				+ " WHERE " + Staff.STAFF_ID + " = " + staffID;

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;


		//Distributor distributor = null;
		Staff staff = null;

		try {

			connection = dataSource.getConnection();
			statement = connection.createStatement();

			rs = statement.executeQuery(query);

			while (rs.next()) {

				staff = new Staff();

				staff.setStaffID(rs.getInt(Staff.STAFF_ID));
				staff.setStaffName(rs.getString(Staff.STAFF_NAME));
				staff.setUsername(rs.getString(Staff.USER_NAME));
				staff.setPassword(rs.getString(Staff.PASSWORD));
				staff.setEnabled(rs.getBoolean(Staff.IS_ENABLED));
				staff.setWaitlisted(rs.getBoolean(Staff.IS_WAITLISTED));

			}


			//System.out.println("Total itemCategories queried " + itemCategoryList.size());	


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return staff;
	}


	public void logMessage(String message)
	{
		System.out.println(message);
	}



	public Staff checkStaff(Integer staffID,String username, String password)
	{


		logMessage("Checking Staff");


		boolean isFirst = true;

		String query = "SELECT * FROM " + Staff.TABLE_NAME;

		if(staffID!=null)
		{
			query = query + " WHERE " + Staff.STAFF_ID + " = " + staffID;

			isFirst = false;
		}

		if(username!=null)
		{
			String queryPartUsername = Staff.USER_NAME + " = '" + username + "'";

			if(isFirst)
			{
				query = query + " WHERE " + queryPartUsername;

				isFirst = false;
			}
			else
			{
				query = query + " AND " + queryPartUsername;
			}
		}


		if(password!=null)
		{
			String queryPartPassword = Staff.PASSWORD + " = '" + password + "'";

			if(isFirst)
			{
				query = query + " WHERE " + queryPartPassword;
			}
			else
			{
				query = query + " AND " + queryPartPassword;
			}
		}


		logMessage(query);


		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;


		//Distributor distributor = null;
		Staff staff = null;

		try {

			connection = dataSource.getConnection();
			statement = connection.createStatement();

			rs = statement.executeQuery(query);

			while(rs.next())
			{
				staff = new Staff();

				staff.setStaffID(rs.getInt(Staff.STAFF_ID));
				staff.setStaffName(rs.getString(Staff.STAFF_NAME));
				staff.setUsername(rs.getString(Staff.USER_NAME));
				staff.setPassword(rs.getString(Staff.PASSWORD));
				staff.setEnabled(rs.getBoolean(Staff.IS_ENABLED));
				staff.setWaitlisted(rs.getBoolean(Staff.IS_WAITLISTED));

			}


			//System.out.println("Total itemCategories queried " + itemCategoryList.size());



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{

			try {
				if(rs!=null)
				{rs.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return staff;
	}
}
