package com.example.sorteio_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

lateinit var adapter : Adapter
lateinit var rvList: RecyclerView
var lista = mutableListOf<Model>()
lateinit var resultadoTv : TextView
lateinit var mesmoNomeCb : CheckBox
lateinit var sortearNomesBtn : Button
lateinit var continuarSorteioBtn : Button
lateinit var quantidadeEdt : EditText
var sorteioNomes = 0

class ActivityNomes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nomes)

        setToolbar()
        sortearNomesBtn = findViewById(R.id.button_sortear_nomes)
        quantidadeEdt = findViewById(R.id.quantos_nomesEdt)
        continuarSorteioBtn = findViewById(R.id.button_continuar)
        mesmoNomeCb = findViewById(R.id.mesmo_nome)
        resultadoTv =findViewById(R.id.resultado_nomes)


        getLista()
        rvList = findViewById(R.id.rvlista)
        rvList.layoutManager = GridLayoutManager(this, 2)
        initSorteio()
    }


    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun getLista() {
        val nomeEDT = findViewById<EditText>(R.id.adicionar_Edt)
        val adicionarBtn = findViewById<Button>(R.id.adicionar_button)


        adicionarBtn.setOnClickListener {
            if (!TextUtils.isEmpty(quantidadeEdt.text.toString())) {

                if (TextUtils.isEmpty(nomeEDT.text.toString())) {
                    nomeEDT.error = "Digete um nome"
                    nomeEDT.requestFocus()
                } else {
                    if (lista.contains(Model(nomeEDT.text.toString()))) {
                        nomeEDT.error = "Nome ja digitado"
                        nomeEDT.requestFocus()
                    } else {
                        lista.add(Model(nomeEDT.text.toString()))
                        adapter = Adapter(lista)
                        rvList.adapter = adapter
                        setButonSortear()
                    }

                }
            } else {
                quantidadeEdt.error = "Quantidade"
                nomeEDT.requestFocus()
            }


        }

    }

    fun setButonSortear() {
        if (lista.size > quantidadeEdt.text.toString().toInt()) {
            sortearNomesBtn.visibility = View.VISIBLE
        } else if (lista.size < 2) {
            sortearNomesBtn.visibility = View.GONE
        }
    }


    private fun initSorteio() {
        val cardNomesCv = findViewById<CardView>(R.id.card_nomes)

        sortearNomesBtn.setOnClickListener {
            cardNomesCv.visibility = View.GONE
            resultadoTv.visibility = View.VISIBLE
            sorteio()

            if (sorteioNomes <= quantidadeEdt.text.toString().toInt() ) {
                continuarSorteioBtn.visibility = View.VISIBLE
                continuarSorteioBtn.setOnClickListener {
                    sorteio()
                }
            }
        }

    }


    private fun sorteio() {
        if (mesmoNomeCb.isChecked) {
            resultadoTv.text = lista.random().nome
        } else {
            val arrayResultados = mutableListOf<String>()
            val resultadoAAdicionar = lista.random().nome
            if (!arrayResultados.contains(resultadoAAdicionar)) {
                resultadoTv.text = resultadoAAdicionar
            }
        }
        sorteioNomes++
        if (sorteioNomes == quantidadeEdt.text.toString().toInt()){
            continuarSorteioBtn.visibility = View.GONE
        }
    }

}






