package no.hyper.demos.recipes.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recipes.*
import no.hyper.demos.recipes.R
import no.hyper.demos.recipes.models.Recipe

class RecipesActivity : AppCompatActivity() {

    private val recipesAdapter = RecipesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recipes)
        setSupportActionBar(toolbar)

        recipesList.adapter = recipesAdapter
        recipesList.layoutManager = LinearLayoutManager(this)

        populateDummyRecipes()
    }

    private fun populateDummyRecipes() {
        val dummyRecipes = listOf(
                Recipe("Angel Delight"),
                Recipe("Butter Braid"),
                Recipe("Cool Whip"),
                Recipe("Dream Whip"),
                Recipe("Entenmann's"),
                Recipe("Fruitcake"),
                Recipe("Gingerbread"),
                Recipe("Hummingbird Cake"),
                Recipe("Ice Cream"),
                Recipe("Jell-O"),
                Recipe("Kladdkaka"),
                Recipe("Ladyf"),
                Recipe("My-T-Fine"),
                Recipe("Meringue"),
                Recipe("Ontbijtkoek"),
                Recipe("Pavlova"),
                Recipe("Queen of Puddings"),
                Recipe("Rum baba"),
                Recipe("Swiss roll"),
                Recipe("Tiramisu"),
                Recipe("Upside-down cake"),
                Recipe("Vla"),
                Recipe("White sugar sponge cake"),
                Recipe("Xurros"),
                Recipe("YoGo"),
                Recipe("Zefir")
        )

        dummyRecipes.forEach { recipesAdapter.add(it) }
    }

}
