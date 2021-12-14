package com.example.citygame.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.citygame.R
import com.example.citygame.databinding.EndGameFragmentBinding
import com.example.citygame.presentation.utils.BindingFragment
import com.example.citygame.presentation.viewmodels.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameEndFragment : BindingFragment<EndGameFragmentBinding>() {

    private val viewModel: GameViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = EndGameFragmentBinding.inflate(inflater, container, false)

    private fun initObservers() {
        viewModel.endGameEvent.observe(viewLifecycleOwner, { flag ->
            if (flag) updateView(R.drawable.defeat, R.string.defeat_msg)
            else updateView(R.drawable.win, R.string.win_msg)
        })
    }

    override fun EndGameFragmentBinding.onInitView() {
        initObservers()
        exitBtn.setOnClickListener { requireActivity().finish() }
    }

    private fun updateView(pic: Int, text: Int) {
        binding.cheems.setImageDrawable(ContextCompat.getDrawable(requireContext(), pic))
        binding.endGameMsg.text = getString(text)
    }
}