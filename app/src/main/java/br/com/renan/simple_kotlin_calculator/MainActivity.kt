
package br.com.renan.simple_kotlin_calculator

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.renan.simple_kotlin_calculator.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression
import kotlin.math.exp

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val expressionBuilder = StringBuilder()

    private var MAX_EXPRESSION_LENGTH = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calculation = binding.calculation

        // Números:
        binding.one.setOnClickListener {
            appendText("1")
        }
        binding.two.setOnClickListener {
            appendText("2")
        }
        binding.three.setOnClickListener {
            appendText("3")
        }
        binding.four.setOnClickListener {
            appendText("4")
        }
        binding.five.setOnClickListener {
            appendText("5")
        }
        binding.six.setOnClickListener {
            appendText("6")
        }
        binding.seven.setOnClickListener {
            appendText("7")
        }
        binding.eight.setOnClickListener {
            appendText("8")
        }
        binding.nine.setOnClickListener {
            appendText("9")
        }
        binding.zero.setOnClickListener {
            appendText("0")
        }


        //Parênteses
        binding.openParentheses.setOnClickListener { appendText("(") }
        binding.closeParentheses.setOnClickListener { appendText(")") }


        //Operadores
        binding.sum.setOnClickListener {
            appendText("+")
        }
        binding.subtraction.setOnClickListener {
            appendText("-")
        }
        binding.multiplications.setOnClickListener {
            appendText("×")
        }
        binding.divide.setOnClickListener {
            appendText("÷")
        }
        binding.dot.setOnClickListener {
            appendText(".")
        }

        //Apagar
        binding.backspace.setOnClickListener {
            if (expressionBuilder.isNotEmpty()) {
                expressionBuilder.deleteCharAt(expressionBuilder.length - 1)
                binding.calculation.text = expressionBuilder.toString()
            }
        }

        //Apagar tudo
        binding.ce.setOnClickListener {
            expressionBuilder.clear()
            binding.calculation.text = ""
            binding.result.text = ""
        }

        //Resultado
        binding.equals.setOnClickListener {
            val expression = expressionBuilder.toString()
                .replace("×", "*")
                .replace("÷", "/")

            val result = Expression(expression).calculate()

            if (result.isNaN()) {
                binding.result.text = "Expressão Inválida"
            } else {
                binding.result.text = formatResult(result)
            }
        }


    }

    private fun formatResult(value: Double): String {
        return if (value % 1 == 0.0) {
            // Número inteiro
            value.toLong().toString()
        } else {
            // Número decimal com limite de casas
            String.format("%.12f", value).trimEnd('0').trimEnd('.')
        }
    }

    private fun appendText(value: String){

        if (expressionBuilder.length >= MAX_EXPRESSION_LENGTH) {
            return
        }

        val operators = listOf("+", "-", "×", "÷", ".")

        // Permitir número negativo no começo da expressão
        if(expressionBuilder.isEmpty() && value == "-") {
            expressionBuilder.append(value)
            binding.calculation.text = expressionBuilder.toString()
            return
        }

        // Evitar operadores duplicados
        if(expressionBuilder.isNotEmpty()) {
            val lastChar = expressionBuilder.last().toString()
            if(operators.contains(lastChar) && operators.contains(value)) {
                return
            }
        }

        expressionBuilder.append(value)
        binding.calculation.text = expressionBuilder.toString()

    }
}