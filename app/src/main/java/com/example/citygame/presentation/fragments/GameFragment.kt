package com.example.citygame.presentation.fragments

import android.text.InputFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.Navigation
import com.example.citygame.R
import com.example.citygame.databinding.GameFragmentBinding
import com.example.citygame.presentation.utils.BindingFragment
import com.example.citygame.presentation.viewmodels.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.game_fragment.*

@AndroidEntryPoint
class GameFragment : BindingFragment<GameFragmentBinding>() {

    private val viewModel: GameViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private var lastCharacter = ""
    private var step = ""
    private var id = ""

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = GameFragmentBinding.inflate(inflater, container, false)

    private fun initObservers() {
        viewModel.word.observe(viewLifecycleOwner, { word ->
            lastCharacter = word.last().toString()
            currentWord.text = getString(R.string.word, word, lastCharacter)
        })
        viewModel.counter.observe(viewLifecycleOwner, { counter ->
            step = counter
            stepCounter.text = getString(R.string.step_counter, counter.toString())
        })
        viewModel.clientId.observe(viewLifecycleOwner, {
            id = it
            idText.text = getString(R.string.id_text, id)
        })
        viewModel.loser.observe(viewLifecycleOwner, { navigateToEndGame() })
    }

    override fun GameFragmentBinding.onInitView() {
        initObservers()
        cityValue.filters = arrayOf(InputFilter.AllCaps())
        validateBtn.setOnClickListener { validate() }
        surrenderBtn.setOnClickListener {
            viewModel.endGame(id)
            navigateToEndGame()
        }
    }

    private fun GameFragmentBinding.validate() {
        val firstCharacter =
            if (!cityValue.text.isNullOrEmpty()) cityValue.text?.first().toString() else ""
        val lastCharacterCondition = firstCharacter != lastCharacter
        val stepCondition = step != "1"
        val emptyFieldCondition = cityValue.text.isNullOrEmpty()
        if ((lastCharacterCondition && stepCondition) || emptyFieldCondition) {
            setError(R.string.error_msg)
        } else {
            setError(0)
            viewModel.sendValues(cityValue.text.toString())
        }
    }

    private fun navigateToEndGame() =
        Navigation.findNavController(binding.root).navigate(R.id.end_game_fragment)

    private fun setError(id: Int) {
        if (id != 0) textInputLayout.error = getString(id) else textInputLayout.error = null
    }
}