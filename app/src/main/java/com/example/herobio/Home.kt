package com.example.herobio

import android.app.DownloadManager.Query
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
//import android.widget.SearchView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition
import java.util.*
import kotlin.collections.ArrayList

class Home : AppCompatActivity() {
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Hero>
    private lateinit var tempArrayList: ArrayList<Hero>
    lateinit var imagesId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var headingTtl: Array<String>
    lateinit var hero: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        imagesId = arrayOf(
            R.drawable.soekarno,
            R.drawable.bung_tomo,
            R.drawable.soedirman,
            R.drawable.kartini,
            R.drawable.agus_salim,
            R.drawable.moh_hatta,
            R.drawable.kh_dewantara,
            R.drawable.tan_malaka,
            R.drawable.cut_nyak_dien,
            R.drawable.abdul_munis

        )

        heading = arrayOf(
            "Ir. Soekarno",
            "Bung Tomo (Sutomo)",
            "Jendral Soedirman",
            "R.A. Kartini",
            "H. Agus Salim",
            "Drs. H. Mohammad Hatta",
            "Ki Hadjar Dewantara",
            "Tan Malaka",
            "Cut Nyak Dien",
            "Abdul Munis"
        )

        headingTtl = arrayOf(
            "Koesno Sosrodihardjo, 6 Juni 1901",
            "Surabaya, 3 Oktober 1920",
            "Purbalingga, 24 Januari 1916",
            "Jepara, 21 April 1879",
            "Minangkabau, pada 8 Oktober 1884",
            "Bukittinggi, 12 Agustus 1902",
            "Yogyakarta, 2 Mei 1889",
            "Pandam Gadang, 2 Juni 1897",
            "Sumedang, 6 November 1908",
            "Sumatera Barat, 3 Juli 1883"
        )

        hero = arrayOf(
            getString(R.string.soekarno),
            getString(R.string.bung_tomo),
            getString(R.string.soedirman),
            getString(R.string.kartini),
            getString(R.string.agus_salim),
            getString(R.string.moh_hatta),
            getString(R.string.kh_dewantata),
            getString(R.string.tan_malaka),
            getString(R.string.cut_nyak_dien),
            getString(R.string.abdul_munis)
        )

        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Hero>()
        tempArrayList = arrayListOf<Hero>()
        getUserData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_item,menu)
        val item = menu?.findItem(R.id.search_action)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                tempArrayList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()) {

                    newArrayList.forEach{
                        if (it.heading.toLowerCase(Locale.getDefault()).contains(searchText)) {

                            tempArrayList.add(it)
                        }
                    }

                    newRecyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    tempArrayList.clear()
                    tempArrayList.addAll(newArrayList)
                    newRecyclerView.adapter!!.notifyDataSetChanged()
                }

                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun getUserData() {
        for(i in imagesId.indices){

            val hero = Hero(imagesId[i], heading[i], headingTtl[i])
            newArrayList.add(hero)
        }

        tempArrayList.addAll(newArrayList)

        tempArrayList.sortedByDescending {

            it.heading
        }

        var adapter = MyAdapter(tempArrayList)
        newRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener{
            override fun onItemClick(position: Int){

                val intent = Intent(this@Home, HeroActivity::class.java)
                intent.putExtra("heading", newArrayList[position].heading)
                intent.putExtra("imageId", newArrayList[position].judulGambar)
                intent.putExtra("hero",hero[position])

                startActivity(intent)
            }
        })
        //newRecyclerView.adapter = MyAdapter(newArrayList)
    }
}