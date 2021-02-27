package br.com.sbrunettajr.quote.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import br.com.sbrunettajr.quote.utils.Preference;

public class PriceIntentService extends IntentService {

    public PriceIntentService() {
        super("PriceIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        while (true) {
            String response = callWebService();

            Intent intent1 = new Intent("UPD_PRICE");
            intent1.putExtra("DATA", response);
            sendBroadcast(intent1);
            sleep();
        }
    }

    private String callWebService() {

        Log.i("QUOTE", "Web Service called");

        try {
            URL url = new URL("https://economia.awesomeapi.com.br/json/all");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(15000);
            connection.connect();

            StringBuilder response = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                response.append(scanner.next());
            }
            connection.disconnect();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sleep() {
        try {
            int time = Preference.getInteger(this, Preference.PreferenceName.PREF_UPD_TYPE.toString() );

            time *= 1000;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
