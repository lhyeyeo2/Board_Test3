package jsboard;
import java.sql.*;
import java.util.*;

public class MemberMgr {
	private DBConnectionMgr pool;
	
	public MemberMgr() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkId(String id) {
		boolean flag=false;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		try {
			conn=pool.getConnection();
			sql="select id from tblmember where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			flag = pstmt.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt);
		}
		return flag;
	}

	public Vector<ZipcodeBean> zipcodeRead(String area3) {
		Vector<ZipcodeBean> vlist = new Vector<ZipcodeBean>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql=null;
		try {
			conn=pool.getConnection();
			sql="select * from tblzipcode where AREA3 like ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+area3+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ZipcodeBean bean=new ZipcodeBean();
				bean.setZipcode(rs.getString(1));
				bean.setArea1(rs.getString(2));
				bean.setArea2(rs.getString(3));
				bean.setArea3(rs.getString(4));
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt, rs);
		}
		return vlist;
	}
	
	public boolean insertMember(MemberBean bean) {
		boolean flag=false;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		try {
			conn=pool.getConnection();
			sql="insert into tblmember (id,pwd,name,gender,birthday,email,zipcode,address,hobby,job) "
					+ " values (?,?,?,?,?,?,?,?,?,?) ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPwd());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getBirthday());
			pstmt.setString(6, bean.getEmail());
			pstmt.setString(7, bean.getZipcode());
			pstmt.setString(8, bean.getAddress());
			String hobby[] = bean.getHobby();
			char hb[] = { '0', '0', '0', '0', '0' };
			String lists[] = { "인터넷", "여행", "게임", "영화", "운동" };
			for (int i = 0; i < hobby.length; i++) {
				for (int j = 0; j < lists.length; j++) {
					if (hobby[i].equals(lists[j]))
						hb[j] = '1';
				}
			}
			pstmt.setString(9, new String(hb));
			pstmt.setString(10, bean.getJob());
			if (pstmt.executeUpdate() == 1)
				flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt);
		}
		return flag;
	}
	
	public boolean loginMember(String id, String pwd) {
		boolean flag=false;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;
		try {
			conn=pool.getConnection();
			sql="select id from tblmember where id=? and pwd=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt, rs);
		}
		return flag;
	}
	
	public Vector<MemberBean> membersInfo() {
		Vector<MemberBean> vlist = new Vector<MemberBean>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql=null;
		String admin = "admin";
		try {
			conn=pool.getConnection();
			sql="select * from tblmember where id<>?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, admin);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberBean bean=new MemberBean();
				bean.setId(rs.getString(1));
				bean.setPwd(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setBirthday(rs.getString(5));
				bean.setEmail(rs.getString(6));
				bean.setZipcode(rs.getString(7));
				bean.setAddress(rs.getString(8));
				//bean.setHobby(rs.getString(9));
				bean.setJob(rs.getString(10));
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt, rs);
		}
		return vlist;
	}
	
}
























