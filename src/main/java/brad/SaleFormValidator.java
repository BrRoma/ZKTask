package brad;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import java.util.Date;
import java.util.Map;
import static brad.SaleConstants.*;

public class SaleFormValidator extends AbstractValidator {

    public void validate(ValidationContext ctx) {
        Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());

        validateOnlyDigits(ctx, (String)beanProps.get(CARD_NUMBER).getValue(), beanProps.get(CARD_NUMBER).getProperty());
        validateExpDate(ctx, (Date)beanProps.get(EXP_DATE).getValue());
        validateOnlyDigits(ctx, (String)beanProps.get(CSC_CODE).getValue(), beanProps.get(CSC_CODE).getProperty());
        validateOnlyDigits(ctx, beanProps.get(AMOUNT).getValue().toString(), beanProps.get(AMOUNT).getProperty());
        validateOnlyLetters(ctx, (String)beanProps.get(HOLDER_NAME).getValue(), beanProps.get(HOLDER_NAME).getProperty());
        validateOnlyLetters(ctx, (String)beanProps.get(CITY).getValue(), beanProps.get(CITY).getProperty());
        validateOnlyLetters(ctx, (String)beanProps.get(STATE).getValue(), beanProps.get(STATE).getProperty());
        validateOnlyDigits(ctx, (String)beanProps.get(ZIP_CODE).getValue(), beanProps.get(ZIP_CODE).getProperty());
    }

    private void validateOnlyDigits(ValidationContext ctx, String s, String key) {
        if(s == null || !s.matches("\\d+")) {
            this.addInvalidMessage(ctx, key, MESSAGE_ONLY_DIGITS);
        }
    }

    private void validateExpDate(ValidationContext ctx, Date expDate) {
        if (expDate == null ) {
            this.addInvalidMessage(ctx,EXP_DATE,MESSAGE_EXP_DATE);
        }
    }

    private void validateOnlyLetters(ValidationContext ctx, String s, String key) {
        if(s == null || !s.matches("^[-a-zA-Z]+")) {
            this.addInvalidMessage(ctx, key, MESSAGE_ONLY_LETTERS);
        }
    }
}

