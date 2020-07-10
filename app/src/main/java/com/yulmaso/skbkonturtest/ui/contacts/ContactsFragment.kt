package com.yulmaso.skbkonturtest.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.yulmaso.skbkonturtest.R
import com.yulmaso.skbkonturtest.data.model.Contact
import com.yulmaso.skbkonturtest.databinding.FragmentContactsBinding
import com.yulmaso.skbkonturtest.di.injectViewModel
import com.yulmaso.skbkonturtest.ui.BaseFragment
import com.yulmaso.skbkonturtest.ui.RequestListener

class ContactsFragment: BaseFragment(), RequestListener, ContactsAdapter.ContactsListener {

    private lateinit var viewModel: ContactsViewModel

    private lateinit var adapter: ContactsAdapter

    private lateinit var binding: FragmentContactsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        viewModel.requestListener = this
        viewModel.updateData()

        adapter = ContactsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsBinding.inflate(
            inflater, container, false
        ).apply {
            lifecycleOwner = this@ContactsFragment
            viewmodel = viewModel
            contactsRv.adapter = adapter
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Observing contacts data
        viewModel.contacts.observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
        })

        // Observing search editText input
        viewModel.searchInput.observe(viewLifecycleOwner, Observer {
            adapter.filter.filter(it)
        })
    }

    override fun onStarted() {
        binding.contactsProgressBar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        binding.contactsProgressBar.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        binding.contactsProgressBar.visibility = View.GONE
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onListItemClick(contact: Contact) {
        val bundle = bundleOf("contact" to contact)
        findNavController().navigate(R.id.action_contactsFragment_to_profileFragment, bundle)
    }
}