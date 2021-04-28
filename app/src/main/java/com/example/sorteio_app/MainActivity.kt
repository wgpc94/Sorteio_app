package com.example.sorteio_app


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setToolbar()
        setListeners()

    }

    private fun setListeners() {
        val carNumber : CardView = findViewById(R.id.card_number)
        val nomesCoisas : CardView = findViewById(R.id.card_nomes_ou_coisas)

        carNumber.setOnClickListener{
            val intent = Intent(this,SorteioNumeros::class.java)
            startActivity(intent)
        }

        nomesCoisas.setOnClickListener{
            Toast.makeText(this,"click nomes ou coisas",Toast.LENGTH_LONG).show()
        }
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
}