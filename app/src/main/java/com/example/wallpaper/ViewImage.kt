package com.example.wallpaper

import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.wallpaper.DataClass.data_classItem
import com.example.wallpaper.RoomDatabase.image_entity
import com.example.wallpaper.RoomDatabase.image_viewmodel
import com.example.wallpaper.databinding.ActivitySearchviewBinding
import com.example.wallpaper.databinding.ActivityViewImageBinding
import com.example.wallpaper.databinding.BottomSheetForInfoBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.IOException

class ViewImage : AppCompatActivity() {

    var binding:ActivityViewImageBinding ?=null
    var bottomsheetbinding: BottomSheetForInfoBinding ?=null
    private lateinit var dialog:BottomSheetDialog
    private val imageviewmodel: image_viewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewImageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //setting up back button
        setSupportActionBar(binding?.viewToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.viewToolbar?.setNavigationOnClickListener(){
            val intent = Intent(this@ViewImage, MainActivity::class.java)
            startActivity(intent)
        }


        //recieving the data from home fragment or searchview or download frsagment
        val imageurl = intent.getStringExtra("image")
        val name = intent.getStringExtra("name")
        val insta = intent.getStringExtra("instagram")
        val twitter = intent.getStringExtra("twitter")


        //to show the image
        Glide.with(this).load(imageurl).into(binding?.imageIv!!)


        // for applying image as background
        binding?.applyCv?.setOnClickListener(){


            val bitmap = getimageview(binding?.imageIv)
            val wallpaperManager = WallpaperManager.getInstance(this)

            try {
                wallpaperManager.setBitmap(bitmap)
                Toast.makeText(this, "Wallpaper set successfully \uD83C\uDF89", Toast.LENGTH_SHORT)
                    .show()
            } catch (ex: IOException) {
                Toast.makeText(this, "Error in setting wallpaper", Toast.LENGTH_SHORT).show()
                ex.printStackTrace()
            }

    }



        //to see the image info
        binding?.infoCv?.setOnClickListener(){

            bottomsheetbinding = BottomSheetForInfoBinding.inflate(layoutInflater)
            dialog = BottomSheetDialog(this)
            dialog.setContentView(bottomsheetbinding!!.root)

            bottomsheetbinding?.userName?.text = ":   " + name.toString()
            bottomsheetbinding?.instaId?.text = ":   " + insta.toString()
            bottomsheetbinding?.twitterId?.text = ":   " + twitter.toString()


            // below line is use to set cancelable to avoid
            // closing of dialog box when clicking on the screen.
            dialog.setCancelable(true)


            // on below line we are calling
            // a show method to display a dialog.
            dialog.show()
        }



        //for saving the image
        binding?.saveCv?.setOnClickListener(){

            imageviewmodel.insert(
                image_entity(
                    id = null,
                    image = imageurl.toString(),
                    nameofuser = name.toString(),
                    insta_id = insta.toString(),
                    twitter_id = twitter.toString()
                )
            )

            Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_LONG).show()
        }



    }





// function to convert image view to bitmap
    fun getimageview(v: View?): Bitmap {

        var screenshotOfImgView: Bitmap? = null
        try {
            // inflate screenshot object
            // with Bitmap.createBitmap it
            // requires three parameters
            // width and height of the view and
            // the background color
            screenshotOfImgView =
                Bitmap.createBitmap(v!!.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)
            // Now draw this bitmap on a canvas
            val canvas = Canvas(screenshotOfImgView)
            v.draw(canvas)
        } catch (e: Exception) {
            Log.e("Error", "Failed to capture ! " + e.message)
        }
        // return the bitmap
        return screenshotOfImgView!!

    }






}