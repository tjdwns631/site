package co.micol.board.command;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import co.micol.board.common.Action;
import co.micol.board.common.FileRenamePolicy;
import co.micol.board.common.FileUtil;
import co.micol.board.dao.MemberDAO;
import co.micol.board.vo.MemberVO;

public class MemberInsertAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원정보를 DB에 입력
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("password"));
		vo.setAddress(request.getParameter("address"));
		vo.setTel(request.getParameter("tel"));
		vo.setEnterdate(Date.valueOf(request.getParameter("enterdate")));
		// 첨부 파일 처리
		// 업로드 위치를 현재 컨텍스트에
		String appPath = request.getServletContext().getRealPath("/images");
		System.out.println(appPath);
		try {
			for (Part part : request.getParts()) {
//				Part part = request.getPart("file"); // 단건인 경우  name 속성 넣어주면됨
				String fileName = FileUtil.extractFileName(part);
				if (!fileName.equals("")) {
					// 파일명 중복체크
					String uploadFile = appPath + File.separator + fileName;
					File renameFile = FileRenamePolicy.rename(new File(uploadFile));
					part.write(renameFile.getAbsolutePath());
					vo.setImg(renameFile.getName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	

	int n = dao.insert(vo);
	String page;
	if(n!=0)
	{
		page = "jsp/member/insertSucess.jsp";
	}else
	{
		page = "jsp/member/insertFail.jsp";
	}return page;
}

}
