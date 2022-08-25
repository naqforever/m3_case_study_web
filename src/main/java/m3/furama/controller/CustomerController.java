package m3.furama.controller;

import m3.furama.model.Customer;
import m3.furama.service.CustomerService;
import m3.furama.service.CustomerServiceImpl;
import m3.furama.util.Page;
import m3.furama.util.Pageable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", value = "/customer")
public class CustomerController extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pageable pageable = new Pageable();
        pageable.setPageNum(1);
        pageable.setPageSize(5);
        request.setAttribute("result", customerService.findAll(pageable));
        request.getRequestDispatcher("customer.tiles").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
