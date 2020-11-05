package co.micol.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import co.micol.board.common.Action;
import co.micol.board.dao.MemberDAO;
import co.micol.board.vo.MemberVO;

public class LoginAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그인 인증과정을 처리한다.
		MemberDAO dao=new MemberDAO();
		MemberVO vo=new MemberVO();
		HttpSession session = request.getSession(false);
		vo.setId(request.getParameter("id"));
		vo.setPassword(request.getParameter("password"));
		String msg;
		
		vo =dao.select(vo);//MemberDAO 실행시킴
		if(vo.getPassword().equals(request.getParameter("password"))) {
		session.setAttribute("id", vo.getId());//세션에 id넣음
		session.setAttribute("author", vo.getAuthor());//세션에 권한 넣음
		session.setAttribute("name", vo.getName());
		msg="정상적인 로그인";
		request.setAttribute("msg", msg);
		request.setAttribute("vo", vo);//멤버를 실어 보냄
		
		}else {
			msg="패스워드 틀렸습니다";
			request.setAttribute("msg", msg);
		}
		return "jsp/main/loginResult.jsp";
	}

}
