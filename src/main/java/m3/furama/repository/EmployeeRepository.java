package m3.furama.repository;

import m3.furama.model.Employee;
import m3.furama.util.CommonUtil;
import m3.furama.util.paging.Page;
import m3.furama.util.paging.PageHelper;
import m3.furama.util.paging.Pageable;

import java.util.List;

public class EmployeeRepository implements BaseRepository<Employee> {
    private final String FIND_ALL = "select * from employee order by id desc";
    private final String FIND_ALL_PAGING = "select * from employee order by id desc limit ?lim offset ?off";
    private static final String INSERT = "insert into employee (fullname, birthday, identify_number, salary, phone" +
            ", email, address, position_id, degree_id, department_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "update employee set fullname = ?,  birthday = ?, identify_number =? , salary=?, phone=?" +
            ", email = ?, address = ?, position_id, degree_id, department_id = ? where id = ?";

    @Override
    public List<Employee> findAll() {
        return CommonUtil.findAll(FIND_ALL, Employee.class);
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        int offset = (pageable.getPageNum() - 1) * pageable.getPageSize();
        String query = FIND_ALL_PAGING.replace("?lim", String.valueOf(pageable.getPageSize()));
        List<Employee> employees = CommonUtil.findAll((query.replace("?off", String.valueOf(offset))), Employee.class);
        PageHelper<Employee> helper = new PageHelper<>();
        return helper.listToPage(employees, findAll().size(), pageable);
    }

    @Override
    public int save(Employee employee) {
        return 0;
    }

    @Override
    public List<Employee> find(String q) {
        return null;
    }
}
