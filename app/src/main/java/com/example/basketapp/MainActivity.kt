package com.example.basketapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basketapp.fragment.FragmentContent
import com.example.basketapp.matchAdapter.MatchAdapter
import com.example.basketapp.roomDatabase.entity.MatchEntity
import com.example.basketapp.utils.AppConstants
import com.example.basketapp.viewModel.MatchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_match.*
import kotlinx.android.synthetic.main.info_match.*
import kotlinx.android.synthetic.main.main_content_recycler.*
import kotlinx.android.synthetic.main.match_cardview.*

class MainActivity : AppCompatActivity() {

    private lateinit var matchViewModel: MatchViewModel
    var twoPane = false
    private lateinit var viewAdapter: MatchAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var mainContentFragment: FragmentContent



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1: Button = findViewById(R.id.all_matches)
        val button2 : Button = findViewById(R.id.finished_matches)
        val button3 : Button = findViewById(R.id.ongoing_matches)
        if(fragment_content_info_match!= null){
            twoPane = true
            mainContentFragment = FragmentContent.newInstance(MatchEntity())
            supportFragmentManager.beginTransaction().replace(R.id.fragment_content_info_match, mainContentFragment).commit()
        }else{
            twoPane = false
        }
        button1.setOnClickListener {
            matchViewModel.allMatch.observe(this, Observer {
                viewAdapter.dataChange(it)
            })
        }
        button2.setOnClickListener {
            matchViewModel.getFinished(1).observe(this, Observer {
                viewAdapter.dataChange(it)
            })
        }
        button3.setOnClickListener{
            matchViewModel.getFinished(0).observe(this, Observer {
                viewAdapter.dataChange(it)
            })
        }
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewMatchActivity::class.java)
            startActivity(intent)
        }


        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)

        matchViewModel.allMatch.observe(this, Observer { match ->
            viewAdapter.dataChange(match)
        })

        initRecycle(emptyList())

    }

    fun initRecycle(match : List<MatchEntity>){
        viewManager = LinearLayoutManager(this)


        viewAdapter = MatchAdapter(match,{ matchItem: MatchEntity-> matchItemClicked(matchItem)})

        match_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun matchItemClicked(item : MatchEntity){
        if(twoPane){
            mainContentFragment = FragmentContent.newInstance(item)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_content_info_match,mainContentFragment).commit()
            Log.d("Click", "Esta haciendo click en: "+ item.name)
        }else{
            val extras = Bundle()
            extras.putString(AppConstants.TEXT_KEY_NAME,item.name)
            startActivity(Intent(this, ActivityMatch::class.java).putExtras(extras))
            Log.d("Book: ", item.name)
        }
    }
}
