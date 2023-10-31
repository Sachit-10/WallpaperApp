package com.example.wallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.wallpaper.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    var binding:ActivityMainBinding ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        replacefragment(Home_Fragment())

        binding?.bottomNavigationView?.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home1 -> replacefragment(Home_Fragment())
                R.id.download1 ->    replacefragment(Download_Fragment())
                }
            true
            }
        }

    fun replacefragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_layout , fragment).commit()

    }
}