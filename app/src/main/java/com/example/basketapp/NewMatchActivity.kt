package com.example.basketapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.basketapp.roomDatabase.entity.MatchEntity
import com.example.basketapp.viewModel.MatchViewModel
import kotlinx.android.synthetic.main.activity_new_match.*

class NewMatchActivity : AppCompatActivity() {

    private lateinit var viewmodel : MatchViewModel
    private lateinit var match : MatchEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_match)

        viewmodel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        bt_agregar.setOnClickListener{
            if(et_nombre_equipo1.text.isEmpty() or et_nombre_equipo2.text.isEmpty() or et_fecha_anio.text.isEmpty() or
            et_fecha_mes.text.isEmpty() or et_fecha_dia.text.isEmpty() or et_hora_horas.text.isEmpty() or et_hora_minutos.text.isEmpty()){
                Toast.makeText(this, "Por favor llene todos los campos",Toast.LENGTH_LONG).show()
            }else{

                var nombreEquipo1 : String = et_nombre_equipo1.text.toString()
                var nombreEquipo2 : String = et_nombre_equipo2.text.toString()
                var nombres_unidos = "$nombreEquipo1 vs $nombreEquipo2"

                var fechdia : String = et_fecha_dia.text.toString()
                var fechmes : String = et_fecha_mes.text.toString()
                var fechanio : String = et_fecha_anio.text.toString()
                var fecha = "$fechdia/$fechmes/$fechanio"

                var horhoras : String = et_hora_horas.text.toString()
                var horaminutos : String = et_hora_minutos.text.toString()
                var hora = "$horhoras:$horaminutos"

                match = MatchEntity(
                        nombres_unidos,
                        0,
                        0,
                        0,
                        fecha,
                        hora)

                viewmodel.insertMatch(match)
            }
        }
    }
}
