package sprinklerconfig.ulduzsoft.com.sprinklerconfig.activities;

/**
 * Created by syunaeva on 8/6/16.
 */
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import sprinklerconfig.ulduzsoft.com.sprinklerconfig.R;
import sprinklerconfig.ulduzsoft.com.sprinklerconfig.dao.ZonesDAO;
import sprinklerconfig.ulduzsoft.com.sprinklerconfig.model.Zone;


public class AddZoneActivity extends Activity{
    ZonesDAO dataSource;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_zone);

        dataSource = new ZonesDAO(this);

        final EditText zoneName = (EditText) findViewById(R.id.txt_zone_name);
        final EditText zoneDTMF = (EditText) findViewById(R.id.txt_zone_dtmf);

        final Button addZone = (Button) findViewById(R.id.btn_add);
        addZone.setOnClickListener(new OnClickListener() {


            public void onClick(View view) {
                dataSource.open();
                String name = zoneName.getText().toString();
                int dtfm = Integer.parseInt(zoneDTMF.getText().toString());
                Zone zone = dataSource.createZone(name, dtfm);
                Toast.makeText(AddZoneActivity.this, "New zone added", Toast.LENGTH_LONG).show();
                dataSource.close();
                finish();
            }



        });
    }


}