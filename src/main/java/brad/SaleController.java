package brad;

import org.zkoss.bind.SimpleForm;
import org.zkoss.zk.ui.select.SelectorComposer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static brad.SaleConstants.*;

public class SaleController extends SelectorComposer{

    private static int CONNECT_TIMEOUT = 10 * 1000;
    private static int READ_TIMEOUT = 1 * 60 * 1000;
    private static String EMPTY = "";


    public static String sendPOST(String url, String data) throws IOException {

        HttpURLConnection conn = null;
        InputStream stream = null;
        URL urlLink = new URL(url);
        OutputStreamWriter writer = null;

        conn = (HttpURLConnection) urlLink.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setConnectTimeout(CONNECT_TIMEOUT);
        conn.setReadTimeout(READ_TIMEOUT);
        conn.setRequestProperty(CONTENT_TYPE, APPLICATION);
        conn.setRequestMethod(POST);

        writer = new OutputStreamWriter(conn.getOutputStream());
        writer.write(data);
        writer.flush();
        writer.close();


        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            stream = conn.getInputStream();
        } else {
            stream = conn.getErrorStream();
        }
        if (stream == null) {
            System.out.println("Response code is " + conn.getResponseCode());
            return EMPTY;
        }
        return stream2String(stream);
    }

    private static String stream2String(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder(8192);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private static String preparationData (SimpleForm sf){
        StringBuilder sb = new StringBuilder();
        sb.append("&requestType=sale");
        sb.append("&userName=");
        sb.append(USERNAME);
        sb.append("&password=");
        sb.append(PASSWORD);
        sb.append("&accountId=");
        sb.append(ACCOUNT_ID);
        sb.append("&transactionIndustryType=");
        sb.append(TRANSACTION_INDASTRY_TYPE);
        sb.append("&transactionCode=");
        sb.append(TRANSACTION_CODE);
        sb.append("&amount=");
        sb.append(sf.getField(AMOUNT));
        sb.append("&holderType=");
        sb.append(HOLDER_TYPE);
        sb.append("&holderName=");
        sb.append(sf.getField(HOLDER_NAME));
        sb.append("&street=");
        sb.append(sf.getField(STREET));
        sb.append("&city=");
        sb.append(sf.getField(CITY));
        sb.append("&state=");
        sb.append(sf.getField(STATE));
        sb.append("&zipCode=");
        sb.append(sf.getField(ZIP_CODE));
        sb.append("&accountType=");
        sb.append(ACCOUNT_TYPE);
        sb.append("&accountNumber=");
        sb.append(sf.getField(CARD_NUMBER));
        sb.append("&accountAccessory=");
        sb.append(ACCOUNT_ACCESSORY);
        sb.append("&customerAccountCode=");
        sb.append(CUSTOMER_ACCOUNT_CODE);

       return sb.toString();
    }

    public static void doTestSale (SimpleForm sf) throws IOException {
        System.out.println(sendPOST(URL_SALE, preparationData(sf)));
    }
}

