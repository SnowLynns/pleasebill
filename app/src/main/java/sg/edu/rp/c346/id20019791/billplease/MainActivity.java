package sg.edu.rp.c346.id20019791.billplease;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    EditText etamt;
    EditText etpax;
    EditText etdisc;
    ToggleButton tbnosvs;
    ToggleButton tbgst;
    ToggleButton tbsplit;
    ToggleButton tbreset;
    TextView totalBill;
    TextView eachPay;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etamt = findViewById(R.id.etAmt);
        etpax = findViewById(R.id.etPax);
        etdisc = findViewById(R.id.etDisc);
        tbnosvs = findViewById(R.id.nosvs);
        tbgst = findViewById(R.id.gst);
        tbsplit = findViewById(R.id.tbSplit);
        tbreset = findViewById(R.id.tbReset);
        totalBill = findViewById(R.id.totalBill);
        eachPay = findViewById(R.id.eachPay);
        radioGroup = findViewById(R.id.rg);

        tbsplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                if(etamt.getText().toString().trim().length()!=0 && etpax.getText().toString().trim().length()!=0) {
                    double newAmt = 0.0;
                    if (!tbnosvs.isChecked() && !tbgst.isChecked()) {
                        newAmt = Double.parseDouble(etamt.getText().toString());
                    } else if (tbnosvs.isChecked() && !tbgst.isChecked()) {
                        newAmt = Double.parseDouble(etamt.getText().toString()) * 1.1;
                    } else if (!tbnosvs.isChecked() && tbgst.isChecked()) {
                        newAmt = Double.parseDouble(etamt.getText().toString()) * 1.07;
                    } else {
                        newAmt = Double.parseDouble(etamt.getText().toString()) * 1.17;
                    }
                    if (etdisc.getText().toString().trim().length() != 0) {
                        newAmt *= 1 - Double.parseDouble(etdisc.getText().toString()) / 100;
                    }
                    totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int numPerson = Integer.parseInt(etpax.getText().toString());
                    if (numPerson != 1)
                        eachPay.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson));
                    else
                        eachPay.setText("Each Pays: $" + newAmt);
                }
            }
        });

        tbreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etamt.setText("");
                etpax.setText("");
                tbnosvs.setChecked(false);
                tbgst.setChecked(false);
                etdisc.setText("");
            }
        });
    }
}
