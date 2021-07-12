package com.bombadu.bitcoinexchange.ui.coins

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.bombadu.bitcoinexchange.R
import com.bombadu.bitcoinexchange.databinding.FragmentCoinsBinding
import com.bombadu.bitcoinexchange.databinding.FragmentExhangeBinding




class CoinsFragment : Fragment() {

    private val coinsViewModel: CoinsViewModel by lazy {
        ViewModelProvider(this).get(CoinsViewModel::class.java)
    }
    private lateinit var coinAdapter: CoinAdapter
    private lateinit var recyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCoinsBinding.inflate(inflater)

        /*coinsViewModel.myCoinData.observe(viewLifecycleOwner, { coinData ->
            coinData.let {
                Toast.makeText(requireContext(), "Size: ${coinData.size}", Toast.LENGTH_SHORT).show()
                coinAdapter.submitList(it)
            }

        })*/

        coinsViewModel.myTickerData.observe(viewLifecycleOwner, { tickerData ->
            tickerData.let {
                coinAdapter.submitList(it)
            }

        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.coin_list_recyclerview)
        setupRecyclerView()

    }

    private fun setupRecyclerView() = recyclerView.apply {
        coinAdapter = CoinAdapter()
        adapter = coinAdapter
        hasFixedSize()
        layoutManager = LinearLayoutManager(this.context)

    }

    companion object {
        val TAG = CoinsFragment::class.java.simpleName
    }


}