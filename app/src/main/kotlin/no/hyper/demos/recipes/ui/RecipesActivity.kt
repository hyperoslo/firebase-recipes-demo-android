package no.hyper.demos.recipes.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_recipes.*
import no.hyper.demos.recipes.R
import no.hyper.demos.recipes.models.Recipe

class RecipesActivity : AppCompatActivity() {

    private val recipesAdapter = RecipesAdapter()
    private lateinit var newRecipeDialog: Dialog

    private val firebaseRef by lazy { FirebaseDatabase.getInstance().getReference("recipes") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recipes)
        setSupportActionBar(toolbar)

        recipesList.adapter = recipesAdapter
        recipesList.layoutManager = LinearLayoutManager(this)

        newRecipeDialog = AlertDialog.Builder(this)
                .setView(R.layout.dialog_new_recipe)
                .setTitle(R.string.title_dialog_new_recipe)
                .setPositiveButton(R.string.submit, { dialog, which -> persistRecipe() })
                .setNegativeButton(R.string.cancel, { dialog, which -> dismissNewRecipeDialog() })
                .create()

        addRecipeButton.setOnClickListener { openNewRecipeDialog() }
    }

    private fun openNewRecipeDialog() {
        newRecipeDialog.show()
    }

    private fun dismissNewRecipeDialog() {
        val nameInput = newRecipeDialog.findViewById(R.id.recipeNameInput) as TextInputEditText

        nameInput.text = null
        newRecipeDialog.dismiss()
    }

    private fun persistRecipe() {
        val nameInput = newRecipeDialog.findViewById(R.id.recipeNameInput) as TextInputEditText
        val recipe = Recipe(nameInput.text.toString(), null)

        firebaseRef.push().setValue(recipe.payload)

        dismissNewRecipeDialog()
    }

}
