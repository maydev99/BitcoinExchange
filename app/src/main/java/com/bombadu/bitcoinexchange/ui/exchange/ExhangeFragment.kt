package com.bombadu.bitcoinexchange.ui.exchange

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.bitcoinexchange.R
import com.bombadu.bitcoinexchange.databinding.FragmentExhangeBinding
import java.lang.Exception


class ExchangeFragment : Fragment() {

    private val exchangeViewModel: ExchangeViewModel by lazy {
        ViewModelProvider(this).get(ExchangeViewModel::class.java)
    }
    private lateinit var exchangeAdapter: ExchangeAdapter2
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentExhangeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = exchangeViewModel

        setHasOptionsMenu(true)






        exchangeViewModel.exchangeData.observe(viewLifecycleOwner, { xData ->
            xData.let {
                exchangeAdapter.submitList(it)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.exchangeRecyclerView)
        setUpRecyclerView()

    }



    private fun setUpRecyclerView() = recyclerView.apply {
        exchangeAdapter = ExchangeAdapter2()
        adapter = exchangeAdapter
        hasFixedSize()
        layoutManager = LinearLayoutManager(this.context)
    }

    companion object {
        val TAG = ExchangeFragment::class.java.simpleName
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exchange_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_refresh -> {
                exchangeViewModel.refreshData()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}