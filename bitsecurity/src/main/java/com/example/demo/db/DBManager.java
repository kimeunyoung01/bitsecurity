package com.example.demo.db;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.MemberVo;
import com.example.demo.vo.TransferVo;


public class DBManager {
	private static SqlSessionFactory factory;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/example/demo/db/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	//계좌이체를 위한 메소드를 정의해요
	public static int transfer(TransferVo t) {
		int re = 0;
		SqlSession session = factory.openSession(false);
		re += session.update("account.withdraw",t);
		re += session.update("account.deposit",t);
		
		if( re == 2) {
			session.commit();
		}else {
			session.rollback();
		}
		
		return re;
	}
	
	
	
	public static List<MemberVo> selectMemberAll(){
		SqlSession session = factory.openSession();
		List<MemberVo> list  = session.selectList("member.selectAll");
		session.close();
		return list;
	}
	
	public static MemberVo selectMember(String username){
		SqlSession session = factory.openSession();
		MemberVo m = session.selectOne("member.selectMember",username);
		session.close();
		return m;
	}

	public static int insertMember(MemberVo m) {
		int re =  -1;
		SqlSession session = factory.openSession();
		re= session.insert("member.insert",m);
		session.commit();
		session.close();
		return re;
	}
}
















