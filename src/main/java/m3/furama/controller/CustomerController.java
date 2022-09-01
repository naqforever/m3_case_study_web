package m3.furama.controller;

import m3.furama.model.Customer;
import m3.furama.service.CustomerService;
import m3.furama.service.CustomerServiceImpl;
import m3.furama.service.CustomerTypeService;
import m3.furama.service.CustomerTypeServiceImpl;
import m3.furama.util.CommonUtil;
import m3.furama.util.Page;
import m3.furama.util.PageHelper;
import m3.furama.util.Pageable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "CustomerController", value = "/customer")
public class CustomerController extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();
    private CustomerTypeService customerTypeService = new CustomerTypeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("result", customerService.findAll(PageHelper.PageRequest(request)));
        request.setAttribute("customerTypes", customerTypeService.findAll());
        request.getRequestDispatcher("customer.tiles").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("id");
        if (request.getParameter("action") != null) {
            customerService.delete(Integer.parseInt(sid));
        } else {
            customerService.save(mapToCustomer(request));
        }

        response.sendRedirect("/customer");
    }

    private Customer mapToCustomer(HttpServletRequest request) {
        int id = CommonUtil.toInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String birthday = request.getParameter("birthday");
        Boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String identifyNumber = request.getParameter("identifyNumber");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int customerTypeId = CommonUtil.toInt(request.getParameter("customerType"));
        return new Customer(id, fullName, LocalDate.parse(birthday), gender, identifyNumber, phone, email, address, customerTypeId, null);
    }
}
