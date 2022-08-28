package m3.furama.util;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

public class DeleteTag extends TagSupport {
    private int idItem;
    private String nameItem;

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    @Override
    public int doStartTag() {
        try {
            ServletRequest request= pageContext.getRequest();
            request.setAttribute("idItem", idItem);
            request.setAttribute("nameItem", nameItem);
            pageContext.include("/view/element/delete.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return SKIP_BODY;
    }
}
