package fr.opc.practice.p9a11y

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import fr.opc.practice.p9a11y.databinding.ActivityCase2Binding

class Case2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.favouriteButton.contentDescription = "Bouton favoris"
        binding.addRecipeToBasket.contentDescription = "Ajouter au panier"

        val cardDescription = StringBuilder().apply {
            append("${binding.productTitle.text}. ")
            append("${binding.favouriteButton.contentDescription}, ")
            append("et ${binding.addRecipeToBasket.contentDescription}.")
        }.toString()

        binding.recipeCard.contentDescription = cardDescription

        var isFavourite = false
        setFavouriteButtonIcon(isFavourite)
        binding.favouriteButton.setOnClickListener {
            isFavourite = !isFavourite
            setFavouriteButtonIcon(isFavourite)
        }

        binding.addRecipeToBasket.setOnClickListener {
            Toast.makeText(this, getString(R.string.recette_ajout_au_panier), Toast.LENGTH_SHORT)
                .show()
        }

        binding.recipeCard.setOnClickListener {
            // TODO navigate to recipe screen
        }

        ViewCompat.setAccessibilityDelegate(binding.recipeCard, object : AccessibilityDelegateCompat() {
            val favoriteAction = AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                View.generateViewId(), "Ajouter bouton favoris"
            )
            val actionButtonAction = AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                View.generateViewId(), "Ajouter au panier bouton"
            )

            override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info.addAction(favoriteAction)
                info.addAction(actionButtonAction)
            }

            override fun performAccessibilityAction(host: View, action: Int, args: Bundle?): Boolean {
                return when (action) {
                    favoriteAction.id -> {
                        binding.favouriteButton.performClick()
                        true
                    }
                    actionButtonAction.id -> {
                        binding.addRecipeToBasket.performClick()
                        true
                    }
                    else -> super.performAccessibilityAction(host, action, args)
                }
            }
        })
    }

    private fun setFavouriteButtonIcon(isFavourite: Boolean) {
            binding.favouriteButton.setImageResource( if (isFavourite) R.drawable.ic_favourite_on else R.drawable.ic_favourite_off)
            binding.favouriteButton.contentDescription = if (isFavourite) "Supprimer des favoris" else "Ajouter aux favoris"
    }
}
