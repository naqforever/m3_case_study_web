package m3.furama.controller;

import m3.furama.service.GenericService;
import m3.furama.util.paging.PageHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/employee", "/customer"})
public class GenericController extends HttpServlet {
    private GenericService genericService = new GenericService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String entityName = request.getServletPath().substring(1);
        genericService.setEntityName(entityName);

        request.setAttribute("result", genericService.findAll(PageHelper.PageRequest(request)));
        request.getRequestDispatcher(entityName + ".tiles").forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
