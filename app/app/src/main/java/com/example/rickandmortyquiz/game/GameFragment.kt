package com.example.rickandmortyquiz.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import com.example.rickandmortyquiz.R
import com.example.rickandmortyquiz.databinding.FragmentGameBinding
import kotlin.math.log

class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater, R.layout.fragment_game, container, false)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        viewModel.newGame()

        binding.gameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer<Boolean> { gameOver ->
            if(gameOver) gameFinished()
        })


        return binding.root
    }

    private fun gameFinished(){
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val action = GameFragmentDirections.actionGameFragmentToGameOverFragment(viewModel.scoreString.value)
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onGameFinishComplete()
    }
}