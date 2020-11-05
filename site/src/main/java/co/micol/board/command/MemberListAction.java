package co.micol.board.command;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import co.micol.board.common.Action;
import co.micol.board.dao.MemberDAO;
import co.micol.board.vo.MemberVO;

public class MemberListAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원리스트 보기 구현
		MemberDAO dao= new MemberDAO();
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		list= dao.selectAll();
		request.setAttribute("members", list);
		
		return "jsp/member/memberList.jsp";
	}

}
