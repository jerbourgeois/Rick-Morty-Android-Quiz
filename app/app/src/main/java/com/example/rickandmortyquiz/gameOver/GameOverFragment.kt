package com.example.rickandmortyquiz.gameOver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.rickandmortyquiz.R
import com.example.rickandmortyquiz.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentGameOverBinding>(inflater, R.layout.fragment_game_over, container, false)

        //Set game over text somewhere here
        binding.gameoverTextview.text = GameOverFragmentArgs.fromBundle(requireArguments()).scoreString

        return binding.root
    }
}