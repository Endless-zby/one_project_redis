import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String wenjianname = request.getParameter("username");// 前台用户名
		response.setContentType("text/html;charset=utf-8");

		request.setCharacterEncoding("utf-8");

		String username = request.getParameter("username");



		HashMap<String, String> map = new HashMap<String, String>();

		//String namefile = request.getParameter("username");
		String path = this.getServletContext().getRealPath(wenjianname);

		System.out.println(path);
		File file = new File(path);
		/* if(!file.isFile()){ */
		if (!file.exists()) {

			file.mkdirs();

		} else {
			File[] list = file.listFiles();

			for (File f : list) {

				if (f.isFile()) {
					map.put(f.getName(), f.getName());
				}
				String filename = f.getName();

				map.put(filename, filename);
			}
		}
		/* HttpSession session = request.getSession(); */

		request.setAttribute("username", username);
		request.getSession().setAttribute("map", map);

		request.getRequestDispatcher("show.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}
}
