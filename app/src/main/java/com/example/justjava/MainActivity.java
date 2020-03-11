/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.Message;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }   int quantity=2;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox Whippedcreamchecked=(CheckBox) findViewById(R.id.Whipped_cream_checked);
        boolean isWhippedcreamchecked=Whippedcreamchecked.isChecked();
        CheckBox Chocolate=(CheckBox) findViewById(R.id.Chocolate);
        boolean isChocolate=Chocolate.isChecked();
        EditText Text=(EditText) findViewById(R.id.Edit_Text);
        Editable text= Text.getText();

        int price= calculatePrice(isWhippedcreamchecked,isChocolate);

       String priceMessage="Total: $"+price;
       priceMessage=priceMessage+"\nThank you!";


   String Message= createOrderSummary(price,isWhippedcreamchecked,isChocolate,text);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, Message);
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for"+text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

   }}

    public void Increment(View view){
        if(quantity==100){
            Toast.makeText(this,"Quantity can't be greater thhan 100",Toast.LENGTH_SHORT);
        return;
        }
        quantity=quantity+1;
        displayquantity(quantity);
    }
    public void Decrement(View view){
if(quantity==1){
    Toast.makeText(this,"Quamtity of coffees can't be less than 1",Toast.LENGTH_SHORT);
    return;
}
        quantity-=1;
        displayquantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayquantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given text on the screen.
     */

    private String createOrderSummary(int a,boolean Whippedcreamchecked,boolean isChocolate,Editable text){
        String display="";
        display+=text;
        display+="\nAdd whipped cream? "+Whippedcreamchecked;
        display+="\nAdd chocolate? "+isChocolate;
        display=display+"\nQuantity: "+quantity;
        display+="\nTotal: ₹"+a;
        display+="\nThank you!";
        return display;

    }
    private int calculatePrice(boolean isWhippedcreamchecked,boolean isChocolate) {
        int price=10;
        if(isWhippedcreamchecked==true&&isChocolate==true){
            price=(quantity)*(5+1+2);
        }
        else if(isWhippedcreamchecked==true&&isChocolate==false)
        { price = quantity * (5+1);

    } else if(isWhippedcreamchecked==false&&isChocolate==true)
        {price = quantity * (5+2);

    }
    else price=quantity*5;
    return price;}}
