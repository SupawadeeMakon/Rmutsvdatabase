package rmutsv.makon.ppp.rmutsvdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {


    private TextView textView;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Button Controller
        buttonController();


    } //Main Method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            textView = (TextView) findViewById(R.id.txtShowScan);
            String myResult = data.getStringExtra("SCAN_RESULT");
            textView.setText(myResult);

        }


    }

    private void buttonController() {
        button = (Button) findViewById(R.id.btnScan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {

                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                    startActivityForResult(intent, 0);



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}// Main Class
