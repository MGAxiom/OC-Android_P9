package fr.opc.practice.p9a11y

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import fr.opc.practice.p9a11y.databinding.ActivityCase3Binding

class Case3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.pseudoEdit.doOnTextChanged { text, _, _, _ ->
            text?.length?.let { textLength ->
                binding.validateButton.isEnabled = textLength > 2
                binding.pseudoEdit.backgroundTintList = if (textLength > 2) {
                    ColorStateList.valueOf(resources.getColor(R.color.green400, theme))
                } else {
                    ColorStateList.valueOf(resources.getColor(R.color.red400, theme))
                }
                if (textLength < 2) {
                    binding.pseudoEdit.error = "Pas assez de caractères"
                } else {
                    binding.pseudoEdit.announceForAccessibility("Pseudo valide")
                }
            }
        }
        binding.validateButton.isEnabled = false
    }
}
