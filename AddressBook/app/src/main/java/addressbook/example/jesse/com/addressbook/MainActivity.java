package addressbook.example.jesse.com.addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button viewContactsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewContactsButton = (Button) findViewById(R.id.viewContactsButton);

        viewContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getNameScreenIntent = new Intent(getBaseContext(), SecondScreen.class);

                final int result = 1;

                getNameScreenIntent.putExtra("callingActivity", "MainActivity");

                startActivity(getNameScreenIntent);
            }
        });
    }
}
