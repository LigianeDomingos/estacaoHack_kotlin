package br.com.ligiane.estacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //Criando uma lista de opções para o Spinner
        val listaGenero = arrayListOf("Selecione o gênero", "Feminino", "Masculino", "Não-binário")

        //Criando um adaptador para o Spinner
        val generoAdapter = ArrayAdapter(
                this,                                          //Contexto
                android.R.layout.simple_spinner_dropdown_item, //Layout
                listaGenero                                    //Dados
        )

        //Plugando o adaptador no Spinner
        spnCadastroGenero.adapter = generoAdapter

        //Executando (escutando) o clique do botão cadastrar
        btnCadastroCadastrar.setOnClickListener {

            //Capturando os dados digitados
            val nome = edtCadastroNome.text.toString().trim()
            val sobrenome = edtCadastroSobrenome.text.toString().trim()
            val email = edtCadastroEmail.text.toString().trim().toLowerCase()
            val senha = edtCadastroSenha.text.toString().trim()
            val genero = spnCadastroGenero.selectedItem.toString()

            //Validação dos campos
            if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty()
                    || genero == listaGenero[0]){

                //Apresentando uma mensagem de erro ao usuário
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            }else{
                //Se todos os campos foram preenchidos

                //Criando ou acessando o arquivo de preferências compartilhadas
                val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

                //Editar o arquivo
                val editPrefs = sharedPrefs.edit()

                //Preparando os dados a serem salvos
                editPrefs.putString("NOME", nome)
                editPrefs.putString("SOBRENOME", sobrenome)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)
                editPrefs.putString("GENERO", genero)

                //Salvando os dados no arquivo de preferências compartilhadas
                editPrefs.apply()

                //Apresentando mensagem de sucesso ao usuário
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()

                //Abrindo a MainActivity
                val mIntent = Intent(this, MainActivity::class.java)

                //Passando informações através da Intent
                mIntent.putExtra("INTENT_EMAIL", email)
                startActivity(mIntent)

                //Tirando todas as telas do empilhamento
                finishAffinity()


            }

        }
    }
}