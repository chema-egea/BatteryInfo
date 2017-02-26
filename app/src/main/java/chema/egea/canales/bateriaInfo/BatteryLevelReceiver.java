package chema.egea.canales.bateriaInfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by chema on 02/03/2016.
 */
public class BatteryLevelReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action=intent.getAction();
        if (Intent.ACTION_BATTERY_LOW.equals(action))
        {
            Toast.makeText(context, "Batería BAJA", Toast.LENGTH_SHORT).show();
        }
    }
}