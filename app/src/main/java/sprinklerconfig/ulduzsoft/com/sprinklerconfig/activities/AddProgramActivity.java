package sprinklerconfig.ulduzsoft.com.sprinklerconfig.activities;

/**
 * Created by syunaeva on 8/6/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import sprinklerconfig.ulduzsoft.com.sprinklerconfig.R;
import sprinklerconfig.ulduzsoft.com.sprinklerconfig.dao.ProgramDAO;
import sprinklerconfig.ulduzsoft.com.sprinklerconfig.dao.ZonesDAO;
import sprinklerconfig.ulduzsoft.com.sprinklerconfig.model.Program;
import sprinklerconfig.ulduzsoft.com.sprinklerconfig.model.Zone;


public class AddProgramActivity extends Activity{
    ProgramDAO dataSource;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);

        dataSource = new ProgramDAO(this);

        final EditText programName = (EditText) findViewById(R.id.txt_progrm_name);

        final CheckBox monday = (CheckBox) findViewById(R.id.checkbox_monday);
        final CheckBox tuesday = (CheckBox) findViewById(R.id.checkbox_tuesday);
        final CheckBox wednesday = (CheckBox) findViewById(R.id.checkbox_wednesday);
        final CheckBox thursday = (CheckBox) findViewById(R.id.checkbox_thursday);
        final CheckBox friday = (CheckBox) findViewById(R.id.checkbox_friday);
        final CheckBox saturday = (CheckBox) findViewById(R.id.checkbox_saturday);
        final CheckBox sunday = (CheckBox) findViewById(R.id.checkbox_sunday);

        final EditText interval = (EditText) findViewById(R.id.txt_dayinterval);


        final Button add = (Button) findViewById(R.id.btn_add);
        add.setOnClickListener(new OnClickListener() {


            public void onClick(View view) {
                Program pm = new Program();
                pm.name = programName.getText().toString();

                StringBuffer sb = new StringBuffer();
                if (monday.isChecked()) sb.append("1");
                if (tuesday.isChecked()) sb.append("2");
                if (wednesday.isChecked()) sb.append("3");
                if (thursday.isChecked()) sb.append("4");
                if (friday.isChecked()) sb.append("5");
                if (saturday.isChecked()) sb.append("6");
                if (sunday.isChecked()) sb.append("7");

                pm.days = sb.toString();

                pm.dayInterval = Integer.parseInt(interval.getText().toString());

                pm.zones = "test";



                Program p = dataSource.createProgram(pm);

                Toast.makeText(AddProgramActivity.this, "Program " + p.name + " added", Toast.LENGTH_LONG).show();
                dataSource.close();
                finish();
            }



        });
    }

    public String getZones(){
        String zones = "";
        ZonesDAO dao = new ZonesDAO(this);
        StringBuffer sb = new StringBuffer();
        List<Zone>  zoneList = dao.getAllZones();
        return zones;
    }


}