package no.hyper.demos.recipes.ui

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.item_recipe.view.*
import no.hyper.demos.recipes.R
import no.hyper.demos.recipes.models.Recipe

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    private val firebaseRef = FirebaseDatabase.getInstance().getReference("recipes")

    private val recipes = arrayListOf<Recipe>()

    init {
        firebaseRef.orderByChild("name").addChildEventListener(object: ChildEventListener {

            override fun onChildMoved(snapshot: DataSnapshot, previousKey: String?) {
                val previousIndex = recipes.indexOfFirst { it.firebaseKey == snapshot.key }
                val recipe = buildRecipe(snapshot)

                recipes.removeAt(previousIndex)

                val newIndex = recipes.indexOfFirst { it.firebaseKey == previousKey } + 1
                recipes.add(newIndex, recipe)

                notifyItemMoved(previousIndex, newIndex)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousKey: String?) {
                val index = recipes.indexOfFirst { it.firebaseKey == previousKey } + 1
                val recipe = buildRecipe(snapshot)

                recipes[index] = recipe
                notifyItemChanged(index)
            }

            override fun onChildAdded(snapshot: DataSnapshot, previousKey: String?) {
                val previousIndex = recipes.indexOfFirst { it.firebaseKey == previousKey }
                val recipe = buildRecipe(snapshot)
                val index = previousIndex + 1

                recipes.add(index, recipe)
                notifyItemInserted(index)
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val recipe = findRecipe(snapshot)
                val index = recipes.indexOf(recipe)

                recipes.remove(recipe)
                notifyItemRemoved(index)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(this.javaClass.simpleName, "Firebase child event cancelled", error.toException())
            }

            private fun buildRecipe(snapshot: DataSnapshot): Recipe {
                val name = snapshot.child("name").getValue(String::class.java)
                val key = snapshot.key

                return Recipe(name, key)
            }

            private fun findRecipe(snapshot: DataSnapshot) = recipes.find { it.firebaseKey == snapshot.key }

        })
    }

    override fun getItemCount() = recipes.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recipe = recipes[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val nameTextView = view.recipeNameText

        var recipe: Recipe? = null
            set(value) {
                nameTextView.text = value?.name
            }

    }

}