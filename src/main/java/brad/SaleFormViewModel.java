package brad;

import org.zkoss.bind.SimpleForm;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import java.io.IOException;

public class SaleFormViewModel extends SaleForm {

    private String dateFormat;

    @Command
    public void submit(@BindingParam("sale") SimpleForm sf) {

        try {
            SaleController.doTestSale(sf);
        } catch (IOException e) {
            e.printStackTrace();
            }
        }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}