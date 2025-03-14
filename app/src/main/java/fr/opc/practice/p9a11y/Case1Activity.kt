package fr.opc.practice.p9a11y

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.opc.practice.p9a11y.databinding.ActivityCase1Binding

class Case1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var quantity = 0

        binding.quantityText.text = "$quantity"
        binding.addButton.setOnClickListener {
            quantity++
            binding.quantityText.text = "$quantity"
            it.announceForAccessibility("Add pressed, total $quantity")
        }

        binding.removeButton.setOnClickListener {
            if (quantity > 0) {
                quantity--
                binding.quantityText.text = "$quantity"
                it.announceForAccessibility("Remove pressed, total $quantity")
            } else {
                Toast.makeText(this, getString(R.string.impossible_d_avoir_une_quantit_n_gative), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
