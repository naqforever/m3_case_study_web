package m3.furama.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import m3.furama.model.Car;
import m3.furama.model.DataField;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.tagext.TagSupport;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class AddEditTag extends TagSupport {
    private String name;
    private String exclude;
    private String radio;
    private String checkbox;
    private String select;

    public void setName(String name) {
        this.name = name;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    @Override
    public int doStartTag() {
        try {
            ServletRequest request = pageContext.getRequest();
            String className = ConstantUtil.MODEL_PATH + name.substring(0, 1).toUpperCase() + name.substring(1);
            List<Field> fields = CommonUtil.getAllFields(Class.forName(className));
            ObjectMapper objectMapper = new ObjectMapper();
//            String jsonCarArray =
//                    "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
//            List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});

//            String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";

                        String jsonCarArray = "[\n" +
                                "\t{\n" +
                                "\t\t\"field\": \"gender\",\n" +
                                "\t\t\"data\":\n" +
                                "\t\t[\n" +
                                "\t\t\t{\n" +
                                "\t\t\t\t\"key\": \"Male\",\n" +
                                "\t\t\t\t\"value\": \"Female\"\n" +
                                "\t\t\t}\n" +
                                "\t\t]\n" +
                                "\t}\n" +
                                "]";
            List<DataField> car = objectMapper.readValue(jsonCarArray, new TypeReference<List<DataField>>(){});

//            List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});
//            List<DataField> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<DataField>>(){});
            String[] excludes;
            if(exclude != null){
                excludes = exclude.split(",");
                for (int i = 0; i < excludes.length; i++) {
                    String tmp = excludes[i];
                    fields.removeIf(e -> e.getName().equals(tmp));
                }


            }



            request.setAttribute("name", name);
            request.setAttribute("fields", fields);
            pageContext.include("/view/element/addEdit.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return SKIP_BODY;
    }
}
