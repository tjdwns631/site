package co.micol.board.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.board.command.AjaxMemberInsertAction;
import co.micol.board.command.AjaxMemberList;
import co.micol.board.command.LoginAction;
import co.micol.board.command.LoginForm;
import co.micol.board.command.LogoutAction;
import co.micol.board.command.MainAction;
import co.micol.board.command.MemberForm;
import co.micol.board.command.MemberInsertAction;
import co.micol.board.command.MemberListAction;
import co.micol.board.common.Action;
import co.micol.users.command.DeleteUsers;
import co.micol.users.command.EmpStat;
import co.micol.users.command.GetUsers;
import co.micol.users.command.GetUsersList;
import co.micol.users.command.InsertUsers;
import co.micol.users.command.UpdateUsers;

@WebServlet("*.do")
@MultipartConfig(maxFileSize = 1024 * 1024 * 2 // size of any uploaded file
		, maxRequestSize = 1024 * 1024 * 10 // overall size of all uploaded files
		, location = "d:/img", fileSizeThreshold = 1024)
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Action> map = new HashMap<String, Action>();

	public FrontController() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {
		// ajax요청
		map.put("/ajax/memberList.do", new AjaxMemberList());
		map.put("/ajax/memberInsert.do",new AjaxMemberInsertAction());
		//차트
		map.put("/ajax/empStat.do",  new EmpStat());//차트
		
		//users
		map.put("/ajax/usersList.do",  new GetUsersList());//전체조회
		map.put("/ajax/users.do", 	   new GetUsers());	//단건조회
		map.put("/ajax/usersInsert.do",new InsertUsers());//등록
		map.put("/ajax/usersUpdate.do",new UpdateUsers());//수정
		map.put("/ajax/usersDelete.do",new DeleteUsers());//삭제
		
		
		// 요청들을 정의함
		map.put("/main.do", new MainAction());// 처음 들어오는 페이지 처리; index.jsp처리
		map.put("/login.do", new LoginAction());// 로그인메뉴를 처리하는것
		map.put("/loginForm.do", new LoginForm());// 로그인폼 호출
		map.put("/memberList.do", new MemberListAction());// 회원전체 리스트 보기
		map.put("/memberForm.do", new MemberForm());// 회원 가입 화면
		map.put("/memberInsert.do", new MemberInsertAction());
		map.put("/logout.do", new LogoutAction());
//		map.put("/loginForm.do",new LoginForm());
//		map.put("/loginForm.do",new LoginForm());

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 수행할 명령을 정리
		request.setCharacterEncoding("utf-8");// 한글 처리
		response.setContentType("text/html; charset=utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length());// 실제들어오는 요청페이지를 찾음

		Action command = map.get(path);
		String viewPage = command.exec(request, response);// 명령이 수행되고 나서 보여줄페이지를 선택
		
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);// 선택한페이지로 가기
			dispatcher.forward(request, response);

		}
	}
}
