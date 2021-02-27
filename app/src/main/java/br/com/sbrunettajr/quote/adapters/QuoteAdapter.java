package br.com.sbrunettajr.quote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.sbrunettajr.quote.R;
import br.com.sbrunettajr.quote.models.Quote;
import br.com.sbrunettajr.quote.utils.Format;

public class QuoteAdapter extends BaseAdapter {

    private Context context;
    private List<Quote> quotes;

    public QuoteAdapter(Context context, List<Quote> quotes) {
        this.context = context;
        this.quotes = quotes;

    }

    @Override
    public int getCount() {
        return quotes.size();
    }

    @Override
    public Object getItem(int position) {
        return quotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_quote, parent, false);

        Quote quote = (Quote) getItem(position);

        TextView tv_price_key = (TextView) view.findViewById(R.id.tv_price_name);
        TextView tv_price_bid = (TextView) view.findViewById(R.id.tv_price_bid);

        String name  = "(" + quote.key + ") " + quote.name;
        String value = Format.currency(Double.parseDouble(quote.bid));

        tv_price_key.setText(name);
        tv_price_bid.setText(value);
        return view;
    }
}
