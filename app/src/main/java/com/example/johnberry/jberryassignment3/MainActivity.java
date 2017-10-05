package com.example.johnberry.jberryassignment3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private double currentTotal = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, MENU_ITEMS);

        final AutoCompleteTextView menuField = (AutoCompleteTextView) findViewById(R.id.itemField);
        menuField.setAdapter(adapter);
        final EditText priceField = (EditText) findViewById(R.id.priceField);
        final EditText quantityField = (EditText) findViewById(R.id.quantField);
        final TextView orderSummary = (TextView) findViewById(R.id.orderSummary);
        final TextView orderTotal = (TextView) findViewById(R.id.orderTotal);

        menuField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                String menuItem = menuField.getText().toString();
                String priceToSet = MENU_PRICES.get(menuItem);
                priceField.setText(priceToSet);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        Button newOrderButton = (Button) findViewById(R.id.newOrderButton);
        newOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuField.setText("");
                priceField.setText("");
                quantityField.setText("");
                orderSummary.setText("");
                orderTotal.setText("");
                currentTotal = 0;
            }
        });

        Button newItemButton = (Button) findViewById(R.id.newItemButton);
        newItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuField.setText("");
                priceField.setText("");
                quantityField.setText("");
            }
        });

        Button totalButton = (Button) findViewById(R.id.totalButton);
        totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                String itemText = menuField.getText().toString();
                String itemQuant = quantityField.getText().toString();
                String itemCost = priceField.getText().toString();
                Double totalCost = Double.parseDouble(itemCost) * Double.parseDouble(itemQuant);
                String costToDisplay = Double.toString(totalCost);

                String toUpdateSummaryWith = orderSummary.getText() + itemText + " X " + itemQuant + "  " + costToDisplay + "\n";
                orderSummary.setText(toUpdateSummaryWith);
                currentTotal = currentTotal + totalCost;
                orderTotal.setText(Double.toString(currentTotal));

                menuField.setText("");
                priceField.setText("");
                quantityField.setText("");
            }
        });
    }

    static final String[] MENU_ITEMS = new String[] {
            "Chicken Parmagiana", "Linguine", "Hummus", "French Fries",
             "Sirloin Steak", "Lasagna"
    };

    static final HashMap<String, String> MENU_PRICES = new HashMap<String, String>(){
        {
            put("Chicken Parmagiana", "23.49");
            put("Linguine", "13.99");
            put("Hummus", "2.49");
            put("French Fries", "1.75");
            put("Lasagna", "11.55");
            put("Sirloin Steak", "43.00");
        }
        ;
    };
}
