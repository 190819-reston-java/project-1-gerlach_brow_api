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
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.Environment;

import com.revature.model.Transaction;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.StreamCloser;

public class TransactionsDAOImplPJDBC implements TransactionsDAO {

	public Transaction getTransaction(long id) {
		
		return null;
	}

	public List<Transaction> getTransactions() {
		
	Statement stmt = null;
	ResultSet rs = null;
	Connection conn = null;
	
	List<Transaction> trs = new ArrayList<Transaction>();
	
	try{
		conn = ConnectionUtil.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Transaction;");
		
		while (rs.next()) {
			trs.add(createTransactionFromRS(rs));
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
		
		return null;
	}

	private Transaction createTransactionFromRS(ResultSet rs) {
		
		return null;
//		return new Transaction(
//				rs.getLong("id"),
//				rs.getLong("userId"),
//				rs.getString("status"),
//				rs.getString("managerName"),
//				rs.getString("transDate"),
//				rs.get("receiptImg"));
	}
	
	public byte[] getImage(int id) {
		byte[] byteImg = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Trimg WHERE id = ?;");
			
			stmt.setInt(1, id);
			stmt.execute();
			rs = stmt.getResultSet();
			
			while(rs.next()) {
				byteImg = rs.getBytes(1);
			}
			
			return byteImg;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
			StreamCloser.close(rs);
		}
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
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
			StreamCloser.close(rs);
		}
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
