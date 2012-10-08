package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.UsuarioDAO;
import com.entity.Usuario;

/**
 * Servlet implementation class UsuarioDelete
 */
@WebServlet(
		urlPatterns = { "/UsuarioDelete" }, 
		initParams = { 
				@WebInitParam(name = "id", value = "")
		})
public class UsuarioDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		Long id = Long.valueOf(request.getParameter("id"));
		
		if(id != null){
			Usuario u = new Usuario();
			u.setId(id);
			u.setValid(false);
			UsuarioDAO dao = new UsuarioDAO();
			
			try{
				dao.excluir(u);
				u.setValid(true);
			}catch(Exception e){
				u.setValid(false);
			}
			
           response.sendRedirect("?p=listar&success="+u.isValid().toString());
			
		}
		//response.sendRedirect("/?p=listar&success=false");
	}

}
