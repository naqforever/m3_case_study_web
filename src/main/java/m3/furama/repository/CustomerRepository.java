package m3.furama.repository;

import m3.furama.model.Customer;
import m3.furama.model.CustomerType;
import m3.furama.util.Page;
import m3.furama.util.PageHelper;
import m3.furama.util.Pageable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerRepository implements BaseRepository<Customer> {
    private final String FIND_ALL = "select * from customer c join customer_type t on c.customer_type_id = t.id order by c.id desc";
    private final String FIND_ALL_PAGING = "select * from customer c join customer_type t on c.customer_type_id = t.id order by c.id desc limit ?lim offset ?off";
    private static final String INSERT = "insert into customer (fullname, birthday, gender, identify_number, phone" +
            ", email, address, customer_type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "update customer set fullname = ?,  birthday = ?, gender=?, identify_number =? , phone=?" +
            ", email = ?, address = ?, customer_type_id = ? where id = ?";
    private static final String DELETE = "delete from customer where id = ?";

    @Override
    public List<Customer> findAll() {
        return findAll(FIND_ALL);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        int offset = (pageable.getPageNum() - 1) * pageable.getPageSize();
        String query = FIND_ALL_PAGING.replace("?lim", String.valueOf(pageable.getPageSize()));
        List<Customer> customers = findAll(query.replace("?off", String.valueOf(offset)));
        PageHelper<Customer> helper = new PageHelper<>();
        return helper.listToPage(customers, findAll().size(), pageable);
    }

    @Override
    public int save(Customer customer) {
        try (PreparedStatement st = Config.getConnection().prepareStatement(customer.getId() > 0 ? UPDATE : INSERT)) {
            st.setString(1, customer.getFullName());
            st.setDate(2, java.sql.Date.valueOf(customer.getBirthday()));
            st.setBoolean(3, customer.getGender());
            st.setString(4, customer.getIdentifyNumber());
            st.setString(5, customer.getPhone());
            st.setString(6, customer.getEmail());
            st.setString(7, customer.getAddress());
            st.setInt(8, customer.getCustomerTypeId());
            if (customer.getId() > 0) {
                st.setInt(9, customer.getId());
            }

            return st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Customer> find(String q) {
        return null;
    }

    private Customer getCustomer(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String fullName = rs.getString(2);
        Date dateBirthday = rs.getDate(3);
        LocalDate birthday = new java.sql.Date(dateBirthday.getTime()).toLocalDate();
        Boolean gender = rs.getBoolean(4);
        String identifyNumber = rs.getString(5);
        String phone = rs.getString(6);
        String email = rs.getString(7);
        String address = rs.getString(8);
        int customerTypeId = rs.getInt(9);
        String customerTypeName = rs.getString(11);

        CustomerType customerType = new CustomerType(customerTypeId, customerTypeName);
        Customer customer = new Customer(id, fullName, birthday, gender, identifyNumber, phone, email, address, customerTypeId, customerType);
        return customer;
    }

    private List<Customer> findAll(String query) {
        List<Customer> result = new ArrayList<>();

        try (Connection connection = Config.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(getCustomer(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
