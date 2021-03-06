package sprinklerconfig.ulduzsoft.com.sprinklerconfig.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import sprinklerconfig.ulduzsoft.com.sprinklerconfig.R;
import sprinklerconfig.ulduzsoft.com.sprinklerconfig.dao.ProgramDAO;
import sprinklerconfig.ulduzsoft.com.sprinklerconfig.dao.ZonesDAO;
import sprinklerconfig.ulduzsoft.com.sprinklerconfig.model.Program;
import sprinklerconfig.ulduzsoft.com.sprinklerconfig.model.Zone;

public class SprinklerConfig extends ListActivity {
    private ProgramDAO dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprinkler_config);

        dataSource = new ProgramDAO(this);
        dataSource.open();

        List<Program> values = dataSource.getAllPrograms();

        // use the simpleCusrsor adapter to show
        // the elements in a list view
        ArrayAdapter<Program> adapter = new ArrayAdapter<Program>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

    }

    public void onClick(View view) {
        ArrayAdapter<Zone> adapter = (ArrayAdapter<Zone>) getListAdapter();
        Zone zone = null;
        switch (view.getId()){
            case R.id.addZone:
                Intent ZoneActivityIntent = new Intent(SprinklerConfig.this,
                        AddZoneActivity.class);
                startActivity(ZoneActivityIntent);
                break;
            case R.id.addProgram:
                Intent ProgramActivityIntent = new Intent(SprinklerConfig.this,
                        AddProgramActivity.class);
                startActivity(ProgramActivityIntent);
                break;
        }

        adapter.clear();
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume(){
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause(){
        dataSource.close();
        super.onPause();
    }


}
