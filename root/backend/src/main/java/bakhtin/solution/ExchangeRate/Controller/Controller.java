package bakhtin.solution.ExchangeRate.Controller;

import bakhtin.solution.ExchangeRate.Entities.ExchangeRate;
import bakhtin.solution.ExchangeRate.Entities.Repos.ExchangeRateRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.json.*;

@CrossOrigin
@RestController
public class Controller {

    @Autowired
    private ExchangeRateRepo rateRepos;

    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity<String> deleteAll() {
        rateRepos.deleteAll();
        return ResponseEntity.ok("Deleted successfully");
    }

    @GetMapping(value = "/rates")
    public List<ExchangeRate> getExchangeRates(@RequestParam boolean usedb) {
        if (usedb) {                        //  load data from url
            HttpURLConnection con = null;   //  http connection
            BufferedReader in = null;       //  buffer holding http response
            try {
                rateRepos.deleteAll();      //  delete previous records
                URL url = new URL("https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v2/rates/exchangera" +
                        "tes?web-api-key=c52a0682-4806-4903-828f-6cc66508329e");
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");                        //  send get request

                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                JSONArray rates = new JSONArray(in.readLine());     //  json array containing rates
                for (Object o : rates) {                            //  loop over rates
                    JSONObject rate = (JSONObject) o;
                    ObjectMapper mapper = new ObjectMapper()
                            .registerModule(new JavaTimeModule());
                    var newExchangeRate = mapper.readValue(rate.toString(), ExchangeRate.class);
                    rateRepos.save(newExchangeRate);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    con.disconnect();           //  close connection
                    in.close();                 //  close input stream
                } catch (IOException e) {
                    System.out.println("Error while closing connections");
                }
            }
        }
        return rateRepos.findAll();     //  return from db (can be updated before)
    }

}
