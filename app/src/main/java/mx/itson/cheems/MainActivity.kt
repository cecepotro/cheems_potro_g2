package mx.itson.cheems

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var evilCheems = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnReset =  findViewById<Button>(R.id.reset)
        btnReset.setOnClickListener(this)
        start()
    }

    fun start(){
        for(i in 1..6){
            val btnSelect = findViewById<View>(
                resources.getIdentifier(
                    "option$i", "id", this.packageName
                )
            ) as ImageButton
            btnSelect.setOnClickListener(this)
            btnSelect.setBackgroundResource(R.drawable.icon_pregunta)
        }
        evilCheems = (1..6).random()
        Log.d("Evil Cheems", evilCheems.toString())
    }

    fun flip(option : Int){
        if(option == evilCheems){
            // Ya perdió

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                val vibratorManager = applicationContext.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                val vibrator = vibratorManager.defaultVibrator
                vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
            }else{
                val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE)  as Vibrator
                vibrator.vibrate(1000)
            }



            Toast.makeText(this,getString(R.string.game_over_text), Toast.LENGTH_LONG).show()
            for(i in 1..6){
                val btnSelect = findViewById<View>(
                    resources.getIdentifier(
                        "option$i", "id", this.packageName
                    )
                ) as ImageButton
                if(i == option){
                    btnSelect.setBackgroundResource(R.drawable.icon_cheems_llora)
                } else{
                    btnSelect.setBackgroundResource(R.drawable.icon_cheems)
                }
            }

        }else{
            val btnSelect = findViewById<View>(
                resources.getIdentifier(
                    "option$option", "id", this.packageName
                )
            ) as ImageButton
            btnSelect.setBackgroundResource(R.drawable.icon_cheems)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.option1 -> {flip(1)}
            R.id.option2 -> {flip(2)}
            R.id.option3 -> {flip(3)}
            R.id.option4 -> {flip(4)}
            R.id.option5 -> {flip(5)}
            R.id.option6 -> {flip(6)}
            R.id.reset -> start()
        }
    }

}