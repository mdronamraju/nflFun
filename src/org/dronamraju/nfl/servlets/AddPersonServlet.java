package org.dronamraju.nfl.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dronamraju.nfl.model.Person;
import org.dronamraju.nfl.dao.MongoDBDAO;

@WebServlet("/addPerson")
public class AddPersonServlet extends HttpServlet {
	private static Log log = LogFactory.getLog(AddPersonServlet.class);
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String country = request.getParameter("country");
		if ((name == null || name.equals(""))
				|| (country == null || country.equals(""))) {
			request.setAttribute("error", "Mandatory Parameters Missing");
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/persons.jsp");
			rd.forward(request, response);
		} else {
			Person p = new Person();
			p.setCountry(country);
			p.setName(name);
			MongoDBDAO personDAO = new MongoDBDAO();
			personDAO.createPerson(p);
			System.out.println("Person Added Successfully with id="+p.getId());
			request.setAttribute("success", "Person Added Successfully");
			List<Person> persons = personDAO.readAllPerson();
			request.setAttribute("persons", persons);

			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/persons.jsp");
			rd.forward(request, response);
		}
	}

}