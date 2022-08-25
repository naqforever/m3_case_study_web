package m3.furama.util;

import m3.furama.model.Customer;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

public class PagingTag extends TagSupport {
    private String name;
    private Object search;
    private Object params;

    public void setName(String name) {
        this.name = name;
    }

    public void setSearch(Object search){
        this.search= search;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    @Override
    public int doStartTag() {
        try {
            ServletRequest request= pageContext.getRequest();
            request.setAttribute("name", name);
            request.setAttribute("search", search);
            Page<Customer> a= (Page<Customer>) params;
            int bbb= a.getTotalPages();
            request.setAttribute("result", params);
            pageContext.include("/layout/paging.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SKIP_BODY;
    }
}
