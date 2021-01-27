package mx.tecnm.tepic.tap_u5_practica4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var vector= ArrayList<String>()
    var timer=object :CountDownTimer(2000,200){
        override fun onTick(p0: Long) {
            mostrarData()
        }
        override fun onFinish() {
            start()
        }


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timer.start()

        button.setOnClickListener {
            var cadena="Nombre : ${Nombre.text.toString()}\n Edad: ${edad.text.toString()}"+"\n Telefono: ${telefono.text.toString()}"
            vector.add(cadena)
            Toast.makeText(this, "Se capturo con exito",Toast.LENGTH_SHORT).show()
            Nombre.setText("")
            edad.setText("")
            telefono.setText("")
        }
        button2.setOnClickListener {
            var campPosicion=EditText(this)

            campPosicion.inputType= InputType.TYPE_CLASS_NUMBER
            AlertDialog.Builder(this)
                .setTitle("Escriba posicion a eliminar")
                .setView(campPosicion)
                .setPositiveButton("Borrar"){d,i->
                    eliminar(campPosicion.text.toString().toInt())
                }
                .setNegativeButton("Cancelar"){d,i->}
                .show()

        }
    }
    fun eliminar(posicion:Int){
        try {
            vector.removeAt(posicion)
            Toast.makeText(this,"Se Borro con exito",Toast.LENGTH_SHORT).show()

        }catch (e:Exception){
            AlertDialog.Builder(this).setTitle("ERROR").setMessage(e.message).show()

        }

    }
    fun mostrarData(){
        var cadena="MOSTRAR: \n"
        if(vector.size==0){
            textView.setText("MOSTRAR: \n No esxisten datos aun")
            return

        }
        var ultimoIndice=vector.size-1;
        (0..ultimoIndice).forEach {
            cadena+= "POSICION: ${it}\n ${vector.get(it)}\n\n"
        }

    }
}