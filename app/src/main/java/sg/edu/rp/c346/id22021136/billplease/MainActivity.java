package sg.edu.rp.c346.id22021136.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {
    EditText amount;
    EditText numPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    Button split;
    Button reset;
    EditText discount;

    RadioGroup rgPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount = findViewById(R.id.editAmount);
        numPax = findViewById(R.id.editNumPax);
        totalBill = findViewById(R.id.textTotalBill);
        eachPays = findViewById(R.id.textEachPays);
        svs = findViewById(R.id.TBsvs);
        gst = findViewById(R.id.TBgst);
        split = findViewById(R.id.Split);
        reset = findViewById(R.id.Reset);
        discount = findViewById(R.id.editDiscount);
        rgPayment = findViewById(R.id.paymentMethod);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().trim().length()!=0 && numPax.getText().toString().trim().length()!=0) {
                    double newAmount = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmount = Double.parseDouble(amount.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.07;
                    } else {
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }
                    if (discount.getText().toString().trim().length() !=0) {
                        newAmount *=1 - Double.parseDouble(discount.getText().toString()) /100;
                }
                    String mode = " in cash";
                    if(rgPayment.getCheckedRadioButtonId() == R.id.paymentMethodPaynow) {
                        mode = " via PayNow to 85338187";
                    }
                    totalBill.setText("Total Bill: $" + String.format("%.2f", newAmount));
                    int numberofPax = Integer.parseInt(numPax.getText().toString());
                    if (numberofPax !=1) {
                        eachPays.setText("Each person pays: $" + String.format("%.2f", newAmount / numberofPax) + mode);
                    } else {
                        eachPays.setText("Each person pays: $" + newAmount + mode);
                    }
                }

            }
        });




        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                numPax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
                totalBill.setText("");
                eachPays.setText("");
            }
        });








    }
}