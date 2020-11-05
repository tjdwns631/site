package co.micol.board.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import co.micol.board.common.Action;
import co.micol.board.dao.MemberDAO;
import co.micol.board.vo.MemberVO;

public class AjaxMemberList implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.selectAll();
		JSONArray arr = new JSONArray(list);
		try {
			response.getWriter().print(arr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
