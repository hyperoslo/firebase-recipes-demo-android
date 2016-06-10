package no.hyper.demos.recipes.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_recipe.view.*
import no.hyper.demos.recipes.R
import no.hyper.demos.recipes.models.Recipe

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    private val recipes = arrayListOf<Recipe>()

    override fun getItemCount() = recipes.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recipe = recipes[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    fun add(recipe: Recipe) {
        this.recipes.add(recipe)
        notifyItemInserted(this.recipes.count() - 1)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val nameTextView = view.recipeNameText
        private val ingredientsCountTextView = view.ingredientsCountText

        var recipe: Recipe? = null
            set(value) {
                nameTextView.text = value?.name
                ingredientsCountTextView.text = "6 ingredients"
            }

    }

}