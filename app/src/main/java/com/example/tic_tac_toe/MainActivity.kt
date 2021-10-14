package com.example.tic_tac_toe

import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var player1=0
    var player2=1
    var activeplayer=player1
    lateinit var filled: IntArray
    var gameActive=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        filled= intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)
        b0.setOnClickListener(this)
        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        b4.setOnClickListener(this)
        b5.setOnClickListener(this)
        b6.setOnClickListener(this)
        b7.setOnClickListener(this)
        b8.setOnClickListener(this)
    }
    @RequiresApi(Build.VERSION_CODES.M)

    override fun onClick(v:View){
        if(!gameActive)
            return
var btnClicked=findViewById<Button>(v!!.id)
        var clickedtag=Integer.parseInt(btnClicked.tag.toString())
         if(filled[clickedtag]!=-1)
         return
        filled[clickedtag]=activeplayer
        if(activeplayer==player1){
            btnClicked.setText("0")
            activeplayer=player2
textView2.setText("Player-2 Turn")
btnClicked.setTextColor(Color.BLACK)
            btnClicked.backgroundTintList=getColorStateList(R.color.yellow)
        }else{
            btnClicked.setText("x")
            activeplayer=player1
            textView2.setText("Player-1  Turn")
            btnClicked.setTextColor(Color.BLACK)
            btnClicked.backgroundTintList=getColorStateList(R.color.green)
        }
checkwin()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkwin() {
        var win = arrayOf(
            intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )
        for (i in 0 until win.size) {
            var v1 = win[i][0]
            var v2 = win[i][1]
            var v3 = win[i][2]
            if (filled[v1] == filled[v2] && filled[v2] == filled[v3]) {
                if (filled[v1] != -1) {
                    gameActive=false
                    if (filled[v1] == player1) {
                        showMessage("Player-1 is Win")
                        //textView2.setText("Player-1 is Win")
                    } else  {
                        showMessage("Player-2 is Win")
                        //textView2.setText("Player-2 is Win")

                    }

                    return
                }
            }

        }
        var count=0
        for (i in 0 until filled.size) {
            if (filled[i] == -1) {
                count++
            }


        }
        if(count==0){
           showMessage("It a Draw")
            return
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showMessage(s: String) {
        AlertDialog.Builder(this)
            .setMessage(s)
            .setTitle("Tic Tac Toe")
            .setPositiveButton("Restart name", DialogInterface.OnClickListener { dialogInterface, i ->
                restartgame()
            } )
            .show()


    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun restartgame() {
        filled= intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)
        activeplayer=player1
        gameActive=true
        textView2.setText("Player-1  Turn")
        b0.setText("")
        b1.setText("")
        b2.setText("")
        b3.setText("")
        b4.setText("")
        b5.setText("")
        b6.setText("")
        b7.setText("")
        b8.setText("")
        b0.backgroundTintList=getColorStateList(R.color.purple_500)
        b1.backgroundTintList=getColorStateList(R.color.purple_500)
        b2.backgroundTintList=getColorStateList(R.color.purple_500)
        b3.backgroundTintList=getColorStateList(R.color.purple_500)
        b4.backgroundTintList=getColorStateList(R.color.purple_500)
        b5.backgroundTintList=getColorStateList(R.color.purple_500)
        b6.backgroundTintList=getColorStateList(R.color.purple_500)
        b7.backgroundTintList=getColorStateList(R.color.purple_500)
        b8.backgroundTintList=getColorStateList(R.color.purple_500)


    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putIntArray("filled", filled)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

       filled = savedInstanceState.getIntArray("filled")!!

    }
}



