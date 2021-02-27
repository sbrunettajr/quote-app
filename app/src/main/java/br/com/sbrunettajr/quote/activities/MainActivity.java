package br.com.sbrunettajr.quote.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.sbrunettajr.quote.models.Quote;
import br.com.sbrunettajr.quote.adapters.QuoteAdapter;
import br.com.sbrunettajr.quote.services.PriceIntentService;
import br.com.sbrunettajr.quote.R;

public class MainActivity extends AppCompatActivity {

    private ListView lv_price;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                List<Quote> quotes = new ArrayList();

                String response = intent.getStringExtra("DATA");
                JSONObject object = new JSONObject(response);

                Gson gson = new Gson();

                for (Iterator<String> keys = object.keys(); keys.hasNext();) {
                    String key = keys.next();

                    Quote quote = gson.fromJson(object.get(key).toString(), Quote.class);
                    quote.key = key;

                    quotes.add(quote);
                }
                lv_price.setAdapter(new QuoteAdapter(MainActivity.this, quotes));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_price = findViewById(R.id.lv_price);

        registerReceiver(broadcastReceiver, new IntentFilter("UPD_PRICE"));
        startService(new Intent(this, PriceIntentService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_configuration) {
            startActivity(new Intent(this, ConfigurationActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}