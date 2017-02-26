package chema.egea.canales.bateriaInfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by chema on 02/03/2016.
 */
public class PowerConnectionReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action=intent.getAction();
        if (Intent.ACTION_POWER_CONNECTED.equals(action))
        {
            Toast.makeText(context, "Batería conectada", Toast.LENGTH_SHORT).show();
        }
        if (Intent.ACTION_POWER_DISCONNECTED.equals(action))
        {
            Toast.makeText(context, "Batería desconectada", Toast.LENGTH_SHORT).show();
        }
    }
}