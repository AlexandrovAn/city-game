package com.example.citygame.presentation.fragments

import android.text.InputFilter
import com.example.citygame.GameApp
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.citygame.R
import com.example.citygame.data.websocket.SocketHandler
import com.example.citygame.databinding.GameFragmentBinding
import com.example.citygame.presentation.utils.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameFragment : BindingFragment<GameFragmentBinding>() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = GameFragmentBinding.inflate(inflater, container, false)

    override fun GameFragmentBinding.onInitView() {
        cityValue.filters = arrayOf(InputFilter.AllCaps())

        val socket = SocketHandler.socket()

        validateBtn.setOnClickListener {
            socket.emit("counter")
            socket.emit("city", cityValue.text.toString())
        }

        socket.on("city") { args ->
            args[0]?.let { word ->
                val city = word as String
                uiScope.launch {
                    currentWord.text = getString(R.string.step_counter, city)
                }
            }
        }

        socket.on("counter") { args ->
            if (args[0] != null) {
                val counter = args[0] as Int
                uiScope.launch {
                    stepCounter.text = getString(R.string.step_counter, counter.toString())
                }
            }
        }

    }
}