package sprinklerconfig.ulduzsoft.com.sprinklerconfig.model;

/**
 * Created by syunaeva on 8/6/16.
 */
public class Zone{
    private long id;
    private String zoneName;
    private int TONE_DTMF;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String get(){
        return zoneName;
    }

    public void setZoneTone(int d){
        this.TONE_DTMF = d;
    }

    public int getZoneTone(){
        return this.TONE_DTMF;
    }

    public void setZone(String zoneName) {
        this.zoneName = zoneName;
    }

    @Override
    public String toString(){
        return zoneName;
    }
}
