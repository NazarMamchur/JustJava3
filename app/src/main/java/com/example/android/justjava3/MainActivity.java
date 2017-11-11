package com.example.android.justjava3;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.android.justjava3.R;

        import java.text.NumberFormat;

        /*
        * This app displays an order form to order coffee.
        */
public class MainActivity extends AppCompatActivity {

            int quantity = 2;
            int price = 5;
            String summary = "Quantity: " + quantity + "\nTotal: $" + (price*2) + "\nThank you!";
            boolean hasWhippedCream;
            boolean hasChocolate;
            String name;
            Toast toastMessage1 = Toast.makeText (getApplicationContext(), "Number of coffees can't be  less than 1", Toast.LENGTH_SHORT);
            Toast toastMessage2 = Toast.makeText (getApplicationContext(), "Number of coffees can't be  more than 100", Toast.LENGTH_SHORT);

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                displayQuantity(quantity);
                displayMessage(summary);
            }


    /*
            * This method is called when the order button is clicked.
            */

            public void submitOrder(View view) {
                CheckBox whippedCream = findViewById(R.id.check_box_cream);
                hasWhippedCream = whippedCream.isChecked();
                CheckBox chocolate = findViewById(R.id.check_box_chocolate);
                hasChocolate = chocolate.isChecked();

                EditText nameView = findViewById(R.id.name);
                name = nameView.getText().toString();

                price = calculatePrice(quantity);
                summary = createOrderSummary(quantity, price, hasWhippedCream, hasChocolate, name);
                displayMessage(summary);
            }

            public String createOrderSummary(int quantity, int price, boolean hasWhippedCream, boolean hasChocolate, String name) {
                price = calculatePrice(quantity);

                summary = "Name: " + name + "\nAdd whipped cream? " + hasWhippedCream + "\nAdd chocolate? " + hasChocolate +"\nQuantity: " + quantity + "\nTotal: $" + price + "\nThank you!";
                return summary;
            }

            /*
                    * This method displays the given quantity value on the screen.
                    */
            private void displayQuantity(int number) {
                TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
                quantityTextView.setText("" + number);
            }


            public void increment(View view) {
                quantity++;
                if (quantity < 1){
                    toastMessage1.show();
                    return;
                }
                displayQuantity(quantity);
            }

            public void decrement(View view) {
                quantity--;
                if (quantity > 100){
                    toastMessage2.show();
                    return;
                }
                displayQuantity(quantity);
            }


            /**
             * This method displays the given text on the screen.
             */
            private void displayMessage(String message) {
                TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
                orderSummaryTextView.setText(message);
            }

            /**
             * Calculates the price of the order based on the current quantity.
             *
             * @return the price
             */
            private int calculatePrice(int quantity) {
                int top_cream = 0;
                int top_chocolate = 0;
                if (hasWhippedCream) {
                    top_cream = 1;
                }
                if (hasChocolate) {
                    top_chocolate = 2;
                }
                price = quantity * (5 + top_cream + top_chocolate) ;
                return price;
            }


        }