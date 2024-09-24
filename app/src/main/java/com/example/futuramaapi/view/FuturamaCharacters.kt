package com.example.futuramaapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.futuramaapi.R
import com.example.futuramaapi.databinding.FragmentFuturamaCharactersBinding
import com.example.futuramaapi.model.Character

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FuturamaCharacters.newInstance] factory method to
 * create an instance of this fragment.
 */
class FuturamaCharacters : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _binding:FragmentFuturamaCharactersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFuturamaCharactersBinding.inflate(inflater,container,false)
        return binding.root
    }

    fun setData(character: Character){
        binding.tvTitleCharacter.text = character.name.run{
            "$first $middle $last" }
         binding.tvRealAge.text = character.age
         binding.tvGender.text = character.gender

         binding.tvhomePlanet.text = character.homePlanet
         binding.tvoccupation.text = character.occupation
         binding.tvSpecies.text = character.species
         binding.imageCharacter.setImageResource(0)
         val urlImage = character.images.main
         Glide.with(binding.root.context).load(urlImage).fitCenter().into(binding.imageCharacter)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FuturamaCharacters.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FuturamaCharacters().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}