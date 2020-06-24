package com.increff.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Api Servlet", description = "api servlet", urlPatterns = { "/api" })
public class ApiServlet extends HttpServlet {

	private static final long serialVersionUID = 1042412442755277478L;
	private static EmployeeHibernateApi api;

	static {
		HibernateUtil.configure();
		api = new EmployeeHibernateApi();
	}

	@Override
	// Retrieving
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String actionParam = req.getParameter("action");
		String idParam = req.getParameter("id");

		if (idParam == null) {
			try {
				List<EmployeePojo> list = api.selectAll();
				String json = JsonUtil.writeJson(list);
				out.write(json);
			} catch (SQLException e) {
				throw new ServletException("Error retrieving emmployee list", e);
			}
			return;
		}

		if (actionParam != null && actionParam.contentEquals("delete")) {
			doDelete(req, resp);
			return;
		}

		int id = Integer.valueOf(idParam);
		try {
			EmployeePojo p = api.select(id);
			String json = JsonUtil.writeJson(p);
			out.write(json);
		} catch (SQLException e) {
			throw new ServletException("Error retrieving single employee", e);
		}

	}

	// Creating
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmployeePojo p = JsonUtil.readJson(req);
		try {
			api.insert(p);
		} catch (SQLException e) {
			throw new ServletException("Error saving object", e);
		}

	}

	// Updating
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String idStr = req.getParameter("id");
		EmployeePojo p = JsonUtil.readJson(req);
		int id = Integer.valueOf(idStr);

		try {
			api.update(id, p);
		} catch (SQLException e) {
			throw new ServletException("Error updating object", e);
		}

	}

	// Deleting
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.valueOf(req.getParameter("id"));
		try {
			api.delete(id);
		} catch (SQLException e) {
			throw new ServletException("Error retrieving object", e);
		}

	}

}
