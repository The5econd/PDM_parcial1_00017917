package com.example.basketapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.basketapp.R
import com.example.basketapp.roomDatabase.entity.MatchEntity
import com.example.basketapp.viewModel.MatchViewModel
import kotlinx.android.synthetic.main.activity_match.*
import kotlinx.android.synthetic.main.activity_match.view.*
import kotlinx.android.synthetic.main.info_match.*
import kotlinx.android.synthetic.main.info_match.view.*

class FragmentContent : Fragment(){

    var match = MatchEntity()
    private lateinit var matchViewModel : MatchViewModel

    companion object{
        fun newInstance(match : MatchEntity) : FragmentContent{
            val newFragment = FragmentContent()
            newFragment.match = match
            return newFragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.info_match, container, false)
        bindData(view)

        return view
    }

    fun bindData(view: View){
        view.nombres_equipos_ls.text = match.name
        view.score_e1_ls.text = match.score1.toString()
        view.score_e2_ls.text = match.score2.toString()
        view.fecha_partido_ls.text = match.fecha
        view.hora_partido_ls.text = match.hora
    }
}