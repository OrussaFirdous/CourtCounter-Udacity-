/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


     int quantity=0;
     String priceMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        int price = calculatePrice();

        EditText  text = (EditText) findViewById(R.id.edit_text);
        String   name = text.getText().toString();

        CheckBox haswhippedCream = (CheckBox) findViewById(R.id.whipped_checkbox) ;
         boolean   checked =  haswhippedCream.isChecked();

         CheckBox   hasChcolate = (CheckBox) findViewById(R.id.chocolate);
         boolean  choco_check = hasChcolate.isChecked();

         String priceMessage = createOrderSummary(price,checked,choco_check,name);

        Intent mailClient = new Intent(Intent.ACTION_SENDTO);
         mailClient.setData(Uri.parse("mailto:"));
         mailClient.putExtra(Intent.EXTRA_SUBJECT,"Just Java order for: " + name );
        mailClient.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if(mailClient.resolveActivity(getPackageManager()) != null){
            startActivity(mailClient);
        }

    }
/**To calculate the price of the order*/
     private int  calculatePrice(){
        int price = quantity*5;
        return price;
     }

     private String  createOrderSummary(int price, boolean checked,boolean choco_check,String name){



         priceMessage= "NAME: " + name + "\n";
         priceMessage= priceMessage + "Add WhippedCream: ";
         if(checked) {
             priceMessage = priceMessage + "true";
             price += quantity*1;
         }
          else{
              priceMessage=priceMessage+ "false";
         }

         if(choco_check){
             priceMessage = priceMessage + "\nAdd Chocolate: " + "True\n";
             price += quantity*2;
         }

         else{
             priceMessage = priceMessage + "\nAdd Chocolate: " + "False\n";

         }

         priceMessage= priceMessage + "Quantity :" + quantity + "\nTotal: $" + price + "\nThank You!";

          return priceMessage;

     }

    public void increment(View view) {

         if(quantity < 100) {
             quantity = quantity + 1;
         }        displayQuantity(quantity);
    }


    public void decrement(View view) {

        if(quantity > 1 ) {
           quantity = quantity - 1;
       }
        displayQuantity(quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**This method gets the text from the user input**/


}
