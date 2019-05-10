import java.io.*;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Godaddy {
    private List<String> cookies;
    private HttpsURLConnection conn;

    private final String USER_AGENT = "Mozilla/5.0";
    public static void main(String[] args) throws Exception {
        String url = "https://sso.godaddy.com/v1/api/pass/login?app=email&realm=pass&path=%2Fwebmail.php%3Ffolder%3DINBOX%26firstMessage%3D1";
//        String gmail = "https://mail.google.com/mail/";

        Godaddy http = new Godaddy();

        // make sure cookies is turn on
        CookieHandler.setDefault(new CookieManager());
        String page = http.GetPageContent(url);
        String postParams = http.getFormParams(page, "catherine@sobersteering.com", "password");
        // 1. Send a "GET" request, so that you can extract the form's data.
        //String payload="{\"username\":\"catherine@sobersteering.com\",\"password\":\"w3234\",\"remember_me\":false,\"plid\":1,\"API_HOST\":\"godaddy.com\"}";
        // 2. Construct above post's content and then send a POST request for
        // authentication
        http.sendPost(url, postParams);

        // 3. success then go to gmail.
       /* String result = http.GetPageContent(gmail);
        System.out.println(result);*/

    }
   /* private void test1() throws MalformedURLException {
        String payload="{\"username\":\"catherine@sobersteering.com\",\"password\":\"w3234\",\"remember_me\":false,\"plid\":1,\"API_HOST\":\"godaddy.com\"}";
        URL url = new URL("https://sso.godaddy.com/v1/api/pass/login?app=email&realm=pass&path=%2Fwebmail.php%3Ffolder%3DINBOX%26firstMessage%3D1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty( "Content-Type", "application/text");

        conn.setRequestProperty( "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(),
                StandardCharsets.UTF_8);
        writer.write(payload);
        writer.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer jsonString = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            jsonString.append(line);
        }
        br.close();
        conn.disconnect();
        System.out.println(jsonString.toString()) ;
    }*/
    private void sendPost(String url, String postParams) throws Exception {

        URL obj = new URL(url);
        conn = (HttpsURLConnection) obj.openConnection();

        // Acts like a browser
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Host", "sso.godaddy.com");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "application/json");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        for (String cookie : this.cookies) {
            conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
        }
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Referer", "https://sso.godaddy.com/?app=email&realm=pass&path=%2Fwebmail.php%3Ffolder%3DINBOX%26firstMessage%3D1");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));

        conn.setDoOutput(true);
        conn.setDoInput(true);

        // Send post request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + postParams);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        // System.out.println(response.toString());

    }


    private String GetPageContent(String url) throws Exception {

        URL obj = new URL(url);
        conn = (HttpsURLConnection) obj.openConnection();

        // default is GET
        conn.setRequestMethod("GET");

        conn.setUseCaches(false);

        // act like a browser
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        if (cookies != null) {
            for (String cookie : this.cookies) {
                conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
            }
        }
        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Get the response cookies
        setCookies(conn.getHeaderFields().get("Set-Cookie"));

        return response.toString();

    }

    public String getFormParams(String html, String username, String password)
            throws UnsupportedEncodingException {

        System.out.println("Extracting form's data...");

        Document doc = Jsoup.parse(html);

        // Google form id
        Element loginform = doc.getElementById("gaia_loginform");
        Elements inputElements = loginform.getElementsByTag("input");
        List<String> paramList = new ArrayList<String>();
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");

            if (key.equals("Email"))
                value = username;
            else if (key.equals("Passwd"))
                value = password;
            paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
        }

        // build parameters list
        StringBuilder result = new StringBuilder();
        for (String param : paramList) {
            if (result.length() == 0) {
                result.append(param);
            } else {
                result.append("&" + param);
            }
        }
        return result.toString();
    }

    public List<String> getCookies() {
        return cookies;
    }

    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }
}
