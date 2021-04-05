package com.example.mphasisassignment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mphasisassignment.R
import com.example.mphasisassignment.adapter.AlbumAdapter
import com.example.mphasisassignment.databinding.FragmentAlbumBinding
import com.example.mphasisassignment.utils.Resource
import com.example.mphasisassignment.viewmodel.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_album.*

@AndroidEntryPoint
class AlbumFragment : Fragment(), AlbumAdapter.AlbumItemListener {
    private lateinit var adapter: AlbumAdapter

    /**
     * Lazily initialize our [AlbumViewModel].
     */

    private val viewModel: AlbumViewModel by lazy {
        ViewModelProvider(this).get(AlbumViewModel::class.java)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }
    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the AlbumFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentAlbumBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the AlbumViewModel
        binding.viewModel = viewModel
        adapter = AlbumAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setupObservers() {
        viewModel.albums.observe(viewLifecycleOwner, Observer { it ->
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data.sortedBy { it.title }))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    progressBar.visibility = View.VISIBLE
            }
        })
    }
    override fun onClickedAlbum(id: String) {
        findNavController().navigate(
                R.id.action_albumFragment_to_albumDetailFragment,
                bundleOf("id" to id)
        )
    }
}