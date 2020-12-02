package com.superherosquadmaker.feature.user_repo.listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.superherosquadmaker.R
import com.superherosquadmaker.feature.user_repo.viewstate.HeroViewState

class HeroesAdapter(private val callback: (id: Int) -> Unit) : RecyclerView.Adapter<HeroesAdapter.ViewHolder>() {

    private val repositoryItems: MutableList<HeroViewState> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.marvel_hero_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = repositoryItems[position]
        holder.bindTo(item)
    }

    fun addAll(items: List<HeroViewState>) {
        repositoryItems.clear()
        repositoryItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = repositoryItems.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.ownerImage)
        private val name = itemView.findViewById<TextView>(R.id.repoName)

        fun bindTo(item: HeroViewState) {
            image.load(item.urlImage) {
                transformations(CircleCropTransformation())
            }
            name.text = item.name

            itemView.setOnClickListener {
                callback(item.id)
            }
        }
    }
}
