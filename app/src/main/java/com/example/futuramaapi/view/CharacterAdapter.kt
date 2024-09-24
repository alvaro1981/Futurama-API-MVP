package com.example.futuramaapi.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.futuramaapi.databinding.CharacterItemBinding
import com.example.futuramaapi.model.Character

class CharacterAdapter(val setFragmentCharacter:(Character)->Unit)
    :RecyclerView.Adapter<CharacterAdapter.FuturamaViewHolder>(){

    inner class FuturamaViewHolder(val binding: CharacterItemBinding):RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object:DiffUtil.ItemCallback<Character>(){
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var characters:List<Character>
        get() = differ.currentList
        set(value){
            differ.submitList(value)
        }
    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterAdapter.FuturamaViewHolder {
        return FuturamaViewHolder(CharacterItemBinding.inflate(LayoutInflater.from(parent.context),parent,
            false))
    }

    override fun onBindViewHolder(holder: FuturamaViewHolder, position: Int) {
        holder.binding.apply{
           val character =  characters[position]
           val nombre = "${character.name.first} ${character.name.last}"
           nameCharacter.text = nombre
            root.setOnClickListener {
                setFragmentCharacter(character)
            }
        }
    }
}