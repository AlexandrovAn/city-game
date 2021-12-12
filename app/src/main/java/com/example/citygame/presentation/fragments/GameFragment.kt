package com.example.citygame.presentation.fragments

import android.text.InputFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.citygame.R
import com.example.citygame.databinding.GameFragmentBinding
import com.example.citygame.presentation.utils.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.game_fragment.*

@AndroidEntryPoint
class GameFragment : BindingFragment<GameFragmentBinding>() {

    private val viewModel: GameViewModel by viewModels()
    private var lastCharacter = ""
    private var step = ""

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = GameFragmentBinding.inflate(inflater, container, false)

    private fun initObservers() {
        viewModel.word.observe(viewLifecycleOwner, Observer { word ->
            lastCharacter = word.last().toString()
            currentWord.text = getString(R.string.word, word, lastCharacter)
        })
        viewModel.counter.observe(viewLifecycleOwner, Observer { counter ->
            step = counter
            stepCounter.text = getString(R.string.step_counter, counter.toString())
        })
    }

    override fun GameFragmentBinding.onInitView() {
        initObservers()
        cityValue.filters = arrayOf(InputFilter.AllCaps())
        validateBtn.setOnClickListener { validate() }
    }

    private fun GameFragmentBinding.validate() {
        val firstCharacter =
            if (!cityValue.text.isNullOrEmpty()) cityValue.text?.first().toString() else ""
        val lastCharacterCondition = firstCharacter != lastCharacter
        val stepCondition = step != "1"
        val emptyFieldCondition = cityValue.text.isNullOrEmpty()
        if ((lastCharacterCondition && stepCondition) || emptyFieldCondition) {
            textInputLayout.error = getString(R.string.error_msg)
        } else {
            textInputLayout.error = null
            viewModel.sendValues(cityValue.text.toString())
        }
    }
}