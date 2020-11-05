package site;

import java.util.List;

import org.json.JSONArray;
import org.junit.Test;

import co.micol.board.dao.MemberDAO;
import co.micol.board.vo.MemberVO;

public class JsonText {

	@Test
	public void jsonobject() {

		MemberDAO dao = new MemberDAO();
//		MemberVO vo = new MemberVO();
//		vo.setFirst(1);
//		vo.setLast(5);
		List<MemberVO> list = dao.selectAll();
		JSONArray arr = new JSONArray(list);
		System.out.println(arr);
	}

}
