package br.com.ligiane.estacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recuperar o email passado através da Intent
        val email = intent.getStringExtra("INTENT_EMAIL")

        // Acessar o arquivo de preferencias compartilhadas
        val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

        // Recuperar os dados no arquivo de preferências compartilhadas
        val nome = sharedPrefs.getString("NOME", "")
        val sobrenome = sharedPrefs.getString("SOBRENOME", "")
        val genero = sharedPrefs.getString("GENERO", "")

        // Exibir os dados recuperados e testar o App
        txvMainNome.text = "$nome $sobrenome"
        txvMainEmail.text = email
        txvMainGenero.text = genero

        // Botão site Cel.Lep
        btnMainSite.setOnClickListener {
            val mIntent = Intent(this, WebActivity::class.java)
            startActivity(mIntent)
        }

        // Botão sair
        btnMainSair.setOnClickListener {

            // Criar a caixa de dialogo
            val alert = AlertDialog.Builder(this)

            // Definir o título
            alert.setTitle("Atenção")

            // Definir a mensagem
            alert.setMessage("Deseja mesmo sair?")

            // Definir o rótudo e as ações do botão positivo
            alert.setPositiveButton("Sair") { dialog, which ->

                val mIntent = Intent(this, LoginActivity::class.java)
                startActivity(mIntent)
                finishAffinity()

            }

            // Definir o rótulo e as ações do botão neutro
            alert.setNeutralButton("Não") { dialog, which -> }

            // Definir se a caixa é cancelável
            alert.setCancelable(false)

            // Exibir a caixa de dialogo
            alert.show()

        }

    }

}