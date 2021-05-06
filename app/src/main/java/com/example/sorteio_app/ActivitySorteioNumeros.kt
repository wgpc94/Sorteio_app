package com.example.sorteio_app

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random
var contadorSorteios = 0
lateinit var numeroInicialEdt : EditText
lateinit var numeroFinalEdt : EditText
lateinit var numeros : ArrayList<Int>


class SorteioNumeros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorteio_numeros)
        setToolbar()
        numeroInicialEdt = findViewById<EditText>(R.id.numero_inicial_Edt)
        numeroFinalEdt = findViewById<EditText>(R.id.numero_final_Edt)


        sorteios()
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun sorteios() {
        val sorteioNumerosCv = findViewById<CardView>(R.id.card_sorteios_Numeros)
        val resultadoNumerosTv = findViewById<TextView>(R.id.resultado_numeros)
        val mesmoNumeroCb = findViewById<CheckBox>(R.id.mesmo_numero)
        val quantosNumerosEdt = findViewById<EditText>(R.id.quantos_numeros_Edt)
        val sortearNumerosBtn = findViewById<Button>(R.id.button_sortear_numeros)


        sortearNumerosBtn.setOnClickListener {
            when {
                TextUtils.isEmpty(numeroInicialEdt.text.toString()) -> {
                    numeroInicialEdt.error ="Digete o numero inicial"
                    numeroInicialEdt.requestFocus()
                }
                TextUtils.isEmpty(numeroFinalEdt.text.toString()) -> {
                    numeroFinalEdt.error ="Digete o numero final"
                    numeroFinalEdt.requestFocus()
                }
                TextUtils.isEmpty(quantosNumerosEdt.text.toString()) ->{
                    quantosNumerosEdt.error = "Sortear quantos numeros"
                }
                numeroInicialEdt.text.toString().toInt()>=numeroFinalEdt.text.toString().toInt() -> {
                    var alertDialog = AlertDialog.Builder(this)
                    alertDialog.setTitle("Valor invalido")
                    alertDialog.setMessage("O numero inicial deve ser menor que o numero final")
                    alertDialog.show()
                }
                else -> {
                    if (mesmoNumeroCb.isChecked){
                        sorteioNumerosCv.visibility = View.INVISIBLE
                        resultadoNumerosTv.visibility = View.VISIBLE
                        var numeros = numeroInicialEdt.text.toString().toInt()..numeroFinalEdt.text.toString().toInt()
                        resultadoNumerosTv.text = numeros.random().toString()
                        contadorSorteios ++

                    }else{
                        sorteioNumerosCv.visibility = View.INVISIBLE
                        resultadoNumerosTv.visibility = View.VISIBLE
                        if (contadorSorteios == 0){
                            numeros = ArrayList((numeroInicialEdt.text.toString().toInt()..numeroFinalEdt.text.toString().toInt()).toList())
                        }
                        var sorteado = numeros.random()
                        resultadoNumerosTv.text = sorteado.toString()
                        numeros.remove(sorteado)
                        contadorSorteios++
                        if (contadorSorteios == quantosNumerosEdt.text.toString().toInt()){
                            sortearNumerosBtn.visibility = View.INVISIBLE
                            contadorSorteios == 0
                        }
                    }
                }
            }

        }



    }




}