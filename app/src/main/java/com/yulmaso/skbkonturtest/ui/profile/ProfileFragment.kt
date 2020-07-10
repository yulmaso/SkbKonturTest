package com.yulmaso.skbkonturtest.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.yulmaso.skbkonturtest.data.model.Contact
import com.yulmaso.skbkonturtest.databinding.FragmentProfileBinding
import com.yulmaso.skbkonturtest.di.injectViewModel
import com.yulmaso.skbkonturtest.ui.BaseFragment


class ProfileFragment: BaseFragment(), ProfileNavigator {

    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        viewModel.navigator = this
        viewModel.setContact(requireArguments().getSerializable("contact") as Contact)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentProfileBinding.inflate(
            inflater, container, false
        ).apply {
            viewmodel = viewModel
        }.root
    }

    override fun goBack() {
        findNavController().navigateUp()
    }

    override fun performPhoneDial() {
        viewModel.contact.value?.phone.let {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:${it}")
            startActivity(callIntent)
        }
    }
}