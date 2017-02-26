package chema.egea.canales.bateriaInfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private TextView bateriaInfo;
    private ProgressBar barraProgreso;
    private BatteryManager datosLollipop;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bateriaInfo = (TextView) findViewById(R.id.infoBateria);
        this.registerReceiver(this.bateriainfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        BatteryLevelReceiver bateriaBaja = new BatteryLevelReceiver();
        this.registerReceiver(bateriaBaja, new IntentFilter(Intent.ACTION_BATTERY_LOW));

        barraProgreso = (ProgressBar)findViewById(R.id.porcentajeBateria);
        datosLollipop = (BatteryManager) getSystemService(BATTERY_SERVICE);
    }

    private BroadcastReceiver bateriainfoReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
            String technology = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
            int temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
            boolean present = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);

            String informacionBateria = "Información batería:\n";
            switch (health)
            {
                case BatteryManager.BATTERY_HEALTH_COLD:
                {
                    informacionBateria+="Vida: "+"Fría"+"\n";
                    break;
                }
                case BatteryManager.BATTERY_HEALTH_DEAD:
                {
                    informacionBateria+="Vida: "+"Muerta"+"\n";
                    break;
                }
                case BatteryManager.BATTERY_HEALTH_GOOD:
                {
                    informacionBateria+="Vida: "+"Buena"+"\n";
                    break;
                }
                case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                {
                    informacionBateria+="Vida: "+"Sobrecargada"+"\n";
                    break;
                }
                case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                {
                    informacionBateria+="Vida: "+"Sobrecalentada"+"\n";
                    break;
                }
                case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                {
                    informacionBateria+="Vida: "+"Desconocido"+"\n";
                    break;
                }
                case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                {
                    informacionBateria+="Vida: "+"Fallo sin especificar"+"\n";
                    break;
                }
            }
            switch (chargePlug)
            {
                case BatteryManager.BATTERY_PLUGGED_AC:
                {
                    informacionBateria+="Cargador conectado: "+"AC"+"\n";
                    break;
                }
                case BatteryManager.BATTERY_PLUGGED_USB:
                {
                    informacionBateria+="Cargador conectado: "+"USB"+"\n";
                    break;
                }
                case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                {
                    informacionBateria+="Cargador conectado: "+"Wireless"+"\n";
                    break;
                }
            }

            informacionBateria+="Porcentaje Batería: "+level+"%\n";
            informacionBateria+="Escala batería: "+scale+"%\n";

            switch (status)
            {
                case BatteryManager.BATTERY_STATUS_CHARGING:
                {
                    informacionBateria+="Estado: "+"Cargando"+"\n";
                    break;
                }
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                {
                    informacionBateria+="Estado: "+"Descargando"+"\n";
                    break;
                }
                case BatteryManager.BATTERY_STATUS_FULL:
                {
                    informacionBateria+="Estado: "+"Cargada al completo"+"\n";
                    break;
                }
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                {
                    informacionBateria+="Estado: "+"No está en carga"+"\n";
                    break;
                }
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                {
                    informacionBateria+="Estado: "+"Desconocido"+"\n";
                    break;
                }
            }


            informacionBateria+="Tecnología: "+technology+"\n";
            informacionBateria+="Temperatura: "+temp+"K\n";
            informacionBateria+="Voltaje: "+voltage+"mV\n";

            if (present)
            {
                informacionBateria+="Batería conectada: "+"Sí"+"\n";
            }
            else
            {
                informacionBateria+="Batería conectada: "+"No"+"\n";
            }


            //DE LA API 21
            int remainingCapacity = datosLollipop.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
            int batteryCapacityMicroAh = datosLollipop.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
            int averageCurrentMicroA = datosLollipop.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE);
            int currentNow = datosLollipop.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW);
            long contadorEnergia = datosLollipop.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER);


            informacionBateria+="----------------------\n";
            informacionBateria+="Datos extras API 21:\n";
            informacionBateria+="Batería restante en %: "+ remainingCapacity+"%\n";
            informacionBateria+="Batería restante en µA/h: "+ batteryCapacityMicroAh+"\n";
            informacionBateria+="Media de µA actual: "+ averageCurrentMicroA+"\n";
            informacionBateria+="Batería instantánea actual en µA: "+ currentNow+"\n";
            informacionBateria+="Contador de energía en nanovatios/hora: "+ contadorEnergia+"\n";

            informacionBateria+="----------------------\n";
            informacionBateria+="Barra progreso batería:";
            bateriaInfo.setText(informacionBateria);

            barraProgreso.setMax(scale);
            barraProgreso.setProgress(level);
        }
    };
}
