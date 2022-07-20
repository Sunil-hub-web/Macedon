package in.co.macedon.activities;

import androidx.appcompat.app.AppCompatActivity;
import in.co.macedon.R;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {

    ImageView plusicon, minusicon;
    TextView quantity, membershipprice;
    int qt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        plusicon = findViewById(R.id.plusicon);
        minusicon = findViewById(R.id.minusicon);
        quantity = findViewById(R.id.quantity);
        membershipprice = findViewById(R.id.membershipprice);
        membershipprice.setPaintFlags(membershipprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        plusicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qt = qt+1;
                quantity.setText(""+qt);
            }
        });

        minusicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qt!=1) {
                    qt = qt - 1;
                    quantity.setText("" + qt);
                }
            }
        });
    }
}
