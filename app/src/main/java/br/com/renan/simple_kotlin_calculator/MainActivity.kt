
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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calculation = binding.calculation

        binding.one.setOnClickListener {
            calculation.text = "${calculation.text}1"
        }

        binding.two.setOnClickListener {
            calculation.text = "${calculation.text}2"
        }

        binding.three.setOnClickListener {
            calculation.text = "${calculation.text}3"
        }

        binding.four.setOnClickListener {
            calculation.text = "${calculation.text}4"
        }

        binding.five.setOnClickListener {
            calculation.text = "${calculation.text}5"
        }

        binding.six.setOnClickListener {
            calculation.text = "${calculation.text}6"
        }

        binding.seven.setOnClickListener {
            calculation.text = "${calculation.text}7"
        }

        binding.eight.setOnClickListener {
            calculation.text = "${calculation.text}8"
        }

        binding.nine.setOnClickListener {
            calculation.text = "${calculation.text}9"
        }

        binding.zero.setOnClickListener {
            calculation.text = "${calculation.text}0"
        }

        binding.openParentheses.setOnClickListener {
            calculation.text = "${calculation.text}("
        }

        binding.closeParentheses.setOnClickListener {
            calculation.text = "${calculation.text})"
        }

        binding.divide.setOnClickListener {
            calculation.text = "${calculation.text}÷"
        }

        binding.multiplications.setOnClickListener {
            calculation.text = "${calculation.text}×"
        }

        binding.subtraction.setOnClickListener {
            calculation.text = "${calculation.text}–"
        }

        binding.sum.setOnClickListener {
            calculation.text = "${calculation.text}+"
        }

        binding.dot.setOnClickListener {
            calculation.text = "${calculation.text}."
        }

        binding.backspace.setOnClickListener {
            calculation.text = calculation.text.dropLast(1)
        }

        binding.ce.setOnClickListener {
            calculation.text = ""
            binding.result.text = ""
        }

        binding.equals.setOnClickListener {
            val expression = calculation.text.toString()
                .replace("×", "*")
                .replace("÷", "/")
                .replace("–", "-")

            val calculatedResult = Expression(expression).calculate()

            if (calculatedResult.isNaN()) {
                binding.result.text = "Expressão Inválida"
            } else {
                binding.result.text = calculatedResult.toString()
            }
        }

    }
}