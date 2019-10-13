package com.revature.repositories;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.omg.CORBA.Environment;

import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.StreamCloser;

public class TransactionsDAOImplPJDBC implements TransactionsDAO {

	public Transaction getTransaction(long trsId) {
		
		Transaction t = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM Transactions WHERE id = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, trsId);
				if (stmt.execute()) {
					try (ResultSet rs = stmt.getResultSet()) {
						if (rs.next()) {
							t = createTransactionFromRS(rs);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return t;
	}
	
	@Override
	public List<Transaction> getTransactions(long userId) {

		List<Transaction> trs = new ArrayList<Transaction>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM Transactions WHERE user_id = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, userId);
				if (stmt.execute()) {
					try (ResultSet rs = stmt.getResultSet()) {
						while (rs.next()) {
							trs.add(createTransactionFromRS(rs));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trs;
	}

	public List<Transaction> getTrsPending(long userId) {
		
		List<Transaction> trs = new ArrayList<Transaction>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM Transactions WHERE user_id = ? AND status = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, userId);
				stmt.setString(2, "pending");
				if (stmt.execute()) {
					try (ResultSet rs = stmt.getResultSet()) {
						while (rs.next()) {
							trs.add(createTransactionFromRS(rs));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trs;
	}

public List<Transaction> getTrsAllPending() {
		
		List<Transaction> trs = new ArrayList<Transaction>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM Transactions WHERE status = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, "pending");
				if (stmt.execute()) {
					try (ResultSet rs = stmt.getResultSet()) {
						while (rs.next()) {
							trs.add(createTransactionFromRS(rs));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trs;
	}
	
	public List<Transaction> getTrsApproved(long userId) {

		List<Transaction> trs = new ArrayList<Transaction>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM Transactions WHERE user_id = ? AND status = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, userId);
				stmt.setString(2, "approved");
				if (stmt.execute()) {
					try (ResultSet rs = stmt.getResultSet()) {
						while (rs.next()) {
							trs.add(createTransactionFromRS(rs));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trs;
	}
	
	public List<Transaction> getTrsAllApproved() {

		List<Transaction> trs = new ArrayList<Transaction>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM Transactions WHERE status = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, "approved");
				if (stmt.execute()) {
					try (ResultSet rs = stmt.getResultSet()) {
						while (rs.next()) {
							trs.add(createTransactionFromRS(rs));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trs;
	}

	public List<Transaction> getTrsAllDenied() {

		List<Transaction> trs = new ArrayList<Transaction>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM Transactions WHERE status = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, "denied");
				if (stmt.execute()) {
					try (ResultSet rs = stmt.getResultSet()) {
						while (rs.next()) {
							trs.add(createTransactionFromRS(rs));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trs;
	}

	public List<Transaction> getTrsDenied(long userId) {

		List<Transaction> trs = new ArrayList<Transaction>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM Transactions WHERE user_id = ? AND status = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, userId);
				stmt.setString(2, "denied");
				if (stmt.execute()) {
					try (ResultSet rs = stmt.getResultSet()) {
						while (rs.next()) {
							trs.add(createTransactionFromRS(rs));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trs;
	}

	public List<Transaction> getTransactions() {

		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		List<Transaction> trs = new ArrayList<Transaction>();

		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Transaction;");

			while (rs.next()) {
				trs.add(createTransactionFromRS(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trs;
	}

	private Transaction createTransactionFromRS(ResultSet rs) throws SQLException {

		return new Transaction(rs.getLong("id"), rs.getLong("user_id"), rs.getString("status"),
				rs.getString("manager_name"), rs.getString("trans_date"), rs.getString("imgUrl"),
				rs.getString("comment"));
	}

	public boolean createTransaction(String comment, long id) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		Date date= new Date();
		long time = date. getTime();
		Timestamp ts = new Timestamp(time);
		
		String query = "INSERT INTO Transactions VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setLong(1, id);
			stmt.setString(2, "pending");
			stmt.setString(3, "");
			stmt.setTimestamp(4, ts);
			stmt.setString(5, "");
			stmt.setString(6, comment);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
		return true;
	}
	
//	public byte[] getImage(int id) {
//		byte[] byteImg = null;
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = ConnectionUtil.getConnection();
//			stmt = conn.prepareStatement("SELECT * FROM Trimg WHERE id = ?;");
//
//			stmt.setInt(1, id);
//			stmt.execute();
//			rs = stmt.getResultSet();
//
//			while (rs.next()) {
//				byteImg = rs.getBytes(1);
//			}
//
//			return byteImg;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		} finally {
//			StreamCloser.close(conn);
//			StreamCloser.close(stmt);
//			StreamCloser.close(rs);
//		}
//	}

	public byte[] getImage1(long id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		imageTest imgt = null;

		try {
			conn = ConnectionUtil.getConnection();
			String query = "SELECT * FROM Trimg WHERE id = ?;";
			stmt = conn.prepareStatement(query);
			stmt.setLong(1, id);
			stmt.execute();
			rs = stmt.getResultSet();

			
			if (rs.next()) {
				imgt = createimageFromRS(rs);
			}


			byte[] image = imgt.getImg();

			return image;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public void addImage(byte[] img, int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			String query = "INSERT INTO Trimg VALUES (DEFAULT, ?, ?);";
			stmt = conn.prepareStatement(query);
			stmt.setLong(1, id);
			stmt.setBytes(2, img);
			stmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
			StreamCloser.close(rs);
		}
	}

//	@Override
//	public boolean createTransaction(Transaction trs, long userid) {
//
//		Connection conn = null;
//		PreparedStatement stmt = null;
//
//		String query = "INSERT into Transaction VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);";
//
//		try {
//			conn = ConnectionUtil.getConnection();
//			stmt = conn.prepareStatement(query);
//			stmt.setLong(1, trs.getUserId());
//			stmt.setString(2, trs.getStatus());
//			stmt.setString(3, trs.getManagerName());
//			stmt.setString(4, trs.getTransDate());
//			stmt.setString(5, trs.getImgUrl());
//			stmt.setString(6, trs.getComment());
//			stmt.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			StreamCloser.close(conn);
//			StreamCloser.close(stmt);
//		}
//
//		return true;
//
//	}

	@Override
	public boolean updateTransaction(Transaction trs, String name) {

		Connection conn = null;
		PreparedStatement stmt = null;
		Date date= new Date();
		long time = date. getTime();
		Timestamp ts = new Timestamp(time);


		String query = "UPDATE Transactions SET status = ?, manager_name = ?, trans_date = ?, imgUrl = ?, comment = ? WHERE id = ?;";
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, trs.getStatus());
			stmt.setString(2, name);
			stmt.setTimestamp(3, Timestamp.valueOf(trs.getTransDate()));
			stmt.setString(4, trs.getImgUrl());
			stmt.setString(5, trs.getComment());
			stmt.setLong(6, trs.getId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}

		return true;
	}


//	@Override
//	public byte[] fileToBytes(File file) {
//		
//		try {
//			FileInputStream fis = new FileInputStream(file);
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			byte[] buf = new byte[1024];
//			
//			try {
//				for (int i; (i = fis.read(buf)) != -1;) {
//					bos.write(buf, 0, i);
//					System.out.println("read" + i + " bytes, ");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			byte[] bytes = bos.toByteArray();
//			return bytes;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//	}

//	@Override
//	public File bytesToFile(byte[] imgByte) throws FileNotFoundException {
//		File imgFile = new File("C:\\Users\\Mamba\\Desktop\\image.pdf");
////		FileOutputStream output = new FileOutputStream(imgFile);
//		FileInputStream input = new FileInputStream(imgFile);
//		input.read(imgByte);
//		
////		byte [] fileBytes = imgByte;
////		
////		try {
////			output.write(imgByte);
////		} catch (Exception e) {
////			e.printStackTrace();
////		} 
//			
//		
//		return imgFile;
//	}
//	
//    @SuppressWarnings("unused")
//	private static void writeBytesToFile(byte[] bFile, String fileDest) {
//
//        try (FileOutputStream fileOuputStream = new FileOutputStream(fileDest)) {
//            fileOuputStream.write(bFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

}
