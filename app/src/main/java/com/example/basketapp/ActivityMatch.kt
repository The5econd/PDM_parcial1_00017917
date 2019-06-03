package com.example.basketapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.basketapp.fragment.FragmentContent
import com.example.basketapp.roomDatabase.entity.MatchEntity
import com.example.basketapp.utils.AppConstants
import com.example.basketapp.viewModel.MatchViewModel
import kotlinx.android.synthetic.main.activity_match.*
import kotlinx.android.synthetic.main.main_content_recycler.*

class ActivityMatch : AppCompatActivity() {

    private lateinit var matchViewModel: MatchViewModel
    var twopane = false
    private lateinit var mainContentFragment : FragmentContent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)
        if(fragment_content_info_match != null){
            twopane = true
            mainContentFragment = FragmentContent.newInstance(MatchEntity())
            supportFragmentManager.beginTransaction().replace(R.id.fragment_content_info_match, mainContentFragment).commit()

        }else{
            twopane = false
        }
        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)

        val intento = intent
        if(intento != null){
            matchViewModel.getMatchByName(intento.getStringExtra(AppConstants.TEXT_KEY_NAME)).observe(this, Observer { match ->

                var teams : List<String> = match.name.split(" vs ")
                nombre_equipo1.text = teams[0]
                nombre_equipo2.text = teams[1]
                score_e1.text = match.score1.toString()
                score_e2.text = match.score2.toString()
                nombres_equipos.text = match.name
                hora_partido.text = match.hora
                fecha_partido.text = match.fecha

                sumar3_1.setOnClickListener{
                    matchViewModel.agregar3a1(match.score1, match.id_match)
                    score_e1.text = match.score1.toString()
                }
                sumar2_1.setOnClickListener{
                    matchViewModel.agregar2a1(match.score1, match.id_match)
                    score_e1.text = match.score1.toString()
                }
                sumar1_1.setOnClickListener{
                    matchViewModel.agregar1a1(match.score1, match.id_match)
                    score_e1.text = match.score1.toString()
                }
                sumar3_2.setOnClickListener{
                    matchViewModel.agregar3a2(match.score2, match.id_match)
                    score_e2.text = match.score2.toString()
                }
                sumar2_2.setOnClickListener{
                    matchViewModel.agregar2a2(match.score2, match.id_match)
                    score_e2.text = match.score2.toString()
                }
                sumar1_2.setOnClickListener{
                    matchViewModel.agregar1a2(match.score2, match.id_match)
                    score_e2.text = match.score2.toString()
                }

                if(match.terminado == 0){
                    bt_terminar_partido.visibility = View.VISIBLE
                    sumar3_1.visibility = View.VISIBLE
                    sumar2_1.visibility = View.VISIBLE
                    sumar1_1.visibility = View.VISIBLE
                    sumar3_2.visibility = View.VISIBLE
                    sumar2_2.visibility = View.VISIBLE
                    sumar1_2.visibility = View.VISIBLE
                    bt_terminar_partido.setOnClickListener {
                        bt_editar_partido.visibility = View.VISIBLE
                        bt_terminar_partido.visibility = View.INVISIBLE


                        if(match.score1 > match.score2){
                            winner.text = nombre_equipo1.text.toString() + " wins!"
                        }
                        if(match.score2 > match.score1){
                            winner.text = nombre_equipo2.text.toString() + " wins!"
                        }
                        if(match.score2 == match.score1){
                            winner.text = "Empate!"
                        }
                        match.terminado = 1
                        matchViewModel.insertMatch(match)
                    }
                }else{
                    bt_editar_partido.visibility = View.VISIBLE
                    sumar3_1.visibility = View.INVISIBLE
                    sumar2_1.visibility = View.INVISIBLE
                    sumar1_1.visibility = View.INVISIBLE
                    sumar3_2.visibility = View.INVISIBLE
                    sumar2_2.visibility = View.INVISIBLE
                    sumar1_2.visibility = View.INVISIBLE
                    if(match.score1 > match.score2){
                        winner.text = nombre_equipo1.text.toString() + " wins!"
                    }
                    if(match.score2 > match.score1){
                        winner.text = nombre_equipo2.text.toString() + " wins!"
                    }
                    if(match.score2 == match.score1){
                        winner.text = "Empate!"
                    }
                    bt_editar_partido.setOnClickListener {
                        bt_editar_partido.visibility = View.INVISIBLE
                        bt_terminar_partido.visibility = View.VISIBLE

                        winner.text = "Quien ganara?"
                        match.terminado = 0
                        matchViewModel.insertMatch(match)
                    }
                }
            })
        }
    }
}
