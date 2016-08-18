package sprinklerconfig.ulduzsoft.com.sprinklerconfig.activities;

/**
 * Created by syunaeva on 8/6/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        final Button add = (Button) findViewById(R.id.btn_add);
        add.setOnClickListener(new OnClickListener() {


            public void onClick(View view) {
                Toast.makeText(AddProgramActivity.this, "New program added", Toast.LENGTH_LONG).show();
                dataSource.close();
                finish();
            }



        });
    }


}